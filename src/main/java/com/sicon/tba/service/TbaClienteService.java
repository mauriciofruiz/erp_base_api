package com.sicon.tba.service;

import com.sicon.tba.constants.ErrorMessage;
import com.sicon.tba.controller.VentasController;
import com.sicon.tba.dto.ExcepcionesClienteDescDto;
import com.sicon.tba.dto.PersonaClienteDto;
import com.sicon.tba.dto.TbaClienteDto;
import com.sicon.tba.dto.TbaPersonaDto;
import com.sicon.tba.dto.response.EstadoCuentoPagarDto;
import com.sicon.tba.dto.response.PersonClientResponse;
import com.sicon.tba.dto.response.PersonaClienteCreditoDto;
import com.sicon.tba.dto.response.PersonaClienteResponse;
import com.sicon.tba.enums.CuentasEnum;
import com.sicon.tba.enums.OrigenIngresoEnum;
import com.sicon.tba.enums.TaxIdentificationEnum;
import com.sicon.tba.enums.TipoFiscalEnum;
import com.sicon.tba.models.*;
import com.sicon.tba.exception.CustomException;
import com.sicon.tba.repository.*;
import com.sicon.tba.utils.Paginacion;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TbaClienteService {

    private final TbaClienteRepository tbaClienteRepository;
    private final TbaPersonaRepository tbaPersonaRepository;
    private final TbaPersonaService tbaPersonaService;
    private final TbaCreditoClienteService tbaCreditoClienteService;
    private final TbaCategoriaClienteService tbaCategoriaClienteService;
    private final TbaCreditoClienteRepository tbaCreditoClienteRepository;
    private final TbaCiudadRepository tbaCiudadRepository;
    private final TbaParroquiaRepository tbaParroquiaRepository;
    private final ExcepcionesClienteDescuentoService excepcionesClienteDescuentoService;
    private final VentasController ventasController;
    private final SesionService sesionService;

    /**
     * Método que crea un cliente
     *
     * @param personaClienteCreditoDto objeto de tipo TbaClienteDto
     * @return objeto de tipo TbaCliente
     */
    @Transactional
    public Mono<PersonClientResponse> createCustomer(PersonaClienteCreditoDto personaClienteCreditoDto) {
        PersonaClienteDto personaClienteDto = convertPersonaClienteCreditoToPersonaCliente(personaClienteCreditoDto);
        personaClienteDto.cliente().setIdCuenta(personaClienteDto.cliente().getIdCuenta() == null ? CuentasEnum.CLIENTES_NACIONALES_NO_RELACIONADOS.getIdCuenta() : personaClienteDto.cliente().getIdCuenta());
        personaClienteDto.persona().setEsClientePersona(true);
        personaClienteDto.persona().setIdIdentificacionFiscal(TaxIdentificationEnum.getIdByName(TipoFiscalEnum.getTipoIdentificacionById(personaClienteDto.cliente().getIdTipoIdFiscal())));
        return tbaPersonaRepository.findByIdentificacionPersona(personaClienteDto.persona().getIdentificacionPersona())
                .flatMap(person -> createClientWhenExistsPerson(person, personaClienteDto))
                .switchIfEmpty(createClientWhenNotExistsPerson(personaClienteDto));
    }

    private Mono<PersonClientResponse> createClientWhenExistsPerson(TbaPersona person, PersonaClienteDto personaClienteDto) {
        return person.getEsClientePersona() ? Mono.error(new CustomException(HttpStatus.CONFLICT, ErrorMessage.PERSON_ALREADY_ASSOCIATED_WITH_CLIENT))
                : tbaPersonaService.updatePerson(person.getIdPersona(), personaClienteDto.persona())
                .flatMap(person1 -> buildAndSaveClient(person.getIdPersona(), personaClienteDto.cliente())
                        .flatMap(client -> {
                                    personaClienteDto.credito().setIdCliente(client.getIdCliente());
                                    return tbaCreditoClienteService.saveCredit(personaClienteDto.credito())
                                            .flatMap(creditoCliente -> excepcionesClienteDescuentoService.saveUpdateAllDto(Flux.fromIterable(personaClienteDto.excepciones()), Long.valueOf(client.getIdCliente()))
                                                    .collectList()
                                                    .flatMap(excepciones -> buildPersonClientResponse(person1, client, creditoCliente, excepciones)));
                                }
                        )
                );
    }

    private Mono<PersonClientResponse> createClientWhenNotExistsPerson(PersonaClienteDto personaClienteDto) {
        return tbaPersonaService.createPerson(personaClienteDto.persona())
                .flatMap(persona -> buildAndSaveClient(persona.getIdPersona(), personaClienteDto.cliente())
                        .flatMap(client -> {
                            personaClienteDto.credito().setIdCliente(client.getIdCliente());
                            return tbaCreditoClienteService.saveCredit(personaClienteDto.credito())
                                    .flatMap(creditoCliente -> excepcionesClienteDescuentoService.saveUpdateAllDto(Flux.fromIterable(personaClienteDto.excepciones()), Long.valueOf(client.getIdCliente()))
                                            .collectList()
                                            .flatMap(excepciones -> buildPersonClientResponse(persona, client, creditoCliente, excepciones)));
                        }));
    }

    private Mono<PersonClientResponse> buildPersonClientResponse(TbaPersona persona, TbaCliente cliente, TbaCreditoCliente creditoCliente, List<ExcepcionesClienteDescDto> excepciones) {
        return Mono.just(PersonClientResponse.builder()
                .persona(persona)
                .cliente(cliente)
                .creditoCliente(creditoCliente)
                .excepciones(excepciones)
                .build());
    }

    /**
     * Método para actualizar un cliente
     *
     * @param id                       ID del cliente
     * @param personaClienteCreditoDto Objeto con los datos a cambiar
     */
    public Mono<TbaCliente> updateClient(Integer id, PersonaClienteCreditoDto personaClienteCreditoDto) {
        PersonaClienteDto personaClienteDto = convertPersonaClienteCreditoToPersonaCliente(personaClienteCreditoDto);
        Mono<TbaCliente> clienteMono = tbaClienteRepository.findById(id).switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.CLIENT_NOT_FOUND + id)));
        Mono<TbaPersona> personaMono = clienteMono.flatMap(cliente -> tbaPersonaRepository.findById(cliente.getIdPersona()).switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.PERSON_NOT_EXISTS + cliente.getIdPersona()))));
        Mono<TbaCreditoCliente> creditoClienteMono = tbaCreditoClienteService.getCreditByClientId(id).switchIfEmpty(Mono.just(new TbaCreditoCliente()));
        return Mono.zip(personaMono, clienteMono, creditoClienteMono).flatMap(tuple ->
                {
                    TbaPersona persona = tuple.getT1();
                    TbaCliente cliente = tuple.getT2();
                    TbaCreditoCliente creditoCliente = tuple.getT3();
                    return buildAndUpdateClient(cliente, personaClienteDto.cliente()).flatMap(cliente1 ->
                            tbaPersonaService.updatePerson(persona.getIdPersona(), personaClienteDto.persona()).flatMap(
                                            persona1 -> {
                                                if (creditoCliente.getIdCreditoCliente() == null) {
                                                    creditoCliente.setIdCliente(cliente1.getIdCliente());
                                                }
                                                creditoCliente.setCupoAutorizado(personaClienteDto.credito().getCupoAutorizado());
                                                creditoCliente.setCupoDisponible(personaClienteDto.credito().getCupoDisponible());
                                                creditoCliente.setCupoUtilizado(personaClienteDto.credito().getCupoUtilizado());
                                                creditoCliente.setMontoGarantia(personaClienteDto.credito().getMontoGarantia());
                                                creditoCliente.setFechaGarantia(personaClienteDto.credito().getFechaGarantia());
                                                creditoCliente.setDiasCredito(personaClienteDto.credito().getDiasCredito());
                                                creditoCliente.setObservacion(personaClienteDto.credito().getObservacion());
                                                creditoCliente.setSobreCupo(personaClienteDto.credito().getSobreCupo());
                                                creditoCliente.setFechaVencimientoSobreCupo(personaClienteDto.credito().getFechaVencimientoSobreCupo());
                                                return tbaCreditoClienteRepository.save(creditoCliente)
                                                        .flatMap(creditoCliente1 -> Mono.just(cliente1));
                                            }
                                    )
                                    .flatMap(entity -> excepcionesClienteDescuentoService.saveUpdateAllDto(Flux.fromIterable(personaClienteDto.excepciones()), Long.valueOf(id)).collectList().flatMap(list->
                                                    excepcionesClienteDescuentoService.getExceptionsByClientId(Long.valueOf(id)).collectList()
                                                            .flatMap(exceptionsListBDD -> excepcionesClienteDescuentoService.deleteRemovedDetails(exceptionsListBDD, list))
                                            )
                                    )
                                    .then(Mono.just(cliente1)));
                }
        );
    }

    private Mono<TbaCliente> buildAndUpdateClient(TbaCliente clientToUpdate, TbaClienteDto tbaClienteDto) {
        clientToUpdate.setIdCategoriaCliente(tbaClienteDto.getIdCategoriaCliente());
        clientToUpdate.setIdOrigenIngreso(tbaClienteDto.getIdOrigenIngreso());
        clientToUpdate.setIdTipoIdFiscal(tbaClienteDto.getIdTipoIdFiscal());
        clientToUpdate.setIdCuenta(tbaClienteDto.getIdCuenta());
        clientToUpdate.setNombreComercialCliente(tbaClienteDto.getNombreComercialCliente());
        clientToUpdate.setDireccion2Cliente(tbaClienteDto.getDireccion2Cliente());
        clientToUpdate.setCorreo2Cliente(tbaClienteDto.getCorreo2Cliente());
        clientToUpdate.setCelular2Cliente(tbaClienteDto.getCelular2Cliente());
        clientToUpdate.setTelefono2Cliente(tbaClienteDto.getTelefono2Cliente());
        clientToUpdate.setSaldoAFavorCliente(tbaClienteDto.getSaldoAFavorCliente());
        clientToUpdate.setContactoCliente(tbaClienteDto.getContactoCliente());
        clientToUpdate.setCargoContactoCliente(tbaClienteDto.getCargoContactoCliente());
        clientToUpdate.setTelefonoContactoCliente(tbaClienteDto.getTelefonoContactoCliente());
        clientToUpdate.setCorreoContactoCliente(tbaClienteDto.getCorreoContactoCliente());
        clientToUpdate.setCelularContactoCliente(tbaClienteDto.getCelularContactoCliente());
        clientToUpdate.setDescuentoEspecialCliente(tbaClienteDto.getDescuentoEspecialCliente().divide(new BigDecimal(100)));
        clientToUpdate.setFechaCaducidadDescCliente(tbaClienteDto.getFechaCaducidadDescCliente());
        clientToUpdate.setObsDescCliente(tbaClienteDto.getObsDescCliente());
        clientToUpdate.setAlertaGeneral(tbaClienteDto.getAlertaGeneral());
        clientToUpdate.setAlertaFacturacion(tbaClienteDto.getAlertaFacturacion());
        clientToUpdate.setAlertaCobro(tbaClienteDto.getAlertaCobro());
        clientToUpdate.setCodDesbloqueoCliente(tbaClienteDto.getCodDesbloqueoCliente());
        clientToUpdate.setNumCambioCliente(tbaClienteDto.getNumCambioCliente());
        clientToUpdate.setMotivoInactivaCliente(tbaClienteDto.getMotivoInactivaCliente());
        clientToUpdate.setEstadoCliente(tbaClienteDto.getEstadoCliente());
        clientToUpdate.setIdUsuarioVentas(tbaClienteDto.getIdUsuarioVentas());
        clientToUpdate.setFacContadoCliente(tbaClienteDto.getFacContadoCliente());
        clientToUpdate.setCategoriaEspecialCliente(tbaClienteDto.getCategoriaEspecialCliente());
        clientToUpdate.setPoliticaCliente(tbaClienteDto.getPoliticaCliente());
        clientToUpdate.setDescuentoCliente(tbaClienteDto.getDescuentoCliente().divide(new BigDecimal(100)));
        clientToUpdate.setIdRetencionIva(tbaClienteDto.getIdRetencionIva());
        clientToUpdate.setIdRetencionFuente(tbaClienteDto.getIdRetencionFuente());
        clientToUpdate.setPlaca(tbaClienteDto.getPlaca());
        return tbaClienteRepository.save(clientToUpdate);
    }


    private PersonaClienteDto convertPersonaClienteCreditoToPersonaCliente(PersonaClienteCreditoDto personaClienteCreditoDto) {
        return new PersonaClienteDto(getTbaPersonaDto(personaClienteCreditoDto), getTbaClienteDto(personaClienteCreditoDto), getTbaCreditoCliente(personaClienteCreditoDto), personaClienteCreditoDto.getExcepciones());
    }

    private static TbaPersonaDto getTbaPersonaDto(PersonaClienteCreditoDto personaClienteCreditoDto) {
        TbaPersonaDto persona = new TbaPersonaDto();
        persona.setIdPersona(personaClienteCreditoDto.getIdPersona());
        persona.setIdTipoContribuyente(personaClienteCreditoDto.getIdTipoContribuyente());
        persona.setIdPais(personaClienteCreditoDto.getIdPais());
        persona.setIdParroquia(personaClienteCreditoDto.getIdParroquia());
        persona.setIdEstadoCivil(personaClienteCreditoDto.getIdEstadoCivil());
        persona.setIdGenero(personaClienteCreditoDto.getIdGenero());
        persona.setIdIdentificacionFiscal(personaClienteCreditoDto.getIdIdentificacionFiscal());
        persona.setIdentificacionPersona(personaClienteCreditoDto.getIdentificacionPersona());
        persona.setNombrePersona(personaClienteCreditoDto.getNombrePersona());
        persona.setFechaNacimientoPersona(personaClienteCreditoDto.getFechaNacimientoPersona());
        persona.setDireccionPersona(personaClienteCreditoDto.getDireccionPersona());
        persona.setCelularPersona(personaClienteCreditoDto.getCelularPersona());
        persona.setTelefonoPersona(personaClienteCreditoDto.getTelefonoPersona());
        persona.setCorreoPersona(personaClienteCreditoDto.getCorreoPersona());
        persona.setEsClientePersona(personaClienteCreditoDto.getEsClientePersona());
        persona.setEsEmpleadoPersona(personaClienteCreditoDto.getEsEmpleadoPersona());
        persona.setEsProveedorPersona(personaClienteCreditoDto.getEsProveedorPersona());
        persona.setFechaRegistroPersona(personaClienteCreditoDto.getFechaRegistroPersona());
        persona.setBarrioDomicilioPersona(personaClienteCreditoDto.getBarrioDomicilioPersona());
        persona.setCantonDomicilioPersona(personaClienteCreditoDto.getCantonDomicilioPersona());
        persona.setParroquiaDomicilioPersona(personaClienteCreditoDto.getParroquiaDomicilioPersona());
        persona.setSectorDomicilioPersona(personaClienteCreditoDto.getSectorDomicilioPersona());
        return persona;
    }


    private static TbaCreditoCliente getTbaCreditoCliente(PersonaClienteCreditoDto personaClienteCreditoDto) {
        TbaCreditoCliente credito = new TbaCreditoCliente();
        credito.setIdCreditoCliente(personaClienteCreditoDto.getIdCreditoCliente());
        credito.setIdCliente(personaClienteCreditoDto.getIdCliente());
        credito.setCupoAutorizado(personaClienteCreditoDto.getCupoAutorizado());
        credito.setCupoDisponible(personaClienteCreditoDto.getCupoDisponible());
        credito.setCupoUtilizado(personaClienteCreditoDto.getCupoUtilizado());
        credito.setMontoGarantia(personaClienteCreditoDto.getMontoGarantia());
        credito.setFechaGarantia(personaClienteCreditoDto.getFechaGarantia());
        credito.setDiasCredito(personaClienteCreditoDto.getDiasCredito());
        credito.setObservacion(personaClienteCreditoDto.getObservacion());
        credito.setSobreCupo(personaClienteCreditoDto.getSobreCupo());
        credito.setFechaVencimientoSobreCupo(personaClienteCreditoDto.getFechaVencimientoSobreCupo());
        return credito;
    }

    private static TbaClienteDto getTbaClienteDto(PersonaClienteCreditoDto personaClienteCreditoDto) {
        TbaClienteDto cliente = new TbaClienteDto();
        cliente.setIdCategoriaCliente(personaClienteCreditoDto.getIdCategoriaCliente());
        cliente.setIdOrigenIngreso(personaClienteCreditoDto.getIdOrigenIngreso());
        cliente.setIdTipoIdFiscal(personaClienteCreditoDto.getIdTipoIdFiscal());
        cliente.setIdPersona(personaClienteCreditoDto.getIdPersona());
        cliente.setIdCuenta(personaClienteCreditoDto.getIdCuenta());
        cliente.setNombreComercialCliente(personaClienteCreditoDto.getNombreComercialCliente());
        cliente.setDireccion2Cliente(personaClienteCreditoDto.getDireccion2Cliente());
        cliente.setCorreo2Cliente(personaClienteCreditoDto.getCorreo2Cliente());
        cliente.setCelular2Cliente(personaClienteCreditoDto.getCelular2Cliente());
        cliente.setTelefono2Cliente(personaClienteCreditoDto.getTelefono2Cliente());
        cliente.setSaldoAFavorCliente(personaClienteCreditoDto.getSaldoAFavorCliente());
        cliente.setContactoCliente(personaClienteCreditoDto.getContactoCliente());
        cliente.setCargoContactoCliente(personaClienteCreditoDto.getCargoContactoCliente());
        cliente.setTelefonoContactoCliente(personaClienteCreditoDto.getTelefonoContactoCliente());
        cliente.setCorreoContactoCliente(personaClienteCreditoDto.getCorreoContactoCliente());
        cliente.setCelularContactoCliente(personaClienteCreditoDto.getCelularContactoCliente());
        cliente.setDescuentoEspecialCliente(personaClienteCreditoDto.getDescuentoEspecialCliente());
        cliente.setFechaCaducidadDescCliente(personaClienteCreditoDto.getFechaCaducidadDescCliente());
        cliente.setObsDescCliente(personaClienteCreditoDto.getObsDescCliente());
        cliente.setAlertaGeneral(personaClienteCreditoDto.getAlertaGeneral());
        cliente.setAlertaFacturacion(personaClienteCreditoDto.getAlertaFacturacion());
        cliente.setAlertaCobro(personaClienteCreditoDto.getAlertaCobro());
        cliente.setCodDesbloqueoCliente(personaClienteCreditoDto.getCodDesbloqueoCliente());
        cliente.setNumCambioCliente(personaClienteCreditoDto.getNumCambioCliente());
        cliente.setMotivoInactivaCliente(personaClienteCreditoDto.getMotivoInactivaCliente());
        cliente.setEstadoCliente(personaClienteCreditoDto.getEstadoCliente());
        cliente.setIdUsuarioVentas(personaClienteCreditoDto.getIdUsuarioVentas());
        cliente.setFacContadoCliente(personaClienteCreditoDto.getFacContadoCliente());
        cliente.setCategoriaEspecialCliente(personaClienteCreditoDto.getCategoriaEspecialCliente());
        cliente.setPoliticaCliente(personaClienteCreditoDto.getPoliticaCliente());
        cliente.setDescuentoCliente(personaClienteCreditoDto.getDescuentoCliente());
        cliente.setIdRetencionIva(personaClienteCreditoDto.getIdRetencionIva());
        cliente.setIdRetencionFuente(personaClienteCreditoDto.getIdRetencionFuente());
        cliente.setPlaca(personaClienteCreditoDto.getPlaca());
        return cliente;
    }

    private Mono<TbaCliente> buildAndSaveClient(Integer personId, TbaClienteDto tbaClienteDto) {
        return tbaClienteRepository.save(
                TbaCliente.builder()
                        .idCategoriaCliente(tbaClienteDto.getIdCategoriaCliente())
                        .idOrigenIngreso(tbaClienteDto.getIdOrigenIngreso() == null ? OrigenIngresoEnum.EMPLEADO_PRIVADO.getId() : tbaClienteDto.getIdOrigenIngreso())
                        .idTipoIdFiscal(tbaClienteDto.getIdTipoIdFiscal())
                        .idPersona(personId)
                        .idCuenta(tbaClienteDto.getIdCuenta() == null ? CuentasEnum.CLIENTES_NACIONALES_NO_RELACIONADOS.getIdCuenta() : tbaClienteDto.getIdCuenta())
                        .nombreComercialCliente(tbaClienteDto.getNombreComercialCliente())
                        .direccion2Cliente(tbaClienteDto.getDireccion2Cliente())
                        .correo2Cliente(tbaClienteDto.getCorreo2Cliente())
                        .celular2Cliente(tbaClienteDto.getCelular2Cliente())
                        .telefono2Cliente(tbaClienteDto.getTelefono2Cliente())
                        .saldoAFavorCliente(tbaClienteDto.getSaldoAFavorCliente())
                        .contactoCliente(tbaClienteDto.getContactoCliente())
                        .cargoContactoCliente(tbaClienteDto.getCargoContactoCliente())
                        .telefonoContactoCliente(tbaClienteDto.getTelefonoContactoCliente())
                        .correoContactoCliente(tbaClienteDto.getCorreoContactoCliente())
                        .celularContactoCliente(tbaClienteDto.getCelularContactoCliente())
                        .descuentoEspecialCliente(tbaClienteDto.getDescuentoEspecialCliente().divide(new BigDecimal(100)))
                        .fechaCaducidadDescCliente(tbaClienteDto.getFechaCaducidadDescCliente())
                        .obsDescCliente(tbaClienteDto.getObsDescCliente())
                        .alertaGeneral(tbaClienteDto.getAlertaGeneral())
                        .alertaFacturacion(tbaClienteDto.getAlertaFacturacion())
                        .alertaCobro(tbaClienteDto.getAlertaCobro())
                        .codDesbloqueoCliente(tbaClienteDto.getCodDesbloqueoCliente())
                        .numCambioCliente(tbaClienteDto.getNumCambioCliente())
                        .motivoInactivaCliente(tbaClienteDto.getMotivoInactivaCliente())
                        .estadoCliente(tbaClienteDto.getEstadoCliente())
                        .idUsuarioVentas(tbaClienteDto.getIdUsuarioVentas())
                        .facContadoCliente(tbaClienteDto.getFacContadoCliente())
                        .categoriaEspecialCliente(tbaClienteDto.getCategoriaEspecialCliente())
                        .politicaCliente(tbaClienteDto.getPoliticaCliente())
                        .descuentoCliente(tbaClienteDto.getDescuentoCliente().divide(new BigDecimal(100), 2, RoundingMode.HALF_UP))
                        .idRetencionIva(tbaClienteDto.getIdRetencionIva())
                        .idRetencionFuente(tbaClienteDto.getIdRetencionFuente())
                        .placa(tbaClienteDto.getPlaca())
                        .build());
    }


    /**
     * Método para encontrar un cliente por su id
     *
     * @param id id del cliente a eliminar
     * @return objeto de tipo TbaCliente
     */
    public Mono<PersonaClienteCreditoDto> findClientById(Integer id, Integer userId) {
        Mono<TbaCliente> clienteMono = tbaClienteRepository.findById(id).switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.CLIENT_NOT_FOUND + id)));
        var personaMono = clienteMono.flatMap(cliente -> tbaPersonaRepository.findById(cliente.getIdPersona()).switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.PERSON_NOT_EXISTS + cliente.getIdPersona()))));
        var creditoClienteMono = tbaCreditoClienteService.getCreditByClientId(id).switchIfEmpty(Mono.just(new TbaCreditoCliente()));
        var parroquiaMono = personaMono.flatMap(persona -> tbaParroquiaRepository.findById(persona.getIdParroquia()).switchIfEmpty(Mono.just(new TbaParroquia())));
        var ciudadMono = parroquiaMono.flatMap(parroquia -> tbaCiudadRepository.findById(parroquia.getIdCiudad()).switchIfEmpty(Mono.just(new TbaCiudad())));
        var excepcionesMono = excepcionesClienteDescuentoService.getAllDtoByIdCliente(Long.valueOf(id)).collectList();
        Mono<EstadoCuentoPagarDto> estadoCuentaPagarMono = getEstadoCuentaController(userId, id);
        return Mono.zip(clienteMono, personaMono, creditoClienteMono, parroquiaMono, ciudadMono, excepcionesMono, estadoCuentaPagarMono).flatMap(
                tuple -> Mono.just(PersonaClienteCreditoDto.builder()
                        .idPersona(tuple.getT2().getIdPersona())
                        .idTipoContribuyente(tuple.getT2().getIdTipoContribuyente())
                        .idPais(tuple.getT2().getIdPais())
                        .idParroquia(tuple.getT2().getIdParroquia())
                        .idCiudad(tuple.getT5().getIdCiudad())
                        .idProvincia(tuple.getT5().getIdProvincia())
                        .idEstadoCivil(tuple.getT2().getIdEstadoCivil())
                        .idGenero(tuple.getT2().getIdGenero())
                        .idIdentificacionFiscal(tuple.getT2().getIdIdentificacionFiscal())
                        .identificacionPersona(tuple.getT2().getIdentificacionPersona())
                        .nombrePersona(tuple.getT2().getNombrePersona())
                        .fechaNacimientoPersona(tuple.getT2().getFechaNacimientoPersona())
                        .direccionPersona(tuple.getT2().getDireccionPersona())
                        .celularPersona(tuple.getT2().getCelularPersona())
                        .telefonoPersona(tuple.getT2().getTelefonoPersona())
                        .correoPersona(tuple.getT2().getCorreoPersona())
                        .esClientePersona(tuple.getT2().getEsClientePersona())
                        .esEmpleadoPersona(tuple.getT2().getEsEmpleadoPersona())
                        .esProveedorPersona(tuple.getT2().getEsProveedorPersona())
                        .fechaRegistroPersona(tuple.getT2().getFechaRegistroPersona())
                        .barrioDomicilioPersona(tuple.getT2().getBarrioDomicilioPersona())
                        .cantonDomicilioPersona(tuple.getT2().getCantonDomicilioPersona())
                        .parroquiaDomicilioPersona(tuple.getT2().getParroquiaDomicilioPersona())
                        .sectorDomicilioPersona(tuple.getT2().getSectorDomicilioPersona())
                        .idCliente(tuple.getT1().getIdCliente())
                        .idCategoriaCliente(tuple.getT1().getIdCategoriaCliente())
                        .idOrigenIngreso(tuple.getT1().getIdOrigenIngreso())
                        .idTipoIdFiscal(tuple.getT1().getIdTipoIdFiscal())
                        .idPersona(tuple.getT1().getIdPersona())
                        .idCuenta(tuple.getT1().getIdCuenta())
                        .nombreComercialCliente(tuple.getT1().getNombreComercialCliente())
                        .direccion2Cliente(tuple.getT1().getDireccion2Cliente())
                        .correo2Cliente(tuple.getT1().getCorreo2Cliente())
                        .celular2Cliente(tuple.getT1().getCelular2Cliente())
                        .telefono2Cliente(tuple.getT1().getTelefono2Cliente())
                        .saldoAFavorCliente(tuple.getT1().getSaldoAFavorCliente())
                        .contactoCliente(tuple.getT1().getContactoCliente())
                        .cargoContactoCliente(tuple.getT1().getCargoContactoCliente())
                        .telefonoContactoCliente(tuple.getT1().getTelefonoContactoCliente())
                        .correoContactoCliente(tuple.getT1().getCorreoContactoCliente())
                        .celularContactoCliente(tuple.getT1().getCelularContactoCliente())
                        .descuentoEspecialCliente(tuple.getT1().getDescuentoEspecialCliente().multiply(new BigDecimal(100)))
                        .fechaCaducidadDescCliente(tuple.getT1().getFechaCaducidadDescCliente())
                        .obsDescCliente(tuple.getT1().getObsDescCliente())
                        .alertaGeneral(tuple.getT1().getAlertaGeneral())
                        .alertaFacturacion(tuple.getT1().getAlertaFacturacion())
                        .alertaCobro(tuple.getT1().getAlertaCobro())
                        .codDesbloqueoCliente(tuple.getT1().getCodDesbloqueoCliente())
                        .numCambioCliente(tuple.getT1().getNumCambioCliente())
                        .motivoInactivaCliente(tuple.getT1().getMotivoInactivaCliente())
                        .estadoCliente(tuple.getT1().getEstadoCliente())
                        .idUsuarioVentas(tuple.getT1().getIdUsuarioVentas())
                        .facContadoCliente(tuple.getT1().getFacContadoCliente())
                        .categoriaEspecialCliente(tuple.getT1().getCategoriaEspecialCliente())
                        .politicaCliente(tuple.getT1().getPoliticaCliente())
                        .descuentoCliente(tuple.getT1().getDescuentoCliente().multiply(new BigDecimal(100)))
                        .idRetencionIva(tuple.getT1().getIdRetencionIva())
                        .idRetencionFuente(tuple.getT1().getIdRetencionFuente())
                        .idCreditoCliente(tuple.getT3().getIdCreditoCliente())
                        .cupoAutorizado(tuple.getT3().getCupoAutorizado() != null ? tuple.getT3().getCupoAutorizado() : BigDecimal.ZERO)
                        .cupoDisponible(tuple.getT3().getCupoDisponible() != null ? tuple.getT3().getCupoDisponible() : BigDecimal.ZERO)
                        .cupoUtilizado(tuple.getT3().getCupoUtilizado() != null ? tuple.getT3().getCupoUtilizado() : BigDecimal.ZERO)
                        .montoGarantia(tuple.getT3().getMontoGarantia() != null ? tuple.getT3().getMontoGarantia() : BigDecimal.ZERO)
                        .fechaGarantia(tuple.getT3().getFechaGarantia())
                        .diasCredito(tuple.getT3().getDiasCredito() != null ? tuple.getT3().getDiasCredito() : 0)
                        .observacion(tuple.getT3().getObservacion())
                        .sobreCupo(tuple.getT3().getSobreCupo() != null ? tuple.getT3().getSobreCupo() : BigDecimal.ZERO)
                        .placa(tuple.getT1().getPlaca())
                        .fechaVencimientoSobreCupo(tuple.getT3().getFechaVencimientoSobreCupo())
                        .fechaCreacion(tuple.getT1().getFechaCreacion())
                        .fechaModificacion(tuple.getT1().getFechaModificacion())
                        .excepciones(tuple.getT6())
                        .detalleEstadoCuentaCobrarDtoList(tuple.getT7())
                        .build()));
    }

    /**
     * Método para encontrar al cliente por su nombre
     *
     * @param customerName nombre del cliente a buscar
     * @return objeto de tipo TbaCliente
     */
    public Mono<Paginacion<TbaCliente>> getCustomerByParams(String customerName, Integer customerCategoryId, String customerIdentification, Integer page, Integer limit) {
        Mono<Long> totalRegisters;
        Flux<TbaCliente> customerFlux;

        boolean searchByName = customerName != null && customerCategoryId == null && customerIdentification == null;
        boolean searchByCategory = customerName == null && customerCategoryId != null && customerIdentification == null;

        Pageable pageable = PageRequest.of(page - 1, limit);

        if (searchByCategory) {
            totalRegisters = tbaClienteRepository.countByIdCategoriaCliente(customerCategoryId);
            customerFlux = tbaClienteRepository.findByIdCategoriaCliente(customerCategoryId, pageable);
        } else if (searchByName) {
            totalRegisters = tbaClienteRepository.countByNombreComercialCliente(customerName);
            customerFlux = tbaClienteRepository.findByNombreComercialClienteContaining(customerName, pageable);
        } else {
            totalRegisters = tbaClienteRepository.count();
            customerFlux = tbaClienteRepository.findAllBy(pageable);
        }
        return totalRegisters.flatMap(total -> customerFlux.collectList().map(tbaClientesList -> new Paginacion<>(tbaClientesList, page, limit, total.intValue())));
    }

    public Mono<PersonaClienteResponse> getClientById(Integer clientId) {
        Mono<TbaCliente> clienteMono = tbaClienteRepository.findById(clientId).switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.CLIENT_NOT_FOUND + clientId)));
        Mono<TbaPersona> personaMono = clienteMono.flatMap(cliente -> tbaPersonaRepository.findById(cliente.getIdPersona()).switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.PERSON_NOT_EXISTS + cliente.getIdPersona()))));
        Mono<TbaCreditoCliente> creditoClienteMono = tbaCreditoClienteService.getCreditByClientId(clientId).switchIfEmpty(Mono.just(new TbaCreditoCliente()));
        Mono<TbaCategoriaCliente> categoriaClienteMono = clienteMono.flatMap(cliente -> tbaCategoriaClienteService.getCategoryClientById(cliente.getIdCategoriaCliente()).switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.CATEGORY_CLIENT_NOT_FOUND + cliente.getIdCategoriaCliente()))));
        return Mono.zip(clienteMono, personaMono, creditoClienteMono, categoriaClienteMono).flatMap(
                tuple -> Mono.just(PersonaClienteResponse.builder()
                        .personaId(tuple.getT2().getIdPersona())
                        .clienteId(tuple.getT1().getIdCliente())
                        .placa(tuple.getT1().getPlaca())
                        .identificacion(tuple.getT2().getIdentificacionPersona())
                        .nombre(tuple.getT2().getNombrePersona())
                        .nombreCliente(tuple.getT1().getNombreComercialCliente())
                        .email(tuple.getT2().getCorreoPersona())
                        .cupoAutorizado(tuple.getT3().getCupoAutorizado())
                        .cupoUtilizado(tuple.getT3().getCupoUtilizado())
                        .sobreCupo(tuple.getT3().getSobreCupo())
                        .cupoDisponible(tuple.getT3().getCupoDisponible())
                        .categoriaCliente(tuple.getT4().getNombreCategoriaCliente())
                        .contactoCliente(tuple.getT1().getContactoCliente())
                        .telefono(tuple.getT2().getTelefonoPersona())
                        .diasCredito(tuple.getT3().getDiasCredito())
                        .observacion(tuple.getT3().getObservacion())
                        .build())
        );
    }

    public Mono<String> verifyClientExistsByIdentification(String identification) {
        return tbaPersonaRepository.existsByIdentificacionPersonaAndEsClientePersona(identification, true)
                .flatMap(exists -> exists ? Mono.error(new CustomException(HttpStatus.CONFLICT, ErrorMessage.CLIENT_ALREADY_EXISTS_WITH_IDENTIFICATION)) : Mono.just("OK"));
    }

    private Mono<EstadoCuentoPagarDto> getEstadoCuentaController(Integer userId, Integer clientId) {
        return sesionService.getHeadersByUser(userId)
                .flatMap(headers -> ventasController.getEstadoCuentaCobrar(clientId, headers));
    }

}
