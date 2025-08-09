package com.sicon.tba.service;

import com.sicon.tba.constants.ErrorMessage;
import com.sicon.tba.controller.ContabilidadController;
import com.sicon.tba.dto.FacturaCalendarioPagoDto;
import com.sicon.tba.dto.PersonaProveedorDto;
import com.sicon.tba.dto.response.*;
import com.sicon.tba.dto.TbaProveedorDto;
import com.sicon.tba.dto.ProveedorPatchDto;
import com.sicon.tba.enums.CountryEnum;
import com.sicon.tba.enums.CuentaProveedorEnum;
import com.sicon.tba.exception.CustomException;
import com.sicon.tba.models.*;
import com.sicon.tba.repository.*;
import com.sicon.tba.repository.custom.TbaProveedorCustom;
import com.sicon.tba.utils.Paginacion;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
@Log4j2
public class TbaProveedorService {

    private final TbaProveedorRepository tbaProveedorRepository;
    private final TbaPersonaRepository tbaPersonaRepository;
    private final AdqFacturaProveedorRepository adqFacturaProveedorRepository;
    private final AdqCalendarioPagoRepository adqCalendarioPagoRepository;
    private final TbaCiudadRepository tbaCiudadRepository;
    private final TbaParroquiaRepository tbaParroquiaRepository;
    private final TbaProvinciaRepository tbaProvinciaRepository;
    private final TbaPersonaService tbaPersonaService;
    private final TbaProveedorCustom tbaProveedorCustom;
    private final ContabilidadController contabilidadController;
    private final SesionService sesionService;

    /**
     * Método para crear un nuevo proveedor
     *
     * @param personaProveedorDto Objeto con los datos del proveedor
     * @return Objeto con los datos de TbaProveedor
     */
    @Transactional
    public Mono<PersonProviderResponse> createProvider(PersonaProveedorDto personaProveedorDto) {
        personaProveedorDto.persona().setEsProveedorPersona(true);
        return tbaPersonaRepository.findByIdentificacionPersona(personaProveedorDto.persona().getIdentificacionPersona())
                .flatMap(person -> createProviderWhenExistPerson(person, personaProveedorDto))
                .switchIfEmpty(createProviderWhenNotExistPerson(personaProveedorDto));
    }

    private Mono<PersonProviderResponse> createProviderWhenExistPerson(TbaPersona persona, PersonaProveedorDto personaProveedorDto) {
        return persona.getEsProveedorPersona() ? Mono.error(new CustomException(HttpStatus.CONFLICT, ErrorMessage.PERSON_ALREADY_ASSOCIATED_WITH_PROVIDER))
                : tbaPersonaService.updatePerson(persona.getIdPersona(), personaProveedorDto.persona())
                .flatMap(person -> buildAndSaveProvider(person, personaProveedorDto.proveedor())
                        .flatMap(provider -> buildPersonProviderResponse(person, provider)));
    }

    private Mono<PersonProviderResponse> createProviderWhenNotExistPerson(PersonaProveedorDto personaProveedorDto) {
        return tbaPersonaService.createPerson(personaProveedorDto.persona())
                .flatMap(person -> buildAndSaveProvider(person, personaProveedorDto.proveedor())
                        .flatMap(provider -> buildPersonProviderResponse(person, provider)));
    }

    private Mono<PersonProviderResponse> buildPersonProviderResponse(TbaPersona persona, Proveedor proveedor) {
        return Mono.just(PersonProviderResponse.builder()
                .persona(persona)
                .proveedor(proveedor)
                .build());
    }

    /**
     * Método para actualizar un proveedor
     *
     * @param id                  ID del Proveedor
     * @param personaProveedorDto Objeto con los datos a cambiar
     */
    public Mono<Proveedor> updateProvider(Integer id, PersonaProveedorDto personaProveedorDto) {
        return tbaProveedorRepository.findById(id).switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.PROVIDER_NOT_FOUND + id)))
                .flatMap(proveedor -> {
                    personaProveedorDto.persona().setEsProveedorPersona(true);
                    return tbaPersonaService.updatePerson(proveedor.getIdPersona(), personaProveedorDto.persona())
                            .flatMap(person -> buildAndUpdateProvider(proveedor, personaProveedorDto.proveedor()));
                });
    }

    private Mono<Proveedor> buildAndUpdateProvider(Proveedor proveedor, TbaProveedorDto tbaProveedorDto) {
        proveedor.setIdTipoIdFiscal(tbaProveedorDto.getIdTipoIdFiscal());
        proveedor.setIdRetencionIva(tbaProveedorDto.getIdRetencionIva());
        proveedor.setIdBancoCatalogo(tbaProveedorDto.getIdBancoCatalogo());
        proveedor.setIdRetencionFuente(tbaProveedorDto.getIdRetencionFuente());
        proveedor.setIdTipoProveedor(tbaProveedorDto.getIdTipoProv());
        proveedor.setIdTipoCuentaBancaria(tbaProveedorDto.getIdTipoCuentaBancaria());
        proveedor.setIdCondicionPago(tbaProveedorDto.getIdCondicionPago());
        proveedor.setIdFormaPagoCobro(tbaProveedorDto.getIdFormaPagoCobro());
        proveedor.setNombreComercialProveedor(tbaProveedorDto.getNombreComercialProveedor());
        proveedor.setDireccion2Proveedor(tbaProveedorDto.getDireccion2Proveedor());
        proveedor.setCorreo2Proveedor(tbaProveedorDto.getCorreo2Proveedor());
        proveedor.setCelular2Proveedor(tbaProveedorDto.getCelular2Proveedor());
        proveedor.setTelefono2Proveedor(tbaProveedorDto.getTelefono2Proveedor());
        proveedor.setSitioWebProveedor(tbaProveedorDto.getSitioWebProveedor());
        proveedor.setContactoProveedor(tbaProveedorDto.getContactoProveedor());
        proveedor.setTelefonoContactoProveedor(tbaProveedorDto.getTelefonoContactoProveedor());
        proveedor.setCorreoContactoProveedor(tbaProveedorDto.getCorreoContactoProveedor());
        proveedor.setCreditoProveedor(tbaProveedorDto.getCreditoProveedor());
        proveedor.setMontoCreditoProveedor(tbaProveedorDto.getMontoCreditoProveedor());
        proveedor.setFechaVencimientoCreditoProveedor(tbaProveedorDto.getFechaVencimientoCreditoProveedor());
        proveedor.setDiasCreditoProveedor(tbaProveedorDto.getDiasCreditoProveedor());
        proveedor.setMontoGarantiaProveedor(tbaProveedorDto.getMontoGarantiaProveedor());
        proveedor.setNumeroCuentaBancariaProveedor(tbaProveedorDto.getNumCuentaBancariaProveedor());
        proveedor.setReferenciaPagoProveedor(tbaProveedorDto.getReferenciaPagoProveedor());
        proveedor.setSaldoAnticipoProveedor(tbaProveedorDto.getSaldoAnticipoProveedor());
        proveedor.setNombreChequeProveedor(tbaProveedorDto.getNombreChequeProveedor());
        proveedor.setContribucionEspecialProveedor(tbaProveedorDto.getContribEspecialProveedor());
        proveedor.setEsTransporteProveedor(tbaProveedorDto.getEsTransporteProveedor());
        proveedor.setEstadoProveedor(tbaProveedorDto.getEstadoProveedor());
        proveedor.setNumeroCuentaPago(tbaProveedorDto.getNumeroCuentaPago());
        proveedor.setIdIdentificacionFiscalPago(tbaProveedorDto.getIdIdentificacionFiscalPago());
        proveedor.setCuentaPasivaProveedor(tbaProveedorDto.getCuentaPasivaProveedor());
        proveedor.setCuentaGastoProveedor(tbaProveedorDto.getCuentaGastoProveedor());
        return tbaProveedorRepository.save(proveedor);
    }

    public Mono<Proveedor> patchProvider(Integer id, ProveedorPatchDto proveedorPatchDto) {
        //Verifica si existe la persona que se va a asociar al proveedor
        Mono<Boolean> existsPersonMono = tbaPersonaRepository.existsById(proveedorPatchDto.getIdPersona());
        //Verifica si la persona ya fue asociada a un proveedor
        Mono<Boolean> existsProviderByPersonMono = tbaProveedorRepository.existsByIdPersona(proveedorPatchDto.getIdPersona());
        //Se recupera el proveedor por el id
        Mono<Proveedor> providerMono = tbaProveedorRepository.findById(id);

        return Mono.zip(existsPersonMono, existsProviderByPersonMono, providerMono).flatMap(tuple -> {

            boolean existPerson = tuple.getT1();
            boolean existProviderByPerson = tuple.getT2();
            Proveedor provider = tuple.getT3();

            if (proveedorPatchDto.getIdPersona() != null) {
                if (!existPerson)
                    return Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.PERSON_NOT_FOUND + proveedorPatchDto.getIdPersona()));
                if (existProviderByPerson)
                    return Mono.error(new CustomException(HttpStatus.CONFLICT, ErrorMessage.PERSON_ALREADY_ASSOCIATED_WITH_PROVIDER));
                provider.setIdPersona(proveedorPatchDto.getIdPersona());
            }
            if (proveedorPatchDto.getIdTipoIdFiscal() != null)
                provider.setIdTipoIdFiscal(proveedorPatchDto.getIdTipoIdFiscal());
            if (proveedorPatchDto.getIdRetencionIva() != null)
                provider.setIdRetencionIva(proveedorPatchDto.getIdRetencionIva());
            if (proveedorPatchDto.getIdBancoCatalogo() != null)
                provider.setIdBancoCatalogo(proveedorPatchDto.getIdBancoCatalogo());
            if (proveedorPatchDto.getIdRetencionFuente() != null)
                provider.setIdRetencionFuente(proveedorPatchDto.getIdRetencionFuente());
            if (proveedorPatchDto.getIdTipoProv() != null)
                provider.setIdTipoProveedor(proveedorPatchDto.getIdTipoProv());
            if (proveedorPatchDto.getIdTipoCuentaBancaria() != null)
                provider.setIdTipoCuentaBancaria(proveedorPatchDto.getIdTipoCuentaBancaria());
            if (proveedorPatchDto.getIdCondicionPago() != null)
                provider.setIdCondicionPago(proveedorPatchDto.getIdCondicionPago());
            if (proveedorPatchDto.getIdFormaPagoCobro() != null)
                provider.setIdFormaPagoCobro(proveedorPatchDto.getIdFormaPagoCobro());
            if (proveedorPatchDto.getNombreComercialProveedor() != null)
                provider.setNombreComercialProveedor(proveedorPatchDto.getNombreComercialProveedor());
            if (proveedorPatchDto.getDireccion2Proveedor() != null)
                provider.setDireccion2Proveedor(proveedorPatchDto.getDireccion2Proveedor());
            if (proveedorPatchDto.getCorreo2Proveedor() != null)
                provider.setCorreo2Proveedor(proveedorPatchDto.getCorreo2Proveedor());
            if (proveedorPatchDto.getCelular2Proveedor() != null)
                provider.setCelular2Proveedor(proveedorPatchDto.getCelular2Proveedor());
            if (proveedorPatchDto.getTelefono2Proveedor() != null)
                provider.setTelefono2Proveedor(proveedorPatchDto.getTelefono2Proveedor());
            if (proveedorPatchDto.getSitioWebProveedor() != null)
                provider.setSitioWebProveedor(proveedorPatchDto.getSitioWebProveedor());
            if (proveedorPatchDto.getContactoProveedor() != null)
                provider.setContactoProveedor(proveedorPatchDto.getContactoProveedor());
            if (proveedorPatchDto.getTelefonoContactoProveedor() != null)
                provider.setTelefonoContactoProveedor(proveedorPatchDto.getTelefonoContactoProveedor());
            if (proveedorPatchDto.getCorreoContactoProveedor() != null)
                provider.setCorreoContactoProveedor(proveedorPatchDto.getCorreoContactoProveedor());
            if (proveedorPatchDto.getCelularContactoProveedor() != null)
                provider.setCelularContactoProveedor(proveedorPatchDto.getCelularContactoProveedor());
            if (proveedorPatchDto.getCreditoProveedor() != null)
                provider.setCreditoProveedor(proveedorPatchDto.getCreditoProveedor());
            if (proveedorPatchDto.getMontoCreditoProveedor() != null)
                provider.setMontoCreditoProveedor(proveedorPatchDto.getMontoCreditoProveedor());
            if (proveedorPatchDto.getFechaVencimientoCreditoProveedor() != null)
                provider.setFechaVencimientoCreditoProveedor(proveedorPatchDto.getFechaVencimientoCreditoProveedor());
            if (proveedorPatchDto.getDiasCreditoProveedor() != null)
                provider.setDiasCreditoProveedor(proveedorPatchDto.getDiasCreditoProveedor());
            if (proveedorPatchDto.getMontoGarantiaProveedor() != null)
                provider.setMontoGarantiaProveedor(proveedorPatchDto.getMontoGarantiaProveedor());
            if (proveedorPatchDto.getNumCuentaBancariaProveedor() != null)
                provider.setNumeroCuentaBancariaProveedor(proveedorPatchDto.getNumCuentaBancariaProveedor());
            if (proveedorPatchDto.getReferenciaPagoProveedor() != null)
                provider.setReferenciaPagoProveedor(proveedorPatchDto.getReferenciaPagoProveedor());
            if (proveedorPatchDto.getSaldoAnticipoProveedor() != null)
                provider.setSaldoAnticipoProveedor(proveedorPatchDto.getSaldoAnticipoProveedor());
            if (proveedorPatchDto.getNombreChequeProveedor() != null)
                provider.setNombreChequeProveedor(proveedorPatchDto.getNombreChequeProveedor());
            if (proveedorPatchDto.getContribEspecialProveedor() != null)
                provider.setContribucionEspecialProveedor(proveedorPatchDto.getContribEspecialProveedor());
            if (proveedorPatchDto.getEsTransporteProveedor() != null)
                provider.setEsTransporteProveedor(proveedorPatchDto.getEsTransporteProveedor());
            if (proveedorPatchDto.getEstadoProveedor() != null)
                provider.setEstadoProveedor(proveedorPatchDto.getEstadoProveedor());
            if (proveedorPatchDto.getNumeroCuentaPago() != null)
                provider.setNumeroCuentaPago(proveedorPatchDto.getNumeroCuentaPago());
            if (proveedorPatchDto.getIdIdentificacionFiscalPago() != null)
                provider.setIdIdentificacionFiscalPago(proveedorPatchDto.getIdIdentificacionFiscalPago());
            if (proveedorPatchDto.getCuentaPasivaProveedor() != null)
                provider.setCuentaPasivaProveedor(proveedorPatchDto.getCuentaPasivaProveedor());
            if (proveedorPatchDto.getCuentaGastoProveedor() != null)
                provider.setCuentaGastoProveedor(proveedorPatchDto.getCuentaGastoProveedor());
            return tbaProveedorRepository.save(provider);
        });
    }

    /**
     * Método para Obtener a todos los Proveedores
     *
     * @param page Número de página
     * @return todos los proveedores
     */
    public Mono<Paginacion<Proveedor>> getProvidersBySearchParams(Boolean searchForeign, Integer page, Integer limit, String sortDirection, String sortField) {
        Pageable pageable;
        try {
            Sort sort = sortDirection != null && sortField != null ? Sort.by(Sort.Direction.fromString(sortDirection), sortField) : Sort.unsorted();
            pageable = PageRequest.of(page - 1, limit, sort);
        } catch (Exception e) {
            return Mono.error(new CustomException(HttpStatus.BAD_REQUEST, e.getCause() != null ? e.getCause().getMessage() : e.getMessage()));
        }
        List<Integer> notSearchCountryIdList = Collections.singletonList(CountryEnum.ECUADOR.getIdCountry());

        Flux<Proveedor> tbaProveedorFlux;
        Mono<Long> totalProvider;
        if (searchForeign) {
            tbaProveedorFlux = tbaProveedorCustom.findByIdPaisNotInAndEstado(notSearchCountryIdList, Boolean.TRUE, sortField, sortDirection, pageable.getPageSize(), pageable.getOffset()).onErrorMap(e -> new CustomException(HttpStatus.BAD_REQUEST, e.getCause() != null ? e.getCause().getMessage() : e.getMessage()));
            totalProvider = tbaProveedorRepository.countByIdPaisNotInAndEstado(notSearchCountryIdList, Boolean.TRUE).onErrorMap(e -> new CustomException(HttpStatus.BAD_REQUEST, e.getCause() != null ? e.getCause().getMessage() : e.getMessage()));
        } else {
            tbaProveedorFlux = tbaProveedorRepository.findAllBy(pageable).onErrorMap(e -> new CustomException(HttpStatus.BAD_REQUEST, e.getCause() != null ? e.getCause().getMessage() : e.getMessage()));
            totalProvider = tbaProveedorRepository.count().onErrorMap(e -> new CustomException(HttpStatus.BAD_REQUEST, e.getCause() != null ? e.getCause().getMessage() : e.getMessage()));
        }
        return totalProvider.flatMap(total -> tbaProveedorFlux.collectList().map(tbaProviders -> new Paginacion<>(tbaProviders, page, limit, total.intValue()))).onErrorMap(e -> new CustomException(HttpStatus.BAD_REQUEST, e.getCause() != null ? e.getCause().getMessage() : e.getMessage()));
    }

    /**
     * Método para obtener un proveedor por la persona id
     *
     * @param persona Objeto con los datos de la persona
     * @return Objeto con los datos del proveedor
     */
    public Mono<PersonaProveedorResponse> getProviderByPersonId(TbaPersona persona) {
        return tbaProveedorRepository.findByIdPersona(persona.getIdPersona())
                .map(proveedor -> PersonaProveedorResponse.builder()
                        .nombreProveedor(proveedor.getNombreComercialProveedor())
                        .proveedorId(proveedor.getIdProveedor())
                        .nombre(persona.getNombrePersona())
                        .personaId(persona.getIdPersona())
                        .identificacion(persona.getIdentificacionPersona())
                        .idCondicionPago(proveedor.getIdCondicionPago())
                        .esExtranjero(!persona.getIdPais().equals(CountryEnum.ECUADOR.getIdCountry()))
                        .build());
    }

    private Mono<EstadoCuentoPagarDto> getEstadoCuentaController(Integer userId, Integer proveedorId) {
        return sesionService.getHeadersByUser(userId)
                .flatMap(headers -> contabilidadController.getEstadoCuentaPagar(proveedorId, headers));
    }

    public Mono<TbaProveedorResponseDto> getProvider(Integer proveedorId, Integer userId) {
        Mono<Proveedor> tbaProveedorDtoMono = tbaProveedorRepository.findById(proveedorId);
        Mono<TbaPersona> tbaPersonaMono = tbaProveedorDtoMono.flatMap(proveedor -> tbaPersonaRepository.findById(proveedor.getIdPersona())).onErrorMap(e -> new CustomException(HttpStatus.BAD_REQUEST, e.getCause() != null ? e.getCause().getMessage() : e.getMessage()));
        Mono<Integer> numberInvoicesMono = tbaProveedorRepository.numberInvoicesByProvider(proveedorId).onErrorMap(e -> new CustomException(HttpStatus.BAD_REQUEST, e.getCause() != null ? e.getCause().getMessage() : e.getMessage()));
        Mono<TbaParroquia> parishMono = tbaPersonaMono.flatMap(tbaPersona -> tbaPersona.getIdParroquia() != null ? tbaParroquiaRepository.findById(tbaPersona.getIdParroquia()) : Mono.just(new TbaParroquia())).onErrorMap(e -> new CustomException(HttpStatus.BAD_REQUEST, e.getCause() != null ? e.getCause().getMessage() : e.getMessage()));
        Mono<TbaCiudad> cityMono = parishMono.flatMap(parroquia -> parroquia.getIdCiudad() != null ? tbaCiudadRepository.findById(parroquia.getIdCiudad()) : Mono.just(new TbaCiudad())).onErrorMap(e -> new CustomException(HttpStatus.BAD_REQUEST, e.getCause() != null ? e.getCause().getMessage() : e.getMessage()));
        Mono<TbaProvincia> provinceMono = cityMono.flatMap(ciudad -> ciudad.getIdProvincia() != null ? tbaProvinciaRepository.findById(ciudad.getIdProvincia()) : Mono.just(new TbaProvincia())).onErrorMap(e -> new CustomException(HttpStatus.BAD_REQUEST, e.getCause() != null ? e.getCause().getMessage() : e.getMessage()));
        Mono<EstadoCuentoPagarDto> estadoCuentoPagarDtoMono = getEstadoCuentaController(userId, proveedorId);

        return Mono.zip(tbaProveedorDtoMono, numberInvoicesMono, estadoCuentoPagarDtoMono, tbaPersonaMono, parishMono, cityMono, provinceMono).flatMap(tuple -> {
            Proveedor provider = tuple.getT1();
            EstadoCuentoPagarDto estadoCuenta = tuple.getT3();

            Integer numeroFacturas = tuple.getT2();

            TbaProveedorResponseDto tbaProveedorResponseDto = new TbaProveedorResponseDto();
            tbaProveedorResponseDto.setProveedor(provider);
            tbaProveedorResponseDto.setNumeroFacturas(tuple.getT2());
            tbaProveedorResponseDto.setNombreParroquia(tuple.getT5().getNombreParroquia());
            tbaProveedorResponseDto.setIdCiudad(tuple.getT6().getIdCiudad());
            tbaProveedorResponseDto.setNombreCiudad(tuple.getT6().getNombreCiudad());
            tbaProveedorResponseDto.setIdProvincia(tuple.getT7().getIdProvincia());
            tbaProveedorResponseDto.setNombreProvincia(tuple.getT7().getNombreProvincia());
            tbaProveedorResponseDto.setPersona(tuple.getT4());

            tbaProveedorResponseDto.setEstadoCuentoPagar(estadoCuenta);
            tbaProveedorResponseDto.setNumeroFacturas(numeroFacturas);

            return Mono.just(tbaProveedorResponseDto);
        }).onErrorMap(e -> new CustomException(HttpStatus.BAD_REQUEST, e.getCause() != null ? e.getCause().getMessage() : e.getMessage()));
    }

    public Mono<PersonaProveedorResponse> getProviderById(Integer proveedorId) {
        return tbaProveedorRepository.findById(proveedorId)
                .flatMap(proveedor -> tbaPersonaRepository.findById(proveedor.getIdPersona())
                        .map(persona -> PersonaProveedorResponse.builder()
                                .proveedorId(proveedor.getIdProveedor())
                                .nombreProveedor(proveedor.getNombreComercialProveedor())
                                .identificacion(persona.getIdentificacionPersona())
                                .nombre(persona.getNombrePersona())
                                .personaId(persona.getIdPersona())
                                .direccion(persona.getDireccionPersona())
                                .email(persona.getCorreoPersona())
                                .telefono(persona.getTelefonoPersona())
                                .idRetencionFuente(proveedor.getIdRetencionFuente())
                                .idRetencionIva(proveedor.getIdRetencionIva())
                                .diasCredito(proveedor.getDiasCreditoProveedor())
                                .build()));
    }


    /**
     * Servicio que retorna los nombres de las personas
     * que son proveedores que tienen productos pendientes de gestionar en las solicitudes de compras
     *
     * @return Lista de personas que son proveedores
     */
    public Flux<PersonaProveedorResponse> getProvidersNameOCPending() {
        return tbaPersonaRepository.findAllPendingPurchaseOrderProvidersPersons()
                .filter(person -> person.getIdPais().equals(CountryEnum.ECUADOR.getIdCountry()))
                .flatMapSequential(this::getProviderByPersonId);
    }

    public Flux<PersonaProveedorResponse> getAllByListaPedidoAprobadas() {
        return tbaPersonaRepository.getAllByListaPedidoAprobadas()
                .flatMap(this::getProviderByPersonId);
    }

    /**
     * Servicio que retorna los datos del proveedor en el formato que se va a usar en importaciones
     *
     * @param proveedorId Id del proveedor
     * @return Objeto con los datos del proveedor
     */
    public Mono<ProveedorImpResponse> getProviderForImport(Integer proveedorId) {
        return tbaProveedorRepository.findById(proveedorId)
                .flatMap(proveedor -> tbaPersonaRepository.findById(proveedor.getIdPersona())
                        .map(persona -> ProveedorImpResponse.builder()
                                .proveedorId(proveedorId)
                                .nombreProveedor(proveedor.getNombreComercialProveedor())
                                .direccionProveedor(persona.getDireccionPersona())
                                .contacto(persona.getNombrePersona())
                                .identificacion(persona.getIdentificacionPersona())
                                .correoList(Arrays.asList(persona.getCorreoPersona(), proveedor.getCorreo2Proveedor(), proveedor.getCorreoContactoProveedor()))
                                .telefonoList(Arrays.asList(persona.getCelularPersona(), persona.getTelefonoPersona(), proveedor.getCelular2Proveedor(), proveedor.getCelularContactoProveedor()))
                                .build()));
    }

    public Flux<PersonaProveedorResponse> getProviderByOrderListId(Integer id) {
        Flux<Proveedor> providerFlux = tbaProveedorRepository.findByOrderListId(id, 1).onErrorMap(e -> new CustomException(HttpStatus.BAD_REQUEST, e.getCause() != null ? e.getCause().getMessage() : e.getMessage()));
        return providerFlux.flatMap(provider ->
                tbaPersonaRepository.findById(provider.getIdPersona())
                        .map(persona -> PersonaProveedorResponse.builder()
                                .nombreProveedor(provider.getNombreComercialProveedor())
                                .proveedorId(provider.getIdProveedor())
                                .nombre(persona.getNombrePersona())
                                .personaId(persona.getIdPersona())
                                .identificacion(persona.getIdentificacionPersona())
                                .build())
        ).onErrorMap(e -> new CustomException(HttpStatus.BAD_REQUEST, e.getCause() != null ? e.getCause().getMessage() : e.getMessage()));
    }

    public Mono<String> verifyProviderExistsByIdentification(String identification) {
        return tbaPersonaRepository.existsByIdentificacionPersonaAndEsProveedorPersona(identification, true)
                .flatMap(exists -> exists ? Mono.error(new CustomException(HttpStatus.CONFLICT, ErrorMessage.PROVIDER_ALREADY_EXISTS_WITH_IDENTIFICATION)) : Mono.just("OK"));
    }


    public Flux<PersonaProveedorResponse> getCarriers() {
        return tbaProveedorRepository.findByEsTransporteProveedorAndEstadoProveedor(true, true)
                .flatMap(provider ->
                        tbaPersonaRepository.findById(provider.getIdPersona())
                                .map(persona -> PersonaProveedorResponse.builder()
                                        .nombreProveedor(provider.getNombreComercialProveedor())
                                        .proveedorId(provider.getIdProveedor())
                                        .nombre(persona.getNombrePersona())
                                        .personaId(persona.getIdPersona())
                                        .identificacion(persona.getIdentificacionPersona())
                                        .build())
                );
    }

    private Mono<Proveedor> buildAndSaveProvider(TbaPersona person, TbaProveedorDto tbaProveedor) {
        return tbaProveedorRepository.save(Proveedor.builder()
                .idTipoIdFiscal(tbaProveedor.getIdTipoIdFiscal())
                .idRetencionIva(tbaProveedor.getIdRetencionIva())
                .idPersona(person.getIdPersona())
                .idBancoCatalogo(tbaProveedor.getIdBancoCatalogo())
                .idRetencionFuente(tbaProveedor.getIdRetencionFuente())
                .idTipoProveedor(tbaProveedor.getIdTipoProv())
                .idTipoCuentaBancaria(tbaProveedor.getIdTipoCuentaBancaria())
                .idCondicionPago(tbaProveedor.getIdCondicionPago())
                .idFormaPagoCobro(tbaProveedor.getIdFormaPagoCobro())
                .nombreComercialProveedor(tbaProveedor.getNombreComercialProveedor())
                .direccion2Proveedor(tbaProveedor.getDireccion2Proveedor())
                .correo2Proveedor(tbaProveedor.getCorreo2Proveedor())
                .celular2Proveedor(tbaProveedor.getCelular2Proveedor())
                .telefono2Proveedor(tbaProveedor.getTelefono2Proveedor())
                .sitioWebProveedor(tbaProveedor.getSitioWebProveedor())
                .contactoProveedor(tbaProveedor.getContactoProveedor())
                .telefonoContactoProveedor(tbaProveedor.getTelefonoContactoProveedor())
                .correoContactoProveedor(tbaProveedor.getCorreoContactoProveedor())
                .celularContactoProveedor(tbaProveedor.getCelularContactoProveedor())
                .creditoProveedor(tbaProveedor.getCreditoProveedor())
                .montoCreditoProveedor(tbaProveedor.getMontoCreditoProveedor())
                .fechaVencimientoCreditoProveedor(tbaProveedor.getFechaVencimientoCreditoProveedor() == null ? LocalDate.now() : tbaProveedor.getFechaVencimientoCreditoProveedor())
                .diasCreditoProveedor(tbaProveedor.getDiasCreditoProveedor())
                .montoGarantiaProveedor(tbaProveedor.getMontoGarantiaProveedor())
                .numeroCuentaBancariaProveedor(tbaProveedor.getNumCuentaBancariaProveedor())
                .referenciaPagoProveedor(tbaProveedor.getReferenciaPagoProveedor())
                .saldoAnticipoProveedor(tbaProveedor.getSaldoAnticipoProveedor())
                .nombreChequeProveedor(tbaProveedor.getNombreChequeProveedor())
                .contribucionEspecialProveedor(tbaProveedor.getContribEspecialProveedor())
                .esTransporteProveedor(tbaProveedor.getEsTransporteProveedor())
                .estadoProveedor(tbaProveedor.getEstadoProveedor())
                .idIdentificacionFiscalPago(tbaProveedor.getIdIdentificacionFiscalPago())
                .numeroCuentaPago(tbaProveedor.getNumeroCuentaPago())
                .cuentaPasivaProveedor(person.getIdPais().equals(593) ? CuentaProveedorEnum.PROVEEDOR_NACIONAL_NO_RELACIONADO.getId() : CuentaProveedorEnum.PROVEEDOR_EXTERIOR.getId())
                .cuentaGastoProveedor(tbaProveedor.getCuentaGastoProveedor())
                .build());
    }

}