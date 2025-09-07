package erp.base.api.service;

import erp.base.api.models.TbaBancoCatalogo;
import erp.base.api.repository.TbaBancoCatalogoRepository;
import erp.base.api.utils.Paginacion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TbaBancoCatalogoService {

    private final TbaBancoCatalogoRepository tbaBancoCatalogoRepository;

    public Flux<TbaBancoCatalogo> getBankCatalog() {
        return tbaBancoCatalogoRepository.findAllByEstadoBanco(Boolean.TRUE);
    }

    public Mono<Paginacion<TbaBancoCatalogo>> getRequestBySearchParams(
            String bankCatalogName,
            String bankCatalogCode,
            Integer page,
            Integer limit,
            String sortDirection,
            String sortfield
    ) {
        Flux<TbaBancoCatalogo> bancoCatalogoFlux = tbaBancoCatalogoRepository.getBySearchParams(bankCatalogName, bankCatalogCode, sortfield, sortDirection, limit, (page - 1) * limit);
        Mono<Long> total = tbaBancoCatalogoRepository.getCountBySearchParams(bankCatalogName, bankCatalogCode);
        return total.flatMap(totalCount -> bancoCatalogoFlux.collectList().map(bancoCatalogoList -> new Paginacion<>(bancoCatalogoList, page, limit, totalCount.intValue())));
    }

    public Mono<TbaBancoCatalogo> createCatalogBank(TbaBancoCatalogo catalog) {
        return validateAndSaveCatalog(null, catalog);
    }

    public Mono<TbaBancoCatalogo> updateCatalogBank(Integer id, TbaBancoCatalogo catalog) {
        return validateAndSaveCatalog(id, catalog);
    }

    public Mono<TbaBancoCatalogo> validateAndSaveCatalog(Integer id, TbaBancoCatalogo catalog) {
        Mono<TbaBancoCatalogo> bancoCatalogoMono = (id != null) ? tbaBancoCatalogoRepository.findById(id) : Mono.just(new TbaBancoCatalogo());
        return bancoCatalogoMono
                .flatMap(existingCaja -> {
                    TbaBancoCatalogo newCatalogBank = TbaBancoCatalogo.builder()
                            .idBancoCatalogo(id)
                            .nombreBancoCatalogo(catalog.getNombreBancoCatalogo())
                            .codigoBancoCatalogo(catalog.getCodigoBancoCatalogo())
                            .build();

                    return tbaBancoCatalogoRepository.save(newCatalogBank);
                });
    }
}
