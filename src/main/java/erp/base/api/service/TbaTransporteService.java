package erp.base.api.service;

import erp.base.api.constants.ErrorMessage;
import erp.base.api.dto.response.PersonaProveedorResponse;
import erp.base.api.dto.response.TbaTransporteResponse;
import erp.base.api.exception.CustomException;
import erp.base.api.models.TbaTransporte;
import erp.base.api.repository.TbaTransporteRepository;
import erp.base.api.repository.custom.TbaTransporteCustom;
import erp.base.api.utils.Paginacion;
import erp.base.api.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
@Log4j2
public class TbaTransporteService {

    private final TbaTransporteRepository tbaTransporteRepository;
    private final TbaTipoVehiculoService tbaTipoVehiculoService;
    private final TbaTransporteCustom tbaTransporteCustom;

    public Mono<Void> createTransportation(TbaTransporte tbaTransporte) {
        tbaTransporte.setIdTransporte(null);
        return tbaTransporteRepository.save(tbaTransporte).then();
    }

    public Mono<Void> updateTransportation(Integer id, TbaTransporte tbaTransporte) {
        return tbaTransporteRepository.findById(id)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.TRANSPORTATION_NOT_FOUND + id)))
                .flatMap(tbaTransporte1 -> {
                    tbaTransporte.setIdTransporte(id);
                    return tbaTransporteRepository.save(tbaTransporte);
                }).then();
    }

    public Mono<TbaTransporte> patchTransportation(Integer id, TbaTransporte tbaTransporte) {
        return tbaTransporteRepository.findById(id)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.TRANSPORTATION_NOT_FOUND + id)))
                .flatMap(tbaTransporte1 -> {
                    if (tbaTransporte.getIdTipoVehiculo() != null)
                        tbaTransporte1.setIdTipoVehiculo(tbaTransporte.getIdTipoVehiculo());
                    if (tbaTransporte.getNombreContacto() != null)
                        tbaTransporte1.setNombreContacto(tbaTransporte.getNombreContacto());
                    if (tbaTransporte.getIdentificacionContacto() != null)
                        tbaTransporte1.setIdentificacionContacto(tbaTransporte.getIdentificacionContacto());
                    if (tbaTransporte.getNumeroContacto() != null)
                        tbaTransporte1.setNumeroContacto(tbaTransporte.getNumeroContacto());
                    if (tbaTransporte.getPlacaTransporte() != null)
                        tbaTransporte1.setPlacaTransporte(tbaTransporte.getPlacaTransporte());
                    if (tbaTransporte.getSectorTransporte() != null)
                        tbaTransporte1.setSectorTransporte(tbaTransporte.getSectorTransporte());
                    if (tbaTransporte.getProvinciaTransporte() != null)
                        tbaTransporte1.setProvinciaTransporte(tbaTransporte.getProvinciaTransporte());
                    if (tbaTransporte.getDetalleTransporte() != null)
                        tbaTransporte1.setDetalleTransporte(tbaTransporte.getDetalleTransporte());
                    if (tbaTransporte.getPesoMinimoTransporte() != null)
                        tbaTransporte1.setPesoMinimoTransporte(tbaTransporte.getPesoMinimoTransporte());
                    if (tbaTransporte.getPesoMaximoTransporte() != null)
                        tbaTransporte1.setPesoMaximoTransporte(tbaTransporte.getPesoMaximoTransporte());
                    if (tbaTransporte.getMatriculaTransporte() != null)
                        tbaTransporte1.setMatriculaTransporte(tbaTransporte.getMatriculaTransporte());
                    if (tbaTransporte.getInternoTransporte() != null)
                        tbaTransporte1.setInternoTransporte(tbaTransporte.getInternoTransporte());
                    if (tbaTransporte.getInactivoTransporte() != null)
                        tbaTransporte1.setInactivoTransporte(tbaTransporte.getInactivoTransporte());
                    return tbaTransporteRepository.save(tbaTransporte1);
                });
    }

    public Mono<TbaTransporteResponse> getTransportationById(Integer id) {
        return tbaTransporteRepository.findById(id)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.TRANSPORTATION_NOT_FOUND + id)))
                .flatMap(tbaTransporte -> tbaTipoVehiculoService.findById(tbaTransporte.getIdTipoVehiculo())
                        .map(tbaTipoVehiculo ->
                                TbaTransporteResponse.builder()
                                        .idTransporte(tbaTransporte.getIdTransporte())
                                        .idTipoVehiculo(tbaTipoVehiculo.getIdTipoVehiculo())
                                        .nombreTipoVehiculo(tbaTipoVehiculo.getNombreTipoVehiculo())
                                        .nombreContacto(tbaTransporte.getNombreContacto())
                                        .identificacionContacto(tbaTransporte.getIdentificacionContacto())
                                        .numeroContacto(tbaTransporte.getNumeroContacto())
                                        .placaTransporte(tbaTransporte.getPlacaTransporte())
                                        .sectorTransporte(tbaTransporte.getSectorTransporte())
                                        .provinciaTransporte(tbaTransporte.getProvinciaTransporte())
                                        .detalleTransporte(tbaTransporte.getDetalleTransporte())
                                        .pesoMinimoTransporte(tbaTransporte.getPesoMinimoTransporte())
                                        .pesoMaximoTransporte(tbaTransporte.getPesoMaximoTransporte())
                                        .matriculaTransporte(tbaTransporte.getMatriculaTransporte())
                                        .internoTransporte(tbaTransporte.getInternoTransporte())
                                        .inactivoTransporte(tbaTransporte.getInactivoTransporte())
                                        .idUsuarioCreacion(tbaTransporte.getIdUsuarioCreacion())
                                        .idUsuarioModificacion(tbaTransporte.getIdUsuarioModificacion())
                                        .fechaCreacion(tbaTransporte.getFechaCreacion())
                                        .fechaModificacion(tbaTransporte.getFechaModificacion())
                                        .build()));
    }

    public Mono<Paginacion<TbaTransporteResponse>> getTransportationsBySearchParams(String vehicleTypeName, String contactName, String sector, String province,
                                                                                    Boolean internal, Boolean inactive,
                                                                                    ZonedDateTime startDateParam, ZonedDateTime endDateParam, Boolean byCurrentMonth,
                                                                                    Integer page, Integer limit, String sortDirection, String sortField) {
        ZonedDateTime startDate = Utils.getStartDateForPagination(byCurrentMonth, startDateParam);
        ZonedDateTime endDate = Utils.getEndDateForPagination(byCurrentMonth, endDateParam);

        Flux<TbaTransporteResponse> tbaTransporteResponseFlux = tbaTransporteCustom.getBySearchParams(vehicleTypeName, contactName, sector, province, internal, inactive, startDate, endDate, sortField, sortDirection, limit, (page - 1) * limit);
        Mono<Long> total = tbaTransporteCustom.getCountBySearchParams(vehicleTypeName, contactName, sector, province, internal, inactive, startDate, endDate);

        return total.flatMap(totalCount -> tbaTransporteResponseFlux.collectList().map(list -> new Paginacion<>(list, page, limit, totalCount.intValue())))
                .onErrorMap(e -> new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage()));
    }

    public Flux<TbaTransporte> getAllActiveTransportistas(String paramSearch) {
        String identification = null;
        String completeName = null;

        if (Utils.validateNumbers(paramSearch))
            identification = paramSearch;
        else
            completeName = paramSearch;

        return tbaTransporteRepository.getAllActiveTransportistas(completeName, identification);

    }

    public Mono<TbaTransporte> getTransportationByCiAndPlaca(String ci, String placa) {
        return tbaTransporteRepository.findByIdentificacionContactoAndPlacaTransporte(ci, placa);
    }

}
