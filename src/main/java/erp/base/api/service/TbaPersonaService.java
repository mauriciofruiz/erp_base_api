package erp.base.api.service;

import erp.base.api.constants.ErrorMessage;
import erp.base.api.dto.response.PersonaClienteResponse;
import erp.base.api.dto.response.PersonaProveedorResponse;
import erp.base.api.dto.TbaPersonaDto;
import erp.base.api.enums.PaisEnum;
import erp.base.api.enums.TaxIdentificationEnum;
import erp.base.api.models.*;
import erp.base.api.exception.CustomException;
import erp.base.api.repository.TbaPaisRepository;
import erp.base.api.repository.TbaPersonaRepository;
import erp.base.api.repository.TbaProveedorRepository;
import erp.base.api.repository.custom.TbaPersonaCustom;
import erp.base.api.utils.Paginacion;
import erp.base.api.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * Clase que maneja la lógica de negocio de las personas
 */
@Service
@RequiredArgsConstructor
public class TbaPersonaService {

    private final TbaPersonaRepository tbaPersonaRepository;
    private final TbaProveedorRepository tbaProveedorRepository;
    private final TbaPaisRepository tbaPaisRepository;
    private final TbaPersonaCustom tbaPersonaCustom;

    /**
     * Método para obtener todas las personas
     *
     * @param personDto Objeto con los datos de la persona
     * @return Objeto con los datos de la persona creada
     */
    public Mono<TbaPersona> createPerson(TbaPersonaDto personDto) {
        Mono<Boolean> isPersonExistsByIdentification = tbaPersonaRepository.findByIdentificacionPersona(personDto.getIdentificacionPersona()).hasElement();
        boolean validateCI;
        if (personDto.getIdIdentificacionFiscal().equals(TaxIdentificationEnum.CEDULA.getId())) {
            validateCI = Utils.ValidadorCedula(personDto.getIdentificacionPersona());
        } else {
            validateCI = true;
        }
        if (personDto.getIdIdentificacionFiscal().equals(TaxIdentificationEnum.RUC.getId())) {
            if (!personDto.getIdentificacionPersona().endsWith("001")) {
                return Mono.error(new CustomException(HttpStatus.BAD_REQUEST, ErrorMessage.BAD_REQUEST_RUC + personDto.getIdentificacionPersona()));
            }
        }
        return isPersonExistsByIdentification.flatMap(exists -> {
            if (exists) {
                return Mono.error(new CustomException(HttpStatus.CONFLICT, ErrorMessage.EXISTING_PERSON + personDto.getIdentificacionPersona()));
            } else if (validateCI) {
                return tbaPersonaRepository.save(
                        TbaPersona.builder()
                                .idTipoContribuyente(personDto.getIdTipoContribuyente())
                                .idPais(personDto.getIdPais())
                                .idParroquia(personDto.getIdParroquia())
                                .idEstadoCivil(personDto.getIdEstadoCivil())
                                .idGenero(personDto.getIdGenero())
                                .idIdentificacionFiscal(personDto.getIdIdentificacionFiscal())
                                .identificacionPersona(personDto.getIdentificacionPersona())
                                .nombrePersona(personDto.getNombrePersona().toUpperCase())
                                .fechaNacimientoPersona(personDto.getFechaNacimientoPersona())
                                .direccionPersona(personDto.getDireccionPersona())
                                .celularPersona(personDto.getCelularPersona())
                                .telefonoPersona(personDto.getTelefonoPersona())
                                .correoPersona(personDto.getCorreoPersona())
                                .esClientePersona(personDto.getEsClientePersona())
                                .esEmpleadoPersona(personDto.getEsEmpleadoPersona())
                                .esProveedorPersona(personDto.getEsProveedorPersona())
                                .fechaRegistroPersona(personDto.getFechaRegistroPersona())
                                .barrioDomicilioPersona(personDto.getBarrioDomicilioPersona())
                                .cantonDomicilioPersona(personDto.getCantonDomicilioPersona())
                                .parroquiaDomicilioPersona(personDto.getParroquiaDomicilioPersona())
                                .sectorDomicilioPersona(personDto.getSectorDomicilioPersona())
                                .build());
            } else {
                return Mono.error(new CustomException(HttpStatus.EXPECTATION_FAILED, ErrorMessage.CI_ERROR));
            }
        });

    }

    /**
     * Método para actualizar una persona
     *
     * @param personId  Identificador de la persona
     * @param personDto Objeto con los datos de la persona
     * @return Objeto con los datos de la persona actualizada
     */
    public Mono<TbaPersona> updatePerson(Integer personId, TbaPersonaDto personDto) {
        Mono<TbaPersona> tbaPersonaMono = tbaPersonaRepository.findById(personId).switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.PERSON_NOT_FOUND + personId)));

        return tbaPersonaMono.flatMap(tbaPersona -> {
                    tbaPersona.setIdTipoContribuyente(personDto.getIdTipoContribuyente() != null ? personDto.getIdTipoContribuyente() : tbaPersona.getIdTipoContribuyente());
                    tbaPersona.setIdPais(personDto.getIdPais() != null ? personDto.getIdPais() : tbaPersona.getIdPais());
                    tbaPersona.setIdParroquia(personDto.getIdParroquia() != null ? personDto.getIdParroquia() : tbaPersona.getIdParroquia());
                    tbaPersona.setIdEstadoCivil(personDto.getIdEstadoCivil() != null ? personDto.getIdEstadoCivil() : tbaPersona.getIdEstadoCivil());
                    tbaPersona.setIdGenero(personDto.getIdGenero() != null ? personDto.getIdGenero() : tbaPersona.getIdGenero());
                    tbaPersona.setIdIdentificacionFiscal(personDto.getIdIdentificacionFiscal() != null ? personDto.getIdIdentificacionFiscal() : tbaPersona.getIdIdentificacionFiscal());
                    tbaPersona.setIdentificacionPersona(personDto.getIdentificacionPersona() != null ? personDto.getIdentificacionPersona() : tbaPersona.getIdentificacionPersona());
                    tbaPersona.setNombrePersona(personDto.getNombrePersona() != null ? personDto.getNombrePersona().toUpperCase() : tbaPersona.getNombrePersona());
                    tbaPersona.setFechaNacimientoPersona(personDto.getFechaNacimientoPersona() != null ? personDto.getFechaNacimientoPersona() : tbaPersona.getFechaNacimientoPersona());
                    tbaPersona.setDireccionPersona(personDto.getDireccionPersona() != null ? personDto.getDireccionPersona() : tbaPersona.getDireccionPersona());
                    tbaPersona.setCelularPersona(personDto.getCelularPersona() != null ? personDto.getCelularPersona() : tbaPersona.getCelularPersona());
                    tbaPersona.setTelefonoPersona(personDto.getTelefonoPersona() != null ? personDto.getTelefonoPersona() : tbaPersona.getTelefonoPersona());
                    tbaPersona.setCorreoPersona(personDto.getCorreoPersona() != null ? personDto.getCorreoPersona() : tbaPersona.getCorreoPersona());
                    tbaPersona.setEsClientePersona(personDto.getEsClientePersona() != null ? personDto.getEsClientePersona() : tbaPersona.getEsClientePersona());
                    tbaPersona.setEsEmpleadoPersona(personDto.getEsEmpleadoPersona() != null ? personDto.getEsEmpleadoPersona() : tbaPersona.getEsEmpleadoPersona());
                    tbaPersona.setEsProveedorPersona(personDto.getEsProveedorPersona() != null ? personDto.getEsProveedorPersona() : tbaPersona.getEsProveedorPersona());
                    tbaPersona.setFechaRegistroPersona(personDto.getFechaRegistroPersona() != null ? personDto.getFechaRegistroPersona() : tbaPersona.getFechaRegistroPersona());
                    tbaPersona.setBarrioDomicilioPersona(personDto.getBarrioDomicilioPersona() != null ? personDto.getBarrioDomicilioPersona() : tbaPersona.getBarrioDomicilioPersona());
                    tbaPersona.setCantonDomicilioPersona(personDto.getCantonDomicilioPersona() != null ? personDto.getCantonDomicilioPersona() : tbaPersona.getCantonDomicilioPersona());
                    tbaPersona.setParroquiaDomicilioPersona(personDto.getParroquiaDomicilioPersona() != null ? personDto.getParroquiaDomicilioPersona() : tbaPersona.getParroquiaDomicilioPersona());
                    tbaPersona.setSectorDomicilioPersona(personDto.getSectorDomicilioPersona() != null ? personDto.getSectorDomicilioPersona() : tbaPersona.getSectorDomicilioPersona());
                    return tbaPersonaRepository.save(tbaPersona);

                }

        );
    }

    /**
     * Método para consultar todas las personas
     *
     * @param page  Número de página
     * @param limit Número de registros por página
     * @return Objeto con los datos de las personas
     */
    public Mono<Paginacion<TbaPersona>> getPersonsBySearchParams(Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page - 1, limit);

        Mono<Long> totalCount = tbaPersonaRepository.count();
        Flux<TbaPersona> personFlux = tbaPersonaRepository.findAllBy(pageable);

        return totalCount.flatMap(count -> personFlux.collectList().map(data -> new Paginacion<>(data, page, limit, count.intValue())));
    }

    /**
     * Método para consultar una persona por su identificador
     *
     * @param personId Identificador de la persona
     * @return Objeto con los datos de la persona
     */
    public Mono<TbaPersona> getPersonById(Integer personId) {
        return tbaPersonaRepository.findById(personId)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.PERSON_NOT_FOUND + personId)));
    }

    public Flux<PersonaProveedorResponse> getAllPersonProviders(String completeName, String identification) {
        return tbaPersonaRepository.getAllPersonProviders(completeName, identification).flatMap(this::buildPersonProvider);
    }

    public Flux<PersonaClienteResponse> getAllPersonClients(String completeName, String identification) {
        return tbaPersonaCustom.getClientPersonListByNameOrIdentification(completeName, identification);
    }

    public Flux<TbaPersonaDto> getAllPeople(String completeName, String identification) {
        return tbaPersonaCustom.getPersonByParams(completeName, identification);
    }

    private Mono<PersonaProveedorResponse> buildPersonProvider(TbaPersona person) {
        Mono<Proveedor> proveedorMono = tbaProveedorRepository.findByIdPersona(person.getIdPersona());

        return tbaPaisRepository.findById(person.getIdPais())
                .flatMap(pais -> proveedorMono.flatMap(proveedor -> Mono.just(PersonaProveedorResponse.builder()
                        .proveedorId(proveedor.getIdProveedor())
                        .nombreProveedor(proveedor.getNombreComercialProveedor())
                        .identificacion(person.getIdentificacionPersona())
                        .nombre(person.getNombrePersona())
                        .personaId(person.getIdPersona())
                        .direccion(person.getDireccionPersona())
                        .email(person.getCorreoPersona())
                        .telefono(person.getTelefonoPersona())
                        .idRetencionFuente(proveedor.getIdRetencionFuente())
                        .idRetencionIva(proveedor.getIdRetencionIva())
                        .diasCredito(proveedor.getDiasCreditoProveedor())
                        .idCondicionPago(proveedor.getIdCondicionPago())
                        .cuentaGastoProveedor(proveedor.getCuentaGastoProveedor())
                        .esExtranjero(!Objects.equals(pais.getCodigoPais(), PaisEnum.ECUADOR.getId()))
                        .build())
                ));
    }

}
