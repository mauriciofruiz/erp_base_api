package erp.base.api.service

import erp.base.api.constants.ErrorMessage
import erp.base.api.dto.PersonaDto
import erp.base.api.dto.response.PersonaClienteResponse
import erp.base.api.dto.response.PersonaProveedorResponse
import erp.base.api.enums.PaisEnum
import erp.base.api.enums.TaxIdentificationEnum
import erp.base.api.exception.CustomException
import erp.base.api.models.Persona
import erp.base.api.models.Proveedor
import erp.base.api.repository.PaisRepository
import erp.base.api.repository.PersonaRepository
import erp.base.api.repository.ProveedorRepository
import erp.base.api.repository.custom.PersonaCustom
import erp.base.api.utils.Paginacion
import erp.base.api.utils.Utils
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class PersonaService(
    private val tbaPersonaRepository: PersonaRepository,
    private val tbaProveedorRepository: ProveedorRepository,
    private val tbaPaisRepository: PaisRepository,
    private val tbaPersonaCustom: PersonaCustom
) {

    fun createPerson(personDto: PersonaDto): Mono<Persona> {
        val identificacion = personDto.identificacionPersona ?: return Mono.error(CustomException(HttpStatus.BAD_REQUEST, "Identificación requerida"))
        val isPersonExistsByIdentification = tbaPersonaRepository.findByIdentificacionPersona(identificacion).hasElement()
        val validateCI = when (personDto.idIdentificacionFiscal) {
            TaxIdentificationEnum.CEDULA.id -> Utils.ValidadorCedula(identificacion)
            else -> true
        }
        if (personDto.idIdentificacionFiscal == TaxIdentificationEnum.RUC.id) {
            if (!identificacion.endsWith("001")) {
                return Mono.error(CustomException(HttpStatus.BAD_REQUEST, ErrorMessage.BAD_REQUEST_RUC + identificacion))
            }
        }
        return isPersonExistsByIdentification.flatMap { exists ->
            when {
                exists -> Mono.error(CustomException(HttpStatus.CONFLICT, ErrorMessage.EXISTING_PERSON + identificacion))
                validateCI -> tbaPersonaRepository.save(
                    Persona(
                        idPersona = null,
                        idTipoContribuyente = personDto.idTipoContribuyente,
                        idPais = personDto.idPais,
                        idParroquia = personDto.idParroquia,
                        idEstadoCivil = personDto.idEstadoCivil,
                        idGenero = personDto.idGenero,
                        idIdentificacionFiscal = personDto.idIdentificacionFiscal,
                        identificacionPersona = personDto.identificacionPersona,
                        nombrePersona = personDto.nombrePersona?.uppercase(),
                        fechaNacimientoPersona = personDto.fechaNacimientoPersona,
                        direccionPersona = personDto.direccionPersona,
                        celularPersona = personDto.celularPersona,
                        telefonoPersona = personDto.telefonoPersona,
                        correoPersona = personDto.correoPersona,
                        esClientePersona = personDto.esClientePersona,
                        esEmpleadoPersona = personDto.esEmpleadoPersona,
                        esProveedorPersona = personDto.esProveedorPersona,
                        fechaRegistroPersona = personDto.fechaRegistroPersona,
                        barrioDomicilioPersona = personDto.barrioDomicilioPersona,
                        cantonDomicilioPersona = personDto.cantonDomicilioPersona,
                        parroquiaDomicilioPersona = personDto.parroquiaDomicilioPersona,
                        sectorDomicilioPersona = personDto.sectorDomicilioPersona,
                        fechaCreacion = null,
                        fechaModificacion = null,
                        idUsuarioCreacion = null,
                        idUsuarioModificacion = null
                    )
                )
                else -> Mono.error(CustomException(HttpStatus.EXPECTATION_FAILED, ErrorMessage.CI_ERROR))
            }
        }
    }

    fun updatePerson(personId: Int, personDto: PersonaDto): Mono<Persona> {
        val tbaPersonaMono = tbaPersonaRepository.findById(personId)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.PERSON_NOT_FOUND + personId)))
        return tbaPersonaMono.flatMap { tbaPersona ->
            tbaPersona.idTipoContribuyente = personDto.idTipoContribuyente ?: tbaPersona.idTipoContribuyente
            tbaPersona.idPais = personDto.idPais ?: tbaPersona.idPais
            tbaPersona.idParroquia = personDto.idParroquia ?: tbaPersona.idParroquia
            tbaPersona.idEstadoCivil = personDto.idEstadoCivil ?: tbaPersona.idEstadoCivil
            tbaPersona.idGenero = personDto.idGenero ?: tbaPersona.idGenero
            tbaPersona.idIdentificacionFiscal = personDto.idIdentificacionFiscal ?: tbaPersona.idIdentificacionFiscal
            tbaPersona.identificacionPersona = personDto.identificacionPersona ?: tbaPersona.identificacionPersona
            tbaPersona.nombrePersona = personDto.nombrePersona?.uppercase() ?: tbaPersona.nombrePersona
            tbaPersona.fechaNacimientoPersona = personDto.fechaNacimientoPersona ?: tbaPersona.fechaNacimientoPersona
            tbaPersona.direccionPersona = personDto.direccionPersona ?: tbaPersona.direccionPersona
            tbaPersona.celularPersona = personDto.celularPersona ?: tbaPersona.celularPersona
            tbaPersona.telefonoPersona = personDto.telefonoPersona ?: tbaPersona.telefonoPersona
            tbaPersona.correoPersona = personDto.correoPersona ?: tbaPersona.correoPersona
            tbaPersona.esClientePersona = personDto.esClientePersona ?: tbaPersona.esClientePersona
            tbaPersona.esEmpleadoPersona = personDto.esEmpleadoPersona ?: tbaPersona.esEmpleadoPersona
            tbaPersona.esProveedorPersona = personDto.esProveedorPersona ?: tbaPersona.esProveedorPersona
            tbaPersona.fechaRegistroPersona = personDto.fechaRegistroPersona ?: tbaPersona.fechaRegistroPersona
            tbaPersona.barrioDomicilioPersona = personDto.barrioDomicilioPersona ?: tbaPersona.barrioDomicilioPersona
            tbaPersona.cantonDomicilioPersona = personDto.cantonDomicilioPersona ?: tbaPersona.cantonDomicilioPersona
            tbaPersona.parroquiaDomicilioPersona = personDto.parroquiaDomicilioPersona ?: tbaPersona.parroquiaDomicilioPersona
            tbaPersona.sectorDomicilioPersona = personDto.sectorDomicilioPersona ?: tbaPersona.sectorDomicilioPersona
            tbaPersonaRepository.save(tbaPersona)
        }
    }

    fun getPersonsBySearchParams(page: Int, limit: Int): Mono<Paginacion<Persona>> {
        val pageable: Pageable = PageRequest.of(page - 1, limit)
        val totalCount = tbaPersonaRepository.count()
        val personFlux = tbaPersonaRepository.findAllBy(pageable)
        return totalCount.flatMap { count ->
            personFlux.collectList().map { data -> Paginacion(data, page, limit, count.toInt()) }
        }
    }

    fun getPersonById(personId: Int): Mono<Persona> =
        tbaPersonaRepository.findById(personId)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.PERSON_NOT_FOUND + personId)))

    fun getAllPersonProviders(completeName: String?, identification: String?): Flux<PersonaProveedorResponse> =
        tbaPersonaRepository.getAllPersonProviders(completeName, identification).flatMap { buildPersonProvider(it) }

    fun getAllPersonClients(completeName: String?, identification: String?): Flux<PersonaClienteResponse> =
        tbaPersonaCustom.getClientPersonListByNameOrIdentification(completeName, identification)

    fun getAllPeople(completeName: String?, identification: String?): Flux<PersonaDto> =
        tbaPersonaCustom.getPersonByParams(completeName, identification)

    private fun buildPersonProvider(person: Persona): Mono<PersonaProveedorResponse> {
        val proveedorMono = tbaProveedorRepository.findByIdPersona(person.idPersona!!)
        return tbaPaisRepository.findById(person.idPais!!)
            .flatMap { pais ->
                proveedorMono.flatMap { proveedor ->
                    val dto = PersonaProveedorResponse().apply {
                        proveedorId = proveedor.idProveedor
                        nombreProveedor = proveedor.nombreComercialProveedor
                        identificacion = person.identificacionPersona
                        nombre = person.nombrePersona
                        personaId = person.idPersona
                        direccion = person.direccionPersona
                        email = person.correoPersona
                        telefono = person.telefonoPersona
                        idRetencionFuente = proveedor.idRetencionFuente
                        idRetencionIva = proveedor.idRetencionIva
                        diasCredito = proveedor.diasCreditoProveedor
                        idCondicionPago = proveedor.idCondicionPago
                        cuentaGastoProveedor = proveedor.cuentaGastoProveedor
                        esExtranjero = pais.codigoPais != PaisEnum.ECUADOR.id
                    }
                    Mono.just(dto)
                }
            }
    }
}
