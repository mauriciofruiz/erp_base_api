package erp.base.api.service;

import erp.base.api.constants.ErrorMessage;
import erp.base.api.dto.ExcepcionesClienteDescDto;
import erp.base.api.enums.TipoExcepcionClienteEnum;
import erp.base.api.exception.CustomException;
import erp.base.api.models.*;
import erp.base.api.repository.ExcepcionesClienteDescuentoRepository;
import erp.base.api.repository.GrupoRepository;
import erp.base.api.repository.ProductoRepository;
import erp.base.api.repository.SubgrupoRepository;
import erp.base.api.utils.Paginacion;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcepcionesClienteDescuentoService {

    private final ExcepcionesClienteDescuentoRepository excepcionesClienteDescuentoRepository;
    private final ProductoRepository productoRepository;
    private final SubgrupoRepository subgrupoRepository;
    private final GrupoRepository grupoRepository;

    public Flux<ExcepcionesClienteDescDto> getAllDtoByIdCliente(Long idCliente) {
        return excepcionesClienteDescuentoRepository.findAllByIdCliente(idCliente)
                .flatMapSequential(this::mapToDto);
    }

    private Mono<ExcepcionesClienteDescDto> mapToDto(ExcepcionesClienteDescuento excepcionesClienteDescuento) {
        Mono<String> valorParametroMono;
        if (excepcionesClienteDescuento.getIdProducto() != null) {
            valorParametroMono = productoRepository.findById(excepcionesClienteDescuento.getIdProducto())
                    .map(producto -> producto.getCodProducto().concat(" - ").concat(producto.getNombreCortoProducto()))
                    .switchIfEmpty(Mono.just("Producto no encontrado"));
        } else if (excepcionesClienteDescuento.getIdSubgrupo() != null) {
            valorParametroMono = subgrupoRepository.findById(excepcionesClienteDescuento.getIdSubgrupo())
                    .map(InvSubGrupo::getNombreSubGrupo)
                    .switchIfEmpty(Mono.just("Subgrupo no encontrado"));
        } else {
            valorParametroMono = grupoRepository.findById(excepcionesClienteDescuento.getIdGrupo())
                    .map(InvGrupo::getNombreGrupo)
                    .switchIfEmpty(Mono.just("Grupo no encontrado"));
        }
        Integer idTipoExcepcion;
        if (excepcionesClienteDescuento.getIdProducto() != null) {
            idTipoExcepcion = TipoExcepcionClienteEnum.PRODUCTO.getIdentification();
        } else if (excepcionesClienteDescuento.getIdSubgrupo() != null) {
            idTipoExcepcion = TipoExcepcionClienteEnum.SUBGRUPO.getIdentification();
        } else {
            idTipoExcepcion = TipoExcepcionClienteEnum.GRUPO.getIdentification();
        }

        Integer idParametro;
        if (excepcionesClienteDescuento.getIdProducto() != null) {
            idParametro = excepcionesClienteDescuento.getIdProducto();
        } else if (excepcionesClienteDescuento.getIdSubgrupo() != null) {
            idParametro = excepcionesClienteDescuento.getIdSubgrupo();
        } else {
            idParametro = excepcionesClienteDescuento.getIdGrupo();
        }
        return valorParametroMono.map(valorParametro ->
                ExcepcionesClienteDescDto.builder()
                        .idExcepcion(excepcionesClienteDescuento.getIdExcepcion())
                        .idCliente(excepcionesClienteDescuento.getIdCliente())
                        .idTipoExcepcion(idTipoExcepcion)
                        .idParametro(idParametro)
                        .valorParametro(valorParametro)
                        .porcionDescuento(excepcionesClienteDescuento.getPorcionDescuento())
                        .fechaInicio(excepcionesClienteDescuento.getFechaInicio())
                        .fechaFin(excepcionesClienteDescuento.getFechaFin())
                        .estado(excepcionesClienteDescuento.getEstado())
                        .build());
    }

    public Flux<ExcepcionesClienteDescDto> saveUpdateAllDto(Flux<ExcepcionesClienteDescDto> excepcionesClienteDescDtoFlux, Long idCliente) {
        return excepcionesClienteDescDtoFlux
                .map(excepcionesClienteDescDto -> mapToEntity(excepcionesClienteDescDto, idCliente))
                .flatMapSequential(excepcionesClienteDescuentoRepository::save)
                .flatMapSequential(this::mapToDto);
    }

    public Flux<ExcepcionesClienteDescuento> getExceptionsByClientId(Long clientId) {
        return excepcionesClienteDescuentoRepository.findAllByIdCliente(clientId);
    }


    public Mono<Boolean> deleteRemovedDetails(List<ExcepcionesClienteDescuento> exceptionsListBDD, List<ExcepcionesClienteDescDto> excepcionesClienteDescDtoList) {
        List<ExcepcionesClienteDescuento> exceptionsListRequest = excepcionesClienteDescDtoList.stream().map(excepcionesClienteDescDto -> mapToEntity(excepcionesClienteDescDto, excepcionesClienteDescDto.getIdCliente())).toList();
        List<ExcepcionesClienteDescuento> exceptionListToDelete = new ArrayList<>();
        for (ExcepcionesClienteDescuento exception : exceptionsListBDD) {
            if (!exceptionsListRequest.contains(exception))
                exceptionListToDelete.add(exception);
        }
        return this.deleteAllExceptions(exceptionListToDelete).then(Mono.just(Boolean.TRUE));
    }

    public Mono<Void> deleteAllExceptions(List<ExcepcionesClienteDescuento> detalleList) {
        return excepcionesClienteDescuentoRepository.deleteAll(detalleList);
    }

    private ExcepcionesClienteDescuento mapToEntity(ExcepcionesClienteDescDto excepcionesClienteDescDto, Long idCliente) {
        Integer idProducto = null;
        Integer idSubgrupo = null;
        Integer idGrupo = null;
        if (excepcionesClienteDescDto.getIdTipoExcepcion().equals(TipoExcepcionClienteEnum.PRODUCTO.getIdentification())) {
            idProducto = excepcionesClienteDescDto.getIdParametro();
        } else if (excepcionesClienteDescDto.getIdTipoExcepcion().equals(TipoExcepcionClienteEnum.SUBGRUPO.getIdentification())) {
            idSubgrupo = excepcionesClienteDescDto.getIdParametro();
        } else {
            idGrupo = excepcionesClienteDescDto.getIdParametro();
        }
        return ExcepcionesClienteDescuento.builder()
                .idExcepcion(excepcionesClienteDescDto.getIdExcepcion())
                .idCliente(idCliente)
                .idProducto(idProducto)
                .idSubgrupo(idSubgrupo)
                .idGrupo(idGrupo)
                .porcionDescuento(excepcionesClienteDescDto.getPorcionDescuento())
                .fechaInicio(excepcionesClienteDescDto.getFechaInicio())
                .fechaFin(excepcionesClienteDescDto.getFechaFin())
                .estado(excepcionesClienteDescDto.getEstado())
                .build();
    }


    @Transactional(readOnly = true)
    public Mono<Paginacion<ExcepcionesClienteDescDto>> getRequestsBySearchParams(Integer clientId, Integer page, Integer limit, String sortDirection, String sortField) {
        Pageable pageable;
        try {
            Sort sort = sortDirection != null && sortField != null ? Sort.by(Sort.Direction.fromString(sortDirection), sortField) : Sort.unsorted();
            pageable = PageRequest.of(page - 1, limit, sort);
        } catch (Exception e) {
            return Mono.error(new CustomException(HttpStatus.BAD_REQUEST, e.getCause() != null ? e.getCause().getMessage() : e.getMessage()));
        }

        Flux<ExcepcionesClienteDescDto> excepcionesClienteDescDtoFlux = excepcionesClienteDescuentoRepository.findByIdClienteAndEstado((long) clientId, Boolean.TRUE, pageable).flatMapSequential(this::mapToDto);
        Mono<Long> totalRegisters = excepcionesClienteDescuentoRepository.countByIdClienteAndEstado((long) clientId, Boolean.TRUE);
        return excepcionesClienteDescDtoFlux.collectList().zipWith(totalRegisters).map(tuple -> new Paginacion<>(tuple.getT1(), page, limit, tuple.getT2().intValue()));
    }


    @Transactional
    public Mono<Void> delete(Integer id) {

        return excepcionesClienteDescuentoRepository.findById(id)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.EXCEPTION_NOT_FOUND + id)))
                .flatMap(excepcionesClienteDescuentoRepository::delete)
                .then();
    }
}
