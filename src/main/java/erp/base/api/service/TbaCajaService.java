package erp.base.api.service;

import erp.base.api.constants.ErrorMessage;
import erp.base.api.exception.CustomException;
import erp.base.api.models.Caja;
import erp.base.api.models.TbaCategoriaCliente;
import erp.base.api.models.TbaRolUsuario;
import erp.base.api.repository.TbaCajaRepository;
import erp.base.api.utils.Paginacion;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Log4j2
public class TbaCajaService {
    private final TbaCajaRepository tbaCajaRepository;

    public Flux<Caja> findCajaByIdSucusal(Integer id) {
        return tbaCajaRepository.findByIdSucursal(id).switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.BOX_NOT_FOUND + id)));
    }

    public Mono<Caja> findById(Integer id) {
        return tbaCajaRepository.findById(id).switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.BOX_NOT_FOUND + id)));
    }

    public Mono<Paginacion<Caja>> getRequestBySearchParams(
            Integer branchId,
            String name,
            Integer page,
            Integer limit,
            String sortDirection,
            String sortfield
    ) {
        Flux<Caja> cajaFlux = tbaCajaRepository.getBySearchParams(branchId, name, sortfield, sortDirection, limit, (page - 1) * limit);
        Mono<Long> total = tbaCajaRepository.getCountBySearchParams(branchId, name);
        return total.flatMap(totalCount -> cajaFlux.collectList().map(cajaList -> new Paginacion<>(cajaList, page, limit, totalCount.intValue())));
    }

    public Mono<Caja> createCaja(Caja caja) {
        return validateAndSaveCaja(null, caja);
    }

    public Mono<Caja> updateCaja(Integer id, Caja caja) {
        return validateAndSaveCaja(id, caja);
    }

    public Mono<Caja> validateAndSaveCaja(Integer id, Caja caja) {
        Mono<Caja> cajaMono = (id != null) ? tbaCajaRepository.findById(id) : Mono.just(new Caja());
        return cajaMono
                .flatMap(existingCaja -> {
                    Caja newCaja = Caja.builder()
                            .idCaja(id)
                            .idCuenta(caja.getIdCuenta())
                            .idSucursal(caja.getIdSucursal())
                            .nombreCaja(caja.getNombreCaja())
                            .numeroCaja(caja.getNumeroCaja())
                            .secuenciaFactura(caja.getSecuenciaFactura())
                            .secuenciaGuia(caja.getSecuenciaGuia())
                            .secuenciaNc(caja.getSecuenciaNc())
                            .secuenciaNd(caja.getSecuenciaNd())
                            .secuenciaRetencion(caja.getSecuenciaRetencion())
                            .secuenciaLiquidacion(caja.getSecuenciaLiquidacion())
                            .estadoCaja(caja.getEstadoCaja())
                            .build();

                    return tbaCajaRepository.save(newCaja);
                });
    }
}
