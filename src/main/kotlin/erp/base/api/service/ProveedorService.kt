package erp.base.api.service

import erp.base.api.constants.ErrorMessage
import erp.base.api.controller.ContabilidadController
import erp.base.api.dto.PersonaProveedorDto
import erp.base.api.dto.ProveedorDto
import erp.base.api.dto.ProveedorPatchDto
import erp.base.api.dto.response.EstadoCuentoPagarDto
import erp.base.api.dto.response.PersonProviderResponse
import erp.base.api.dto.response.PersonaProveedorResponse
import erp.base.api.dto.response.ProveedorImpResponse
import erp.base.api.dto.response.ProveedorResponseDto
import erp.base.api.enums.CountryEnum
import erp.base.api.enums.CuentaProveedorEnum
import erp.base.api.exception.CustomException
import erp.base.api.models.Ciudad
import erp.base.api.models.Parroquia
import erp.base.api.models.Persona
import erp.base.api.models.Proveedor
import erp.base.api.models.Provincia
import erp.base.api.repository.CiudadRepository
import erp.base.api.repository.ParroquiaRepository
import erp.base.api.repository.PersonaRepository
import erp.base.api.repository.ProveedorRepository
import erp.base.api.repository.ProvinciaRepository
import erp.base.api.repository.custom.ProveedorCustom
import erp.base.api.utils.Paginacion
import org.slf4j.LoggerFactory
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDate
import java.util.Collections

@Service
class ProveedorService(
    private val tbaProveedorRepository: ProveedorRepository,
    private val tbaPersonaRepository: PersonaRepository,
    private val tbaCiudadRepository: CiudadRepository,
    private val tbaParroquiaRepository: ParroquiaRepository,
    private val tbaProvinciaRepository: ProvinciaRepository,
    private val tbaPersonaService: PersonaService,
    private val tbaProveedorCustom: ProveedorCustom,
    private val contabilidadController: ContabilidadController,
    private val sesionService: SesionService
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @Transactional
    fun createProvider(personaProveedorDto: PersonaProveedorDto): Mono<PersonProviderResponse> {
        personaProveedorDto.persona.esProveedorPersona = true
        return tbaPersonaRepository.findByIdentificacionPersona(personaProveedorDto.persona.identificacionPersona!!)
            .flatMap { createProviderWhenExistPerson(it, personaProveedorDto) }
            .switchIfEmpty(createProviderWhenNotExistPerson(personaProveedorDto))
    }

    private fun createProviderWhenExistPerson(persona: Persona, personaProveedorDto: PersonaProveedorDto): Mono<PersonProviderResponse> =
        if (persona.esProveedorPersona == true) {
            Mono.error(CustomException(HttpStatus.CONFLICT, ErrorMessage.PERSON_ALREADY_ASSOCIATED_WITH_PROVIDER))
        } else {
            tbaPersonaService.updatePerson(persona.idPersona!!, personaProveedorDto.persona)
                .flatMap { buildAndSaveProvider(it, personaProveedorDto.proveedor) }
                .flatMap { buildPersonProviderResponse(persona, it) }
        }

    private fun createProviderWhenNotExistPerson(personaProveedorDto: PersonaProveedorDto): Mono<PersonProviderResponse> =
        tbaPersonaService.createPerson(personaProveedorDto.persona)
            .flatMap { person -> buildAndSaveProvider(person, personaProveedorDto.proveedor)
                .flatMap { provider -> buildPersonProviderResponse(person, provider) } }

    private fun buildPersonProviderResponse(persona: Persona, proveedor: Proveedor): Mono<PersonProviderResponse> =
        Mono.just(PersonProviderResponse(persona, proveedor))

    fun updateProvider(id: Int, personaProveedorDto: PersonaProveedorDto): Mono<Proveedor> =
        tbaProveedorRepository.findById(id)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.PROVIDER_NOT_FOUND + id)))
            .flatMap { proveedor ->
                personaProveedorDto.persona.esProveedorPersona = true
                tbaPersonaService.updatePerson(proveedor.idPersona!!, personaProveedorDto.persona)
                    .flatMap { buildAndUpdateProvider(proveedor, personaProveedorDto.proveedor) }
            }

    private fun buildAndUpdateProvider(proveedor: Proveedor, tbaProveedorDto: ProveedorDto): Mono<Proveedor> {
        proveedor.idTipoIdFiscal = tbaProveedorDto.idTipoIdFiscal
        proveedor.idRetencionIva = tbaProveedorDto.idRetencionIva
        proveedor.idBancoCatalogo = tbaProveedorDto.idBancoCatalogo
        proveedor.idRetencionFuente = tbaProveedorDto.idRetencionFuente
        proveedor.idTipoProveedor = tbaProveedorDto.idTipoProv
        proveedor.idTipoCuentaBancaria = tbaProveedorDto.idTipoCuentaBancaria
        proveedor.idCondicionPago = tbaProveedorDto.idCondicionPago
        proveedor.idFormaPagoCobro = tbaProveedorDto.idFormaPagoCobro
        proveedor.nombreComercialProveedor = tbaProveedorDto.nombreComercialProveedor
        proveedor.direccion2Proveedor = tbaProveedorDto.direccion2Proveedor
        proveedor.correo2Proveedor = tbaProveedorDto.correo2Proveedor
        proveedor.celular2Proveedor = tbaProveedorDto.celular2Proveedor
        proveedor.telefono2Proveedor = tbaProveedorDto.telefono2Proveedor
        proveedor.sitioWebProveedor = tbaProveedorDto.sitioWebProveedor
        proveedor.contactoProveedor = tbaProveedorDto.contactoProveedor
        proveedor.telefonoContactoProveedor = tbaProveedorDto.telefonoContactoProveedor
        proveedor.correoContactoProveedor = tbaProveedorDto.correoContactoProveedor
        proveedor.creditoProveedor = tbaProveedorDto.creditoProveedor
        proveedor.montoCreditoProveedor = tbaProveedorDto.montoCreditoProveedor
        proveedor.fechaVencimientoCreditoProveedor = tbaProveedorDto.fechaVencimientoCreditoProveedor
        proveedor.diasCreditoProveedor = tbaProveedorDto.diasCreditoProveedor
        proveedor.montoGarantiaProveedor = tbaProveedorDto.montoGarantiaProveedor
        proveedor.numeroCuentaBancariaProveedor = tbaProveedorDto.numCuentaBancariaProveedor
        proveedor.referenciaPagoProveedor = tbaProveedorDto.referenciaPagoProveedor
        proveedor.saldoAnticipoProveedor = tbaProveedorDto.saldoAnticipoProveedor
        proveedor.nombreChequeProveedor = tbaProveedorDto.nombreChequeProveedor
        proveedor.contribucionEspecialProveedor = tbaProveedorDto.contribEspecialProveedor
        proveedor.esTransporteProveedor = tbaProveedorDto.esTransporteProveedor
        proveedor.estadoProveedor = tbaProveedorDto.estadoProveedor
        proveedor.numeroCuentaPago = tbaProveedorDto.numeroCuentaPago
        proveedor.idIdentificacionFiscalPago = tbaProveedorDto.idIdentificacionFiscalPago
        proveedor.cuentaPasivaProveedor = tbaProveedorDto.cuentaPasivaProveedor
        proveedor.cuentaGastoProveedor = tbaProveedorDto.cuentaGastoProveedor
        return tbaProveedorRepository.save(proveedor)
    }

    fun patchProvider(id: Int, proveedorPatchDto: ProveedorPatchDto): Mono<Proveedor> {
        val existsPersonMono = proveedorPatchDto.idPersona?.let { tbaPersonaRepository.existsById(it) } ?: Mono.just(false)
        val existsProviderByPersonMono = proveedorPatchDto.idPersona?.let { tbaProveedorRepository.existsByIdPersona(it) } ?: Mono.just(false)
        val providerMono = tbaProveedorRepository.findById(id)
        return Mono.zip(existsPersonMono, existsProviderByPersonMono, providerMono).flatMap { tuple ->
            val existPerson = tuple.t1
            val existProviderByPerson = tuple.t2
            val provider = tuple.t3
            if (proveedorPatchDto.idPersona != null) {
                if (!existPerson) return@flatMap Mono.error<Proveedor>(
                    CustomException(HttpStatus.NOT_FOUND, ErrorMessage.PERSON_NOT_FOUND + proveedorPatchDto.idPersona)
                )
                if (existProviderByPerson) return@flatMap Mono.error<Proveedor>(
                    CustomException(HttpStatus.CONFLICT, ErrorMessage.PERSON_ALREADY_ASSOCIATED_WITH_PROVIDER)
                )
                provider.idPersona = proveedorPatchDto.idPersona
            }
            proveedorPatchDto.idTipoIdFiscal?.let { provider.idTipoIdFiscal = it }
            proveedorPatchDto.idRetencionIva?.let { provider.idRetencionIva = it }
            proveedorPatchDto.idBancoCatalogo?.let { provider.idBancoCatalogo = it }
            proveedorPatchDto.idRetencionFuente?.let { provider.idRetencionFuente = it }
            proveedorPatchDto.idTipoProv?.let { provider.idTipoProveedor = it }
            proveedorPatchDto.idTipoCuentaBancaria?.let { provider.idTipoCuentaBancaria = it }
            proveedorPatchDto.idCondicionPago?.let { provider.idCondicionPago = it }
            proveedorPatchDto.idFormaPagoCobro?.let { provider.idFormaPagoCobro = it }
            proveedorPatchDto.nombreComercialProveedor?.let { provider.nombreComercialProveedor = it }
            proveedorPatchDto.direccion2Proveedor?.let { provider.direccion2Proveedor = it }
            proveedorPatchDto.correo2Proveedor?.let { provider.correo2Proveedor = it }
            proveedorPatchDto.celular2Proveedor?.let { provider.celular2Proveedor = it }
            proveedorPatchDto.telefono2Proveedor?.let { provider.telefono2Proveedor = it }
            proveedorPatchDto.sitioWebProveedor?.let { provider.sitioWebProveedor = it }
            proveedorPatchDto.contactoProveedor?.let { provider.contactoProveedor = it }
            proveedorPatchDto.telefonoContactoProveedor?.let { provider.telefonoContactoProveedor = it }
            proveedorPatchDto.correoContactoProveedor?.let { provider.correoContactoProveedor = it }
            proveedorPatchDto.celularContactoProveedor?.let { provider.celularContactoProveedor = it }
            proveedorPatchDto.creditoProveedor?.let { provider.creditoProveedor = it }
            proveedorPatchDto.montoCreditoProveedor?.let { provider.montoCreditoProveedor = it }
            proveedorPatchDto.fechaVencimientoCreditoProveedor?.let { provider.fechaVencimientoCreditoProveedor = it }
            proveedorPatchDto.diasCreditoProveedor?.let { provider.diasCreditoProveedor = it }
            proveedorPatchDto.montoGarantiaProveedor?.let { provider.montoGarantiaProveedor = it }
            proveedorPatchDto.numCuentaBancariaProveedor?.let { provider.numeroCuentaBancariaProveedor = it }
            proveedorPatchDto.referenciaPagoProveedor?.let { provider.referenciaPagoProveedor = it }
            proveedorPatchDto.saldoAnticipoProveedor?.let { provider.saldoAnticipoProveedor = it }
            proveedorPatchDto.nombreChequeProveedor?.let { provider.nombreChequeProveedor = it }
            proveedorPatchDto.contribEspecialProveedor?.let { provider.contribucionEspecialProveedor = it }
            proveedorPatchDto.esTransporteProveedor?.let { provider.esTransporteProveedor = it }
            proveedorPatchDto.estadoProveedor?.let { provider.estadoProveedor = it }
            proveedorPatchDto.numeroCuentaPago?.let { provider.numeroCuentaPago = it }
            proveedorPatchDto.idIdentificacionFiscalPago?.let { provider.idIdentificacionFiscalPago = it }
            proveedorPatchDto.cuentaPasivaProveedor?.let { provider.cuentaPasivaProveedor = it }
            proveedorPatchDto.cuentaGastoProveedor?.let { provider.cuentaGastoProveedor = it }
            tbaProveedorRepository.save(provider)
        }
    }

    fun getProvidersBySearchParams(
        searchForeign: Boolean?,
        page: Int,
        limit: Int,
        sortDirection: String?,
        sortField: String?
    ): Mono<Paginacion<Proveedor>> {
        val pageable = try {
            val sort = if (sortDirection != null && sortField != null) {
                Sort.by(Sort.Direction.fromString(sortDirection), sortField)
            } else {
                Sort.unsorted()
            }
            PageRequest.of(page - 1, limit, sort)
        } catch (e: Exception) {
            return Mono.error(CustomException(HttpStatus.BAD_REQUEST, e.cause?.message ?: e.message!!))
        }
        val notSearchCountryIdList = listOf(CountryEnum.ECUADOR.idCountry)
        val sortFieldVal = sortField ?: "id_proveedor"
        val sortDirectionVal = sortDirection ?: "ASC"
        val offset = (pageable.pageNumber * pageable.pageSize).toLong()
        return if (searchForeign == true) {
            val tbaProveedorFlux = tbaProveedorCustom.findByIdPaisNotInAndEstado(
                notSearchCountryIdList,
                true,
                sortFieldVal,
                sortDirectionVal,
                pageable.pageSize,
                offset
            ).onErrorMap { CustomException(HttpStatus.BAD_REQUEST, it.cause?.message ?: it.message!!) }
            val totalProvider = tbaProveedorRepository.countByIdPaisNotInAndEstado(notSearchCountryIdList, true)
                .onErrorMap { CustomException(HttpStatus.BAD_REQUEST, it.cause?.message ?: it.message!!) }
            totalProvider.flatMap { total ->
                tbaProveedorFlux.collectList().map { tbaProviders -> Paginacion(tbaProviders, page, limit, total.toInt()) }
            }.onErrorMap { CustomException(HttpStatus.BAD_REQUEST, it.cause?.message ?: it.message!!) }
        } else {
            val tbaProveedorFlux = tbaProveedorRepository.findAllBy(pageable)
                .onErrorMap { CustomException(HttpStatus.BAD_REQUEST, it.cause?.message ?: it.message!!) }
            val totalProvider = tbaProveedorRepository.count()
                .onErrorMap { CustomException(HttpStatus.BAD_REQUEST, it.cause?.message ?: it.message!!) }
            totalProvider.flatMap { total ->
                tbaProveedorFlux.collectList().map { tbaProviders -> Paginacion(tbaProviders, page, limit, total.toInt()) }
            }.onErrorMap { CustomException(HttpStatus.BAD_REQUEST, it.cause?.message ?: it.message!!) }
        }
    }

    fun getProviderByPersonId(persona: Persona): Mono<PersonaProveedorResponse> =
        tbaProveedorRepository.findByIdPersona(persona.idPersona!!)
            .map { proveedor ->
                PersonaProveedorResponse().apply {
                    nombreProveedor = proveedor.nombreComercialProveedor
                    proveedorId = proveedor.idProveedor
                    nombre = persona.nombrePersona
                    personaId = persona.idPersona
                    identificacion = persona.identificacionPersona
                    idCondicionPago = proveedor.idCondicionPago
                    esExtranjero = persona.idPais != CountryEnum.ECUADOR.idCountry
                }
            }

    private fun getEstadoCuentaController(userId: Int, proveedorId: Int): Mono<EstadoCuentoPagarDto> =
        sesionService.getHeadersByUser(userId)
            .flatMap { contabilidadController.getEstadoCuentaPagar(proveedorId, it) }

    fun getProvider(proveedorId: Int, userId: Int): Mono<ProveedorResponseDto> {
        val tbaProveedorDtoMono = tbaProveedorRepository.findById(proveedorId)
        val tbaPersonaMono = tbaProveedorDtoMono.flatMap { tbaPersonaRepository.findById(it.idPersona!!) }
            .onErrorMap { CustomException(HttpStatus.BAD_REQUEST, it.cause?.message ?: it.message!!) }
        val numberInvoicesMono = tbaProveedorRepository.numberInvoicesByProvider(proveedorId)
            .onErrorMap { CustomException(HttpStatus.BAD_REQUEST, it.cause?.message ?: it.message!!) }
        val parishMono = tbaPersonaMono.flatMap { tbaPersona ->
            if (tbaPersona.idParroquia != null) tbaParroquiaRepository.findById(tbaPersona.idParroquia!!)
            else Mono.just(Parroquia())
        }.onErrorMap { CustomException(HttpStatus.BAD_REQUEST, it.cause?.message ?: it.message!!) }
        val cityMono = parishMono.flatMap { parroquia ->
            if (parroquia.idCiudad != null) tbaCiudadRepository.findById(parroquia.idCiudad!!)
            else Mono.just(Ciudad())
        }.onErrorMap { CustomException(HttpStatus.BAD_REQUEST, it.cause?.message ?: it.message!!) }
        val provinceMono = cityMono.flatMap { ciudad ->
            if (ciudad.idProvincia != null) tbaProvinciaRepository.findById(ciudad.idProvincia!!)
            else Mono.just(Provincia())
        }.onErrorMap { CustomException(HttpStatus.BAD_REQUEST, it.cause?.message ?: it.message!!) }
        val estadoCuentoPagarDtoMono = getEstadoCuentaController(userId, proveedorId)

        return Mono.zip(
            tbaProveedorDtoMono,
            numberInvoicesMono,
            estadoCuentoPagarDtoMono,
            tbaPersonaMono,
            parishMono,
            cityMono,
            provinceMono
        ).flatMap { tuple ->
            val provider = tuple.t1
            val numeroFacturas = tuple.t2
            val estadoCuenta = tuple.t3
            val tbaProveedorResponseDto = ProveedorResponseDto().apply {
                this.proveedor = provider
                this.numeroFacturas = numeroFacturas
                nombreParroquia = tuple.t5.nombreParroquia
                idCiudad = tuple.t6.idCiudad
                nombreCiudad = tuple.t6.nombreCiudad
                idProvincia = tuple.t7.idProvincia
                nombreProvincia = tuple.t7.nombreProvincia
                persona = tuple.t4
                estadoCuentoPagar = estadoCuenta
            }
            Mono.just(tbaProveedorResponseDto)
        }.onErrorMap { CustomException(HttpStatus.BAD_REQUEST, it.cause?.message ?: it.message!!) }
    }

    fun getProviderById(providerId: Int): Mono<PersonaProveedorResponse> =
        tbaProveedorRepository.findById(providerId)
            .flatMap { proveedor ->
                tbaPersonaRepository.findById(proveedor.idPersona!!)
                    .map { persona ->
                        PersonaProveedorResponse(
                            proveedorId = proveedor.idProveedor,
                            nombreProveedor = proveedor.nombreComercialProveedor,
                            identificacion = persona.identificacionPersona,
                            nombre = persona.nombrePersona,
                            personaId = persona.idPersona,
                            direccion = persona.direccionPersona,
                            email = persona.correoPersona,
                            telefono = persona.telefonoPersona,
                            idRetencionFuente = proveedor.idRetencionFuente,
                            idRetencionIva = proveedor.idRetencionIva,
                            diasCredito = proveedor.diasCreditoProveedor,
                        )
                    }
            }

    fun getProvidersNameOCPending(): Flux<PersonaProveedorResponse> =
        tbaPersonaRepository.findAllPendingPurchaseOrderProvidersPersons()
            .filter { it.idPais == CountryEnum.ECUADOR.idCountry }
            .flatMapSequential { getProviderByPersonId(it) }

    fun getAllByListaPedidoAprobadas(): Flux<PersonaProveedorResponse> =
        tbaPersonaRepository.getAllByListaPedidoAprobadas()
            .flatMap { getProviderByPersonId(it) }

    fun getProviderForImport(proveedorId: Int): Mono<ProveedorImpResponse> =
        tbaProveedorRepository.findById(proveedorId)
            .flatMap { proveedor ->
                tbaPersonaRepository.findById(proveedor.idPersona!!)
                    .map { persona ->
                        ProveedorImpResponse().apply {
                            this.proveedorId = proveedorId
                            nombreProveedor = proveedor.nombreComercialProveedor
                            direccionProveedor = persona.direccionPersona
                            contacto = persona.nombrePersona
                            identificacion = persona.identificacionPersona
                            correoList = listOfNotNull(
                                persona.correoPersona,
                                proveedor.correo2Proveedor,
                                proveedor.correoContactoProveedor
                            )
                            telefonoList = listOfNotNull(
                                persona.celularPersona,
                                persona.telefonoPersona,
                                proveedor.celular2Proveedor,
                                proveedor.celularContactoProveedor
                            )
                        }
                    }
            }

    fun getProviderByOrderListId(id: Int): Flux<PersonaProveedorResponse> =
        tbaProveedorRepository.findByOrderListId(id, 1)
            .onErrorMap { CustomException(HttpStatus.BAD_REQUEST, it.cause?.message ?: it.message!!) }
            .flatMap { provider ->
                tbaPersonaRepository.findById(provider.idPersona!!)
                    .map { persona ->
                        PersonaProveedorResponse().apply {
                            nombreProveedor = provider.nombreComercialProveedor
                            proveedorId = provider.idProveedor
                            nombre = persona.nombrePersona
                            personaId = persona.idPersona
                            identificacion = persona.identificacionPersona
                        }
                    }
            }
            .onErrorMap { CustomException(HttpStatus.BAD_REQUEST, it.cause?.message ?: it.message!!) }

    fun verifyProviderExistsByIdentification(identification: String): Mono<String> =
        tbaPersonaRepository.existsByIdentificacionPersonaAndEsProveedorPersona(identification, true)
            .flatMap { exists ->
                if (exists) Mono.error(CustomException(HttpStatus.CONFLICT, ErrorMessage.PROVIDER_ALREADY_EXISTS_WITH_IDENTIFICATION))
                else Mono.just("OK")
            }

    fun getCarriers(): Flux<PersonaProveedorResponse> =
        tbaProveedorRepository.findByEsTransporteProveedorAndEstadoProveedor(true, true)
            .flatMap { provider ->
                tbaPersonaRepository.findById(provider.idPersona!!)
                    .map { persona ->
                        PersonaProveedorResponse().apply {
                            nombreProveedor = provider.nombreComercialProveedor
                            proveedorId = provider.idProveedor
                            nombre = persona.nombrePersona
                            personaId = persona.idPersona
                            identificacion = persona.identificacionPersona
                        }
                    }
            }

    private fun buildAndSaveProvider(person: Persona, tbaProveedor: ProveedorDto): Mono<Proveedor> =
        tbaProveedorRepository.save(
            Proveedor(
                idUsuarioCreacion = null,
                idUsuarioModificacion = null,
                fechaCreacion = null,
                fechaModificacion = null,
                idProveedor = null,
                idTipoIdFiscal = tbaProveedor.idTipoIdFiscal,
                idRetencionIva = tbaProveedor.idRetencionIva,
                idPersona = person.idPersona,
                idBancoCatalogo = tbaProveedor.idBancoCatalogo,
                idRetencionFuente = tbaProveedor.idRetencionFuente,
                idTipoProveedor = tbaProveedor.idTipoProv,
                idTipoCuentaBancaria = tbaProveedor.idTipoCuentaBancaria,
                idCondicionPago = tbaProveedor.idCondicionPago,
                idFormaPagoCobro = tbaProveedor.idFormaPagoCobro,
                nombreComercialProveedor = tbaProveedor.nombreComercialProveedor,
                direccion2Proveedor = tbaProveedor.direccion2Proveedor,
                correo2Proveedor = tbaProveedor.correo2Proveedor,
                celular2Proveedor = tbaProveedor.celular2Proveedor,
                telefono2Proveedor = tbaProveedor.telefono2Proveedor,
                sitioWebProveedor = tbaProveedor.sitioWebProveedor,
                contactoProveedor = tbaProveedor.contactoProveedor,
                cargoContactoProveedor = null,
                telefonoContactoProveedor = tbaProveedor.telefonoContactoProveedor,
                correoContactoProveedor = tbaProveedor.correoContactoProveedor,
                celularContactoProveedor = tbaProveedor.celularContactoProveedor,
                creditoProveedor = tbaProveedor.creditoProveedor,
                montoCreditoProveedor = tbaProveedor.montoCreditoProveedor,
                fechaVencimientoCreditoProveedor = tbaProveedor.fechaVencimientoCreditoProveedor ?: LocalDate.now(),
                diasCreditoProveedor = tbaProveedor.diasCreditoProveedor,
                montoGarantiaProveedor = tbaProveedor.montoGarantiaProveedor,
                numeroCuentaBancariaProveedor = tbaProveedor.numCuentaBancariaProveedor,
                referenciaPagoProveedor = tbaProveedor.referenciaPagoProveedor,
                saldoAnticipoProveedor = tbaProveedor.saldoAnticipoProveedor,
                nombreChequeProveedor = tbaProveedor.nombreChequeProveedor,
                contribucionEspecialProveedor = tbaProveedor.contribEspecialProveedor,
                esTransporteProveedor = tbaProveedor.esTransporteProveedor,
                estadoProveedor = tbaProveedor.estadoProveedor,
                numeroCuentaPago = tbaProveedor.numeroCuentaPago,
                idIdentificacionFiscalPago = tbaProveedor.idIdentificacionFiscalPago,
                cuentaPasivaProveedor = null,
                cuentaGastoProveedor = if (person.idPais != null && person.idPais == 593) {
                    CuentaProveedorEnum.PROVEEDOR_NACIONAL_NO_RELACIONADO.id
                } else {
                    CuentaProveedorEnum.PROVEEDOR_EXTERIOR.id
                }
            )
        )
}
