package erp.base.api.service

import erp.base.api.constants.ErrorMessage
import erp.base.api.controller.VentasController
import erp.base.api.dto.ClienteDto
import erp.base.api.dto.ExcepcionesClienteDescDto
import erp.base.api.dto.PersonaClienteDto
import erp.base.api.dto.PersonaDto
import erp.base.api.dto.response.EstadoCuentoPagarDto
import erp.base.api.dto.response.PersonClientResponse
import erp.base.api.dto.response.PersonaClienteCreditoDto
import erp.base.api.dto.response.PersonaClienteResponse
import erp.base.api.enums.CuentasEnum
import erp.base.api.enums.OrigenIngresoEnum
import erp.base.api.enums.TaxIdentificationEnum
import erp.base.api.enums.TipoFiscalEnum
import erp.base.api.exception.CustomException
import erp.base.api.models.CategoriaCliente
import erp.base.api.models.Ciudad
import erp.base.api.models.Cliente
import erp.base.api.models.CreditoCliente
import erp.base.api.models.Parroquia
import erp.base.api.models.Persona
import erp.base.api.repository.CiudadRepository
import erp.base.api.repository.ClienteRepository
import erp.base.api.repository.CreditoClienteRepository
import erp.base.api.repository.ParroquiaRepository
import erp.base.api.repository.PersonaRepository
import erp.base.api.utils.Paginacion
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.math.BigDecimal
import java.math.RoundingMode

@Service
class ClienteService(
    private val tbaClienteRepository: ClienteRepository,
    private val tbaPersonaRepository: PersonaRepository,
    private val tbaPersonaService: PersonaService,
    private val tbaCreditoClienteService: CreditoClienteService,
    private val tbaCategoriaClienteService: CategoriaClienteService,
    private val tbaCreditoClienteRepository: CreditoClienteRepository,
    private val tbaCiudadRepository: CiudadRepository,
    private val tbaParroquiaRepository: ParroquiaRepository,
    private val excepcionesClienteDescuentoService: ExcepcionesClienteDescuentoService,
    private val ventasController: VentasController,
    private val sesionService: SesionService
) {

    @Transactional
    fun createCustomer(personaClienteCreditoDto: PersonaClienteCreditoDto): Mono<PersonClientResponse> {
        val personaClienteDto = convertPersonaClienteCreditoToPersonaCliente(personaClienteCreditoDto)
        personaClienteDto.cliente.idCuenta = personaClienteDto.cliente.idCuenta ?: CuentasEnum.CLIENTES_NACIONALES_NO_RELACIONADOS.idCuenta
        personaClienteDto.persona.esClientePersona = true
        personaClienteDto.persona.idIdentificacionFiscal = TaxIdentificationEnum.getIdByName(
            TipoFiscalEnum.getTipoIdentificacionById(personaClienteDto.cliente.idTipoIdFiscal!!)!!
        )
        return tbaPersonaRepository.findByIdentificacionPersona(personaClienteDto.persona.identificacionPersona!!)
            .flatMap { createClientWhenExistsPerson(it, personaClienteDto) }
            .switchIfEmpty(createClientWhenNotExistsPerson(personaClienteDto))
    }

    private fun createClientWhenExistsPerson(person: Persona, personaClienteDto: PersonaClienteDto): Mono<PersonClientResponse> =
        if (person.esClientePersona == true) {
            Mono.error(CustomException(HttpStatus.CONFLICT, ErrorMessage.PERSON_ALREADY_ASSOCIATED_WITH_CLIENT))
        } else {
            tbaPersonaService.updatePerson(person.idPersona!!, personaClienteDto.persona)
                .flatMap { person1 ->
                    buildAndSaveClient(person.idPersona!!, personaClienteDto.cliente)
                        .flatMap { client ->
                            personaClienteDto.credito!!.idCliente = client.idCliente
                            tbaCreditoClienteService.saveCredit(personaClienteDto.credito!!)
                                .flatMap { creditoCliente ->
                                    excepcionesClienteDescuentoService.saveUpdateAllDto(
                                        Flux.fromIterable(personaClienteDto.excepciones ?: emptyList()),
                                        client.idCliente!!.toLong()
                                    ).collectList()
                                        .flatMap { excepciones -> buildPersonClientResponse(person1, client, creditoCliente, excepciones) }
                                }
                        }
                }
        }

    private fun createClientWhenNotExistsPerson(personaClienteDto: PersonaClienteDto): Mono<PersonClientResponse> =
        tbaPersonaService.createPerson(personaClienteDto.persona)
            .flatMap { persona ->
                buildAndSaveClient(persona.idPersona!!, personaClienteDto.cliente)
                    .flatMap { client ->
                        personaClienteDto.credito!!.idCliente = client.idCliente
                        tbaCreditoClienteService.saveCredit(personaClienteDto.credito!!)
                            .flatMap { creditoCliente ->
                                excepcionesClienteDescuentoService.saveUpdateAllDto(
                                    Flux.fromIterable(personaClienteDto.excepciones ?: emptyList()),
                                    client.idCliente!!.toLong()
                                ).collectList()
                                    .flatMap { excepciones -> buildPersonClientResponse(persona, client, creditoCliente, excepciones) }
                            }
                    }
            }

    private fun buildPersonClientResponse(
        persona: Persona,
        cliente: Cliente,
        creditoCliente: CreditoCliente,
        excepciones: List<ExcepcionesClienteDescDto>
    ): Mono<PersonClientResponse> = Mono.just(PersonClientResponse(persona, cliente, creditoCliente, excepciones))

    fun updateClient(id: Int, personaClienteCreditoDto: PersonaClienteCreditoDto): Mono<Cliente> {
        val personaClienteDto = convertPersonaClienteCreditoToPersonaCliente(personaClienteCreditoDto)
        val clienteMono = tbaClienteRepository.findById(id)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.CLIENT_NOT_FOUND + id)))
        val personaMono = clienteMono.flatMap { cliente ->
            tbaPersonaRepository.findById(cliente.idPersona!!)
                .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.PERSON_NOT_EXISTS + cliente.idPersona)))
        }
        val creditoClienteMono = tbaCreditoClienteService.getCreditByClientId(id)
            .switchIfEmpty(Mono.just(CreditoCliente()))
        return Mono.zip(personaMono, clienteMono, creditoClienteMono).flatMap { tuple ->
            val persona = tuple.t1
            val cliente = tuple.t2
            val creditoCliente = tuple.t3
            buildAndUpdateClient(cliente, personaClienteDto.cliente).flatMap { cliente1 ->
                tbaPersonaService.updatePerson(persona.idPersona!!, personaClienteDto.persona).flatMap {
                    if (creditoCliente.idCreditoCliente == null) {
                        creditoCliente.idCliente = cliente1.idCliente
                    }
                    creditoCliente.cupoAutorizado = personaClienteDto.credito?.cupoAutorizado
                    creditoCliente.cupoDisponible = personaClienteDto.credito?.cupoDisponible
                    creditoCliente.cupoUtilizado = personaClienteDto.credito?.cupoUtilizado
                    creditoCliente.montoGarantia = personaClienteDto.credito?.montoGarantia
                    creditoCliente.fechaGarantia = personaClienteDto.credito?.fechaGarantia
                    creditoCliente.diasCredito = personaClienteDto.credito?.diasCredito
                    creditoCliente.observacion = personaClienteDto.credito?.observacion
                    creditoCliente.sobreCupo = personaClienteDto.credito?.sobreCupo
                    creditoCliente.fechaVencimientoSobreCupo = personaClienteDto.credito?.fechaVencimientoSobreCupo
                    tbaCreditoClienteRepository.save(creditoCliente).flatMap { Mono.just(cliente1) }
                }.flatMap {
                    excepcionesClienteDescuentoService.saveUpdateAllDto(
                        Flux.fromIterable(personaClienteDto.excepciones ?: emptyList()),
                        id.toLong()
                    ).collectList().flatMap {
                        excepcionesClienteDescuentoService.getExceptionsByClientId(id.toLong()).collectList()
                            .flatMap { exceptionsListBDD ->
                                excepcionesClienteDescuentoService.deleteRemovedDetails(exceptionsListBDD, it)
                            }
                    }.then(Mono.just(cliente1))
                }
            }
        }
    }

    private fun buildAndUpdateClient(clientToUpdate: Cliente, tbaClienteDto: ClienteDto): Mono<Cliente> {
        clientToUpdate.idCategoriaCliente = tbaClienteDto.idCategoriaCliente
        clientToUpdate.idOrigenIngreso = tbaClienteDto.idOrigenIngreso
        clientToUpdate.idTipoIdFiscal = tbaClienteDto.idTipoIdFiscal
        clientToUpdate.idCuenta = tbaClienteDto.idCuenta
        clientToUpdate.nombreComercialCliente = tbaClienteDto.nombreComercialCliente
        clientToUpdate.direccion2Cliente = tbaClienteDto.direccion2Cliente
        clientToUpdate.correo2Cliente = tbaClienteDto.correo2Cliente
        clientToUpdate.celular2Cliente = tbaClienteDto.celular2Cliente
        clientToUpdate.telefono2Cliente = tbaClienteDto.telefono2Cliente
        clientToUpdate.saldoAFavorCliente = tbaClienteDto.saldoAFavorCliente
        clientToUpdate.contactoCliente = tbaClienteDto.contactoCliente
        clientToUpdate.cargoContactoCliente = tbaClienteDto.cargoContactoCliente
        clientToUpdate.telefonoContactoCliente = tbaClienteDto.telefonoContactoCliente
        clientToUpdate.correoContactoCliente = tbaClienteDto.correoContactoCliente
        clientToUpdate.celularContactoCliente = tbaClienteDto.celularContactoCliente
        clientToUpdate.descuentoEspecialCliente = tbaClienteDto.descuentoEspecialCliente?.divide(BigDecimal(100))
        clientToUpdate.fechaCaducidadDescCliente = tbaClienteDto.fechaCaducidadDescCliente
        clientToUpdate.obsDescCliente = tbaClienteDto.obsDescCliente
        clientToUpdate.alertaGeneral = tbaClienteDto.alertaGeneral
        clientToUpdate.alertaFacturacion = tbaClienteDto.alertaFacturacion
        clientToUpdate.alertaCobro = tbaClienteDto.alertaCobro
        clientToUpdate.codDesbloqueoCliente = tbaClienteDto.codDesbloqueoCliente
        clientToUpdate.numCambioCliente = tbaClienteDto.numCambioCliente
        clientToUpdate.motivoInactivaCliente = tbaClienteDto.motivoInactivaCliente
        clientToUpdate.estadoCliente = tbaClienteDto.estadoCliente
        clientToUpdate.idUsuarioVentas = tbaClienteDto.idUsuarioVentas
        clientToUpdate.facContadoCliente = tbaClienteDto.facContadoCliente
        clientToUpdate.categoriaEspecialCliente = tbaClienteDto.categoriaEspecialCliente
        clientToUpdate.politicaCliente = tbaClienteDto.politicaCliente
        clientToUpdate.descuentoCliente = tbaClienteDto.descuentoCliente?.divide(BigDecimal(100))
        clientToUpdate.idRetencionIva = tbaClienteDto.idRetencionIva
        clientToUpdate.idRetencionFuente = tbaClienteDto.idRetencionFuente
        clientToUpdate.placa = tbaClienteDto.placa
        return tbaClienteRepository.save(clientToUpdate)
    }

    private fun convertPersonaClienteCreditoToPersonaCliente(personaClienteCreditoDto: PersonaClienteCreditoDto): PersonaClienteDto =
        PersonaClienteDto(
            getTbaPersonaDto(personaClienteCreditoDto),
            getTbaClienteDto(personaClienteCreditoDto),
            getTbaCreditoCliente(personaClienteCreditoDto),
            personaClienteCreditoDto.excepciones
        )

    private fun getTbaPersonaDto(personaClienteCreditoDto: PersonaClienteCreditoDto): PersonaDto = PersonaDto().apply {
        idPersona = personaClienteCreditoDto.idPersona
        idTipoContribuyente = personaClienteCreditoDto.idTipoContribuyente
        idPais = personaClienteCreditoDto.idPais
        idParroquia = personaClienteCreditoDto.idParroquia
        idEstadoCivil = personaClienteCreditoDto.idEstadoCivil
        idGenero = personaClienteCreditoDto.idGenero
        idIdentificacionFiscal = personaClienteCreditoDto.idIdentificacionFiscal
        identificacionPersona = personaClienteCreditoDto.identificacionPersona
        nombrePersona = personaClienteCreditoDto.nombrePersona
        fechaNacimientoPersona = personaClienteCreditoDto.fechaNacimientoPersona
        direccionPersona = personaClienteCreditoDto.direccionPersona
        celularPersona = personaClienteCreditoDto.celularPersona
        telefonoPersona = personaClienteCreditoDto.telefonoPersona
        correoPersona = personaClienteCreditoDto.correoPersona
        esClientePersona = personaClienteCreditoDto.esClientePersona
        esEmpleadoPersona = personaClienteCreditoDto.esEmpleadoPersona
        esProveedorPersona = personaClienteCreditoDto.esProveedorPersona
        fechaRegistroPersona = personaClienteCreditoDto.fechaRegistroPersona
        barrioDomicilioPersona = personaClienteCreditoDto.barrioDomicilioPersona
        cantonDomicilioPersona = personaClienteCreditoDto.cantonDomicilioPersona
        parroquiaDomicilioPersona = personaClienteCreditoDto.parroquiaDomicilioPersona
        sectorDomicilioPersona = personaClienteCreditoDto.sectorDomicilioPersona
    }

    private fun getTbaCreditoCliente(personaClienteCreditoDto: PersonaClienteCreditoDto): CreditoCliente = CreditoCliente().apply {
        idCreditoCliente = personaClienteCreditoDto.idCreditoCliente
        idCliente = personaClienteCreditoDto.idCliente
        cupoAutorizado = personaClienteCreditoDto.cupoAutorizado
        cupoDisponible = personaClienteCreditoDto.cupoDisponible
        cupoUtilizado = personaClienteCreditoDto.cupoUtilizado
        montoGarantia = personaClienteCreditoDto.montoGarantia
        fechaGarantia = personaClienteCreditoDto.fechaGarantia
        diasCredito = personaClienteCreditoDto.diasCredito
        observacion = personaClienteCreditoDto.observacion
        sobreCupo = personaClienteCreditoDto.sobreCupo
        fechaVencimientoSobreCupo = personaClienteCreditoDto.fechaVencimientoSobreCupo
    }

    private fun getTbaClienteDto(personaClienteCreditoDto: PersonaClienteCreditoDto): ClienteDto = ClienteDto().apply {
        idCategoriaCliente = personaClienteCreditoDto.idCategoriaCliente
        idOrigenIngreso = personaClienteCreditoDto.idOrigenIngreso
        idTipoIdFiscal = personaClienteCreditoDto.idTipoIdFiscal
        idPersona = personaClienteCreditoDto.idPersona
        idCuenta = personaClienteCreditoDto.idCuenta
        nombreComercialCliente = personaClienteCreditoDto.nombreComercialCliente
        direccion2Cliente = personaClienteCreditoDto.direccion2Cliente
        correo2Cliente = personaClienteCreditoDto.correo2Cliente
        celular2Cliente = personaClienteCreditoDto.celular2Cliente
        telefono2Cliente = personaClienteCreditoDto.telefono2Cliente
        saldoAFavorCliente = personaClienteCreditoDto.saldoAFavorCliente
        contactoCliente = personaClienteCreditoDto.contactoCliente
        cargoContactoCliente = personaClienteCreditoDto.cargoContactoCliente
        telefonoContactoCliente = personaClienteCreditoDto.telefonoContactoCliente
        correoContactoCliente = personaClienteCreditoDto.correoContactoCliente
        celularContactoCliente = personaClienteCreditoDto.celularContactoCliente
        descuentoEspecialCliente = personaClienteCreditoDto.descuentoEspecialCliente
        fechaCaducidadDescCliente = personaClienteCreditoDto.fechaCaducidadDescCliente
        obsDescCliente = personaClienteCreditoDto.obsDescCliente
        alertaGeneral = personaClienteCreditoDto.alertaGeneral
        alertaFacturacion = personaClienteCreditoDto.alertaFacturacion
        alertaCobro = personaClienteCreditoDto.alertaCobro
        codDesbloqueoCliente = personaClienteCreditoDto.codDesbloqueoCliente
        numCambioCliente = personaClienteCreditoDto.numCambioCliente
        motivoInactivaCliente = personaClienteCreditoDto.motivoInactivaCliente
        estadoCliente = personaClienteCreditoDto.estadoCliente
        idUsuarioVentas = personaClienteCreditoDto.idUsuarioVentas
        facContadoCliente = personaClienteCreditoDto.facContadoCliente
        categoriaEspecialCliente = personaClienteCreditoDto.categoriaEspecialCliente
        politicaCliente = personaClienteCreditoDto.politicaCliente
        descuentoCliente = personaClienteCreditoDto.descuentoCliente
        idRetencionIva = personaClienteCreditoDto.idRetencionIva
        idRetencionFuente = personaClienteCreditoDto.idRetencionFuente
        placa = personaClienteCreditoDto.placa
    }

    private fun buildAndSaveClient(personId: Int, tbaClienteDto: ClienteDto): Mono<Cliente> =
        tbaClienteRepository.save(
            Cliente(
                idCliente = null,
                idCategoriaCliente = tbaClienteDto.idCategoriaCliente,
                idOrigenIngreso = tbaClienteDto.idOrigenIngreso ?: OrigenIngresoEnum.EMPLEADO_PRIVADO.id,
                idTipoIdFiscal = tbaClienteDto.idTipoIdFiscal,
                idPersona = personId,
                idCuenta = tbaClienteDto.idCuenta ?: CuentasEnum.CLIENTES_NACIONALES_NO_RELACIONADOS.idCuenta,
                nombreComercialCliente = tbaClienteDto.nombreComercialCliente,
                direccion2Cliente = tbaClienteDto.direccion2Cliente,
                correo2Cliente = tbaClienteDto.correo2Cliente,
                celular2Cliente = tbaClienteDto.celular2Cliente,
                telefono2Cliente = tbaClienteDto.telefono2Cliente,
                saldoAFavorCliente = tbaClienteDto.saldoAFavorCliente,
                contactoCliente = tbaClienteDto.contactoCliente,
                cargoContactoCliente = tbaClienteDto.cargoContactoCliente,
                telefonoContactoCliente = tbaClienteDto.telefonoContactoCliente,
                correoContactoCliente = tbaClienteDto.correoContactoCliente,
                celularContactoCliente = tbaClienteDto.celularContactoCliente,
                descuentoEspecialCliente = tbaClienteDto.descuentoEspecialCliente?.divide(BigDecimal(100)),
                fechaCaducidadDescCliente = tbaClienteDto.fechaCaducidadDescCliente,
                obsDescCliente = tbaClienteDto.obsDescCliente,
                alertaGeneral = tbaClienteDto.alertaGeneral,
                alertaFacturacion = tbaClienteDto.alertaFacturacion,
                alertaCobro = tbaClienteDto.alertaCobro,
                codDesbloqueoCliente = tbaClienteDto.codDesbloqueoCliente,
                numCambioCliente = tbaClienteDto.numCambioCliente,
                motivoInactivaCliente = tbaClienteDto.motivoInactivaCliente,
                estadoCliente = tbaClienteDto.estadoCliente,
                idUsuarioVentas = tbaClienteDto.idUsuarioVentas,
                facContadoCliente = tbaClienteDto.facContadoCliente,
                categoriaEspecialCliente = tbaClienteDto.categoriaEspecialCliente,
                politicaCliente = tbaClienteDto.politicaCliente,
                descuentoCliente = tbaClienteDto.descuentoCliente?.divide(BigDecimal(100), 2, RoundingMode.HALF_UP),
                idRetencionIva = tbaClienteDto.idRetencionIva,
                idRetencionFuente = tbaClienteDto.idRetencionFuente,
                placa = tbaClienteDto.placa,
                idUsuarioCreacion = null,
                idUsuarioModificacion = null,
                fechaCreacion = null,
                fechaModificacion = null
            )
        )

    fun findClientById(id: Int, userId: Int): Mono<PersonaClienteCreditoDto> {
        val clienteMono = tbaClienteRepository.findById(id)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.CLIENT_NOT_FOUND + id)))
        val personaMono = clienteMono.flatMap { cliente ->
            tbaPersonaRepository.findById(cliente.idPersona!!)
                .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.PERSON_NOT_EXISTS + cliente.idPersona)))
        }
        val creditoClienteMono = tbaCreditoClienteService.getCreditByClientId(id)
            .switchIfEmpty(Mono.just(CreditoCliente()))
        val parroquiaMono = personaMono.flatMap { persona ->
            tbaParroquiaRepository.findById(persona.idParroquia!!).switchIfEmpty(Mono.just(Parroquia()))
        }
        val ciudadMono = parroquiaMono.flatMap { parroquia ->
            tbaCiudadRepository.findById(parroquia.idCiudad!!).switchIfEmpty(Mono.just(Ciudad()))
        }
        val excepcionesMono = excepcionesClienteDescuentoService.getAllDtoByIdCliente(id.toLong()).collectList()
        val estadoCuentaPagarMono = getEstadoCuentaController(userId, id)
        return Mono.zip(clienteMono, personaMono, creditoClienteMono, parroquiaMono, ciudadMono, excepcionesMono, estadoCuentaPagarMono).flatMap { tuple ->
            val persona = tuple.t2
            val cliente = tuple.t1
            val creditoCliente = tuple.t3
            val ciudad = tuple.t5
            val excepciones = tuple.t6
            val estadoCuenta = tuple.t7
            val dto = PersonaClienteCreditoDto().apply {
                idPersona = persona.idPersona
                idTipoContribuyente = persona.idTipoContribuyente
                idPais = persona.idPais
                idParroquia = persona.idParroquia
                idCiudad = ciudad.idCiudad
                idProvincia = ciudad.idProvincia
                idEstadoCivil = persona.idEstadoCivil
                idGenero = persona.idGenero
                idIdentificacionFiscal = persona.idIdentificacionFiscal
                identificacionPersona = persona.identificacionPersona
                nombrePersona = persona.nombrePersona
                fechaNacimientoPersona = persona.fechaNacimientoPersona
                direccionPersona = persona.direccionPersona
                celularPersona = persona.celularPersona
                telefonoPersona = persona.telefonoPersona
                correoPersona = persona.correoPersona
                esClientePersona = persona.esClientePersona
                esEmpleadoPersona = persona.esEmpleadoPersona
                esProveedorPersona = persona.esProveedorPersona
                fechaRegistroPersona = persona.fechaRegistroPersona
                barrioDomicilioPersona = persona.barrioDomicilioPersona
                cantonDomicilioPersona = persona.cantonDomicilioPersona
                parroquiaDomicilioPersona = persona.parroquiaDomicilioPersona
                sectorDomicilioPersona = persona.sectorDomicilioPersona
                idCliente = cliente.idCliente
                idCategoriaCliente = cliente.idCategoriaCliente
                idOrigenIngreso = cliente.idOrigenIngreso
                idTipoIdFiscal = cliente.idTipoIdFiscal
                idPersona = cliente.idPersona
                idCuenta = cliente.idCuenta
                nombreComercialCliente = cliente.nombreComercialCliente
                direccion2Cliente = cliente.direccion2Cliente
                correo2Cliente = cliente.correo2Cliente
                celular2Cliente = cliente.celular2Cliente
                telefono2Cliente = cliente.telefono2Cliente
                saldoAFavorCliente = cliente.saldoAFavorCliente
                contactoCliente = cliente.contactoCliente
                cargoContactoCliente = cliente.cargoContactoCliente
                telefonoContactoCliente = cliente.telefonoContactoCliente
                correoContactoCliente = cliente.correoContactoCliente
                celularContactoCliente = cliente.celularContactoCliente
                descuentoEspecialCliente = cliente.descuentoEspecialCliente?.multiply(BigDecimal(100))
                fechaCaducidadDescCliente = cliente.fechaCaducidadDescCliente
                obsDescCliente = cliente.obsDescCliente
                alertaGeneral = cliente.alertaGeneral
                alertaFacturacion = cliente.alertaFacturacion
                alertaCobro = cliente.alertaCobro
                codDesbloqueoCliente = cliente.codDesbloqueoCliente
                numCambioCliente = cliente.numCambioCliente
                motivoInactivaCliente = cliente.motivoInactivaCliente
                estadoCliente = cliente.estadoCliente
                idUsuarioVentas = cliente.idUsuarioVentas
                facContadoCliente = cliente.facContadoCliente
                categoriaEspecialCliente = cliente.categoriaEspecialCliente
                politicaCliente = cliente.politicaCliente
                descuentoCliente = cliente.descuentoCliente?.multiply(BigDecimal(100))
                idRetencionIva = cliente.idRetencionIva
                idRetencionFuente = cliente.idRetencionFuente
                idCreditoCliente = creditoCliente.idCreditoCliente
                cupoAutorizado = creditoCliente.cupoAutorizado ?: BigDecimal.ZERO
                cupoDisponible = creditoCliente.cupoDisponible ?: BigDecimal.ZERO
                cupoUtilizado = creditoCliente.cupoUtilizado ?: BigDecimal.ZERO
                montoGarantia = creditoCliente.montoGarantia ?: BigDecimal.ZERO
                fechaGarantia = creditoCliente.fechaGarantia
                diasCredito = creditoCliente.diasCredito ?: 0
                observacion = creditoCliente.observacion
                sobreCupo = creditoCliente.sobreCupo ?: BigDecimal.ZERO
                placa = cliente.placa
                fechaVencimientoSobreCupo = creditoCliente.fechaVencimientoSobreCupo
                fechaCreacion = cliente.fechaCreacion
                fechaModificacion = cliente.fechaModificacion
                this.excepciones = excepciones
                detalleEstadoCuentaCobrarDtoList = estadoCuenta
            }
            Mono.just(dto)
        }
    }

    fun getCustomerByParams(
        customerName: String?,
        customerCategoryId: Int?,
        customerIdentification: String?,
        page: Int,
        limit: Int
    ): Mono<Paginacion<Cliente>> {
        val searchByName = customerName != null && customerCategoryId == null && customerIdentification == null
        val searchByCategory = customerName == null && customerCategoryId != null && customerIdentification == null
        val pageable: Pageable = PageRequest.of(page - 1, limit)
        val (totalRegisters, customerFlux) = when {
            searchByCategory -> tbaClienteRepository.countByIdCategoriaCliente(customerCategoryId!!) to
                tbaClienteRepository.findByIdCategoriaCliente(customerCategoryId, pageable)
            searchByName -> tbaClienteRepository.countByNombreComercialCliente(customerName) to
                tbaClienteRepository.findByNombreComercialClienteContaining(customerName, pageable)
            else -> tbaClienteRepository.count() to tbaClienteRepository.findAllBy(pageable)
        }
        return totalRegisters.flatMap { total ->
            customerFlux.collectList().map { tbaClientesList -> Paginacion(tbaClientesList, page, limit, total.toInt()) }
        }
    }

    fun getClientById(clientId: Int): Mono<PersonaClienteResponse> {
        val clienteMono = tbaClienteRepository.findById(clientId)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.CLIENT_NOT_FOUND + clientId)))
        val personaMono = clienteMono.flatMap { cliente ->
            tbaPersonaRepository.findById(cliente.idPersona!!)
                .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.PERSON_NOT_EXISTS + cliente.idPersona)))
        }
        val creditoClienteMono = tbaCreditoClienteService.getCreditByClientId(clientId)
            .switchIfEmpty(Mono.just(CreditoCliente()))
        val categoriaClienteMono = clienteMono.flatMap { cliente ->
            tbaCategoriaClienteService.getCategoryClientById(cliente.idCategoriaCliente!!)
                .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.CATEGORY_CLIENT_NOT_FOUND + cliente.idCategoriaCliente)))
        }
        return Mono.zip(clienteMono, personaMono, creditoClienteMono, categoriaClienteMono).flatMap { tuple ->
            val dto = PersonaClienteResponse().apply {
                personaId = tuple.t2.idPersona
                clienteId = tuple.t1.idCliente
                placa = tuple.t1.placa
                identificacion = tuple.t2.identificacionPersona
                nombre = tuple.t2.nombrePersona
                nombreCliente = tuple.t1.nombreComercialCliente
                email = tuple.t2.correoPersona
                cupoAutorizado = tuple.t3.cupoAutorizado
                cupoUtilizado = tuple.t3.cupoUtilizado
                sobreCupo = tuple.t3.sobreCupo
                cupoDisponible = tuple.t3.cupoDisponible
                categoriaCliente = tuple.t4.nombreCategoriaCliente
                contactoCliente = tuple.t1.contactoCliente
                telefono = tuple.t2.telefonoPersona
                diasCredito = tuple.t3.diasCredito
                observacion = tuple.t3.observacion
            }
            Mono.just(dto)
        }
    }

    fun verifyClientExistsByIdentification(identification: String): Mono<String> =
        tbaPersonaRepository.existsByIdentificacionPersonaAndEsClientePersona(identification, true)
            .flatMap { exists ->
                if (exists) Mono.error(CustomException(HttpStatus.CONFLICT, ErrorMessage.CLIENT_ALREADY_EXISTS_WITH_IDENTIFICATION))
                else Mono.just("OK")
            }

    private fun getEstadoCuentaController(userId: Int, clientId: Int): Mono<EstadoCuentoPagarDto> =
        sesionService.getHeadersByUser(userId)
            .flatMap { ventasController.getEstadoCuentaCobrar(clientId, it) }
}
