package erp.base.api.service;

import erp.base.api.constants.ErrorMessage;
import erp.base.api.exception.CustomException;
import erp.base.api.models.Sucursal;
import erp.base.api.models.TbaCategoriaCliente;
import erp.base.api.repository.TbaCategoriaClienteRepository;
import erp.base.api.utils.Paginacion;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TbaCategoriaClienteService {

    private final TbaCategoriaClienteRepository tbaCategoriaClienteRepository;

    public Mono<TbaCategoriaCliente> getCategoryClientById(Integer id) {
        return tbaCategoriaClienteRepository.findById(id);
    }

    public Flux<TbaCategoriaCliente> getAllCategoryClient() {
        return tbaCategoriaClienteRepository.findByCategoriaEspecialCategoriaClienteAndActivoCategoriaCliente(false, true)
                .map(categoria -> {
                    categoria.setDescuentoCategoriaCliente(categoria.getDescuentoCategoriaCliente().multiply(new BigDecimal(100)));
                    return categoria;
                });
    }

    public Mono<Paginacion<TbaCategoriaCliente>> getRequestBySearchParams(
            String name,
            Integer page,
            Integer limit,
            String sortDirection,
            String sortfield
    ) {
        Flux<TbaCategoriaCliente> categoriaClienteFlux = tbaCategoriaClienteRepository.getBySearchParams(name, sortfield, sortDirection, limit, (page - 1) * limit);
        Mono<Long> total = tbaCategoriaClienteRepository.getCountBySearchParams(name);
        return total.flatMap(totalCount -> categoriaClienteFlux.collectList().map(categoriaClientes -> new Paginacion<>(categoriaClientes, page, limit, totalCount.intValue())));
    }

    public Mono<TbaCategoriaCliente> createClientCategory(TbaCategoriaCliente clientCategoryDto) {
        BigDecimal descuentoCategoriaCliente = clientCategoryDto.getDescuentoCategoriaCliente();
        BigDecimal descuentoCategoriaClienteEnPorcentaje = descuentoCategoriaCliente.divide(new BigDecimal("100"));
        TbaCategoriaCliente newCategoriaCliente = TbaCategoriaCliente.builder()
                .nombreCategoriaCliente(clientCategoryDto.getNombreCategoriaCliente())
                .descuentoCategoriaCliente(descuentoCategoriaClienteEnPorcentaje)
                .categoriaEspecialCategoriaCliente(clientCategoryDto.getCategoriaEspecialCategoriaCliente() != null ? clientCategoryDto.getCategoriaEspecialCategoriaCliente() : Boolean.FALSE)
                .activoCategoriaCliente(clientCategoryDto.getActivoCategoriaCliente() != null ? clientCategoryDto.getActivoCategoriaCliente() : Boolean.TRUE)
                .build();

        return tbaCategoriaClienteRepository.save(newCategoriaCliente);
    }

    public Mono<TbaCategoriaCliente> updateClientCategory(Integer id, TbaCategoriaCliente clientCategoryDto) {
        BigDecimal descuentoCategoriaCliente = clientCategoryDto.getDescuentoCategoriaCliente();
        BigDecimal descuentoCategoriaClienteEnPorcentaje = descuentoCategoriaCliente.divide(new BigDecimal("100"));
        return tbaCategoriaClienteRepository.findById(id)
                .flatMap(existingCategoriaCliente -> {
                    existingCategoriaCliente.setNombreCategoriaCliente(clientCategoryDto.getNombreCategoriaCliente());
                    existingCategoriaCliente.setDescuentoCategoriaCliente(descuentoCategoriaClienteEnPorcentaje);
                    existingCategoriaCliente.setCategoriaEspecialCategoriaCliente(clientCategoryDto.getCategoriaEspecialCategoriaCliente() != null ? clientCategoryDto.getCategoriaEspecialCategoriaCliente() : Boolean.FALSE);
                    existingCategoriaCliente.setActivoCategoriaCliente(clientCategoryDto.getActivoCategoriaCliente() != null ? clientCategoryDto.getActivoCategoriaCliente() : Boolean.TRUE);
                    return tbaCategoriaClienteRepository.save(existingCategoriaCliente);
                })
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.CATEGORY_CLIENT_NOT_FOUND + id)));
    }
}
