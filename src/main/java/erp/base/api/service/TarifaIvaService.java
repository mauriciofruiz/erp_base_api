package erp.base.api.service;

import erp.base.api.constants.ErrorMessage;
import erp.base.api.dto.TarifaIvaDto;
import erp.base.api.exception.CustomException;
import erp.base.api.models.TarifaIva;
import erp.base.api.repository.TarifaIvaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TarifaIvaService {

    private final TarifaIvaRepository tarifaIvaRepository;

    /**
     * Este método se utiliza para crear una nueva tarifa de IVA.
     *
     * @param tarifaIvaDto Un objeto de tipo TarifaIvaDto que contiene la información necesaria para crear una nueva tarifa de IVA.
     * @return Un objeto Mono que contiene la tarifa de IVA creada.
     */
    public Mono<TarifaIva> create(TarifaIvaDto tarifaIvaDto) {
        return tarifaIvaRepository.save(TarifaIva.builder()
                .porcentajeTarifaIva(tarifaIvaDto.porcentajeTarifaIva()) // Establece el porcentaje de la tarifa de IVA.
                .codigoTarifaIva(tarifaIvaDto.codigoTarifaIva()) // Establece el código de la tarifa de IVA.
                .descripcionIva(tarifaIvaDto.descripcionIva()) // Establece la descripción de la tarifa de IVA.
                .idCuentaVenta(tarifaIvaDto.idCuentaVenta()) // Establece el ID de la cuenta de venta asociada a la tarifa de IVA.
                .idCuentaCompra(tarifaIvaDto.idCuentaCompra()) // Establece el ID de la cuenta de compra asociada a la tarifa de IVA.
                .estadoTarifaIva(tarifaIvaDto.estadoTarifaIva()) // Establece el estado de la tarifa de IVA.
                .build() // Construye el objeto TarifaIva con los datos proporcionados.
        );
    }

    /**
     * Este método se utiliza para actualizar una tarifa de IVA existente.
     *
     * @param id El ID de la tarifa de IVA que se desea actualizar.
     * @param tarifaIvaDto Un objeto de tipo TarifaIvaDto que contiene la información actualizada de la tarifa de IVA.
     * @return Un objeto Mono que contiene la tarifa de IVA actualizada.
     * @throws CustomException Si no se encuentra una tarifa de IVA con el ID proporcionado, se lanza una excepción.
     */
    public Mono<TarifaIva> update(Integer id, TarifaIvaDto tarifaIvaDto) {
        return tarifaIvaRepository.findById(id).switchIfEmpty(
                Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.TAX_RATE_NOT_FOUND + id))
        ).flatMap(retencionFuente -> tarifaIvaRepository.save(TarifaIva.builder()
                .idTarifaIva(id) // Establece el ID de la tarifa de IVA.
                .porcentajeTarifaIva(tarifaIvaDto.porcentajeTarifaIva()) // Establece el porcentaje de la tarifa de IVA.
                .codigoTarifaIva(tarifaIvaDto.codigoTarifaIva()) // Establece el código de la tarifa de IVA.
                .descripcionIva(tarifaIvaDto.descripcionIva()) // Establece la descripción de la tarifa de IVA.
                .idCuentaVenta(tarifaIvaDto.idCuentaVenta()) // Establece el ID de la cuenta de venta asociada a la tarifa de IVA.
                .idCuentaCompra(tarifaIvaDto.idCuentaCompra()) // Establece el ID de la cuenta de compra asociada a la tarifa de IVA.
                .estadoTarifaIva(tarifaIvaDto.estadoTarifaIva()) // Establece el estado de la tarifa de IVA.
                .build()) // Construye el objeto TarifaIva con los datos proporcionados.
        );
    }

    /**
     * Este método se utiliza para obtener una tarifa de IVA por su ID.
     *
     * @param id El ID de la tarifa de IVA que se desea obtener.
     * @return Un objeto Mono que contiene la tarifa de IVA solicitada.
     * @throws CustomException Si no se encuentra una tarifa de IVA con el ID proporcionado, se lanza una excepción.
     */
    public Mono<TarifaIva> getById(Integer id) {
        return tarifaIvaRepository.findById(id)
                .switchIfEmpty(
                        Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.TAX_RATE_NOT_FOUND + id)
                        )
                );
    }

    /**
     * Este método se utiliza para obtener todas las tarifas de IVA activas.
     *
     * @return Un objeto Flux que contiene todas las tarifas de IVA activas.
     */
    public Flux<TarifaIva> getAllActive() {
        return tarifaIvaRepository.findByEstadoTarifaIva(true);
    }

}
