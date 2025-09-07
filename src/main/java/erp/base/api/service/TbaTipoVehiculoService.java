package erp.base.api.service;

import erp.base.api.constants.ErrorMessage;
import erp.base.api.exception.CustomException;
import erp.base.api.models.TbaTipoVehiculo;
import erp.base.api.repository.TbaTipoVehiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TbaTipoVehiculoService {

    private final TbaTipoVehiculoRepository tbaTipoVehiculoRepository;

    public Mono<TbaTipoVehiculo> createVehicleType(TbaTipoVehiculo tbaTipoVehiculo) {
        tbaTipoVehiculo.setIdTipoVehiculo(null);
        return tbaTipoVehiculoRepository.save(tbaTipoVehiculo);
    }

    public Flux<TbaTipoVehiculo> findAll() {
        return tbaTipoVehiculoRepository.findAll();
    }

    public Mono<TbaTipoVehiculo> findById(Integer id) {
        return tbaTipoVehiculoRepository.findById(id).switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.VEHICLE_TYPE_NOT_FOUND + id)));
    }

    public Mono<Void> delete(Integer id) {
        return tbaTipoVehiculoRepository.findById(id)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.VEHICLE_TYPE_NOT_FOUND + id)))
                .flatMap(tbaTipoVehiculoRepository::delete);

    }
}
