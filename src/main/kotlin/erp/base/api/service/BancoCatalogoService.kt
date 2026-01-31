package erp.base.api.service

import erp.base.api.models.BancoCatalogo
import erp.base.api.repository.BancoCatalogoRepository
import erp.base.api.utils.Paginacion
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class BancoCatalogoService(private val tbaBancoCatalogoRepository: BancoCatalogoRepository) {

    fun getBankCatalog(): Flux<BancoCatalogo> =
        tbaBancoCatalogoRepository.findAllByEstadoBanco(true)

    fun getRequestBySearchParams(
        bankCatalogName: String?,
        bankCatalogCode: String?,
        page: Int,
        limit: Int,
        sortDirection: String?,
        sortfield: String?
    ): Mono<Paginacion<BancoCatalogo>> {
        val bancoCatalogoFlux = tbaBancoCatalogoRepository.getBySearchParams(
            bankCatalogName, bankCatalogCode, sortfield, sortDirection, limit, (page - 1) * limit
        )
        val total = tbaBancoCatalogoRepository.getCountBySearchParams(bankCatalogName, bankCatalogCode)
        return total.flatMap { totalCount ->
            bancoCatalogoFlux.collectList().map { bancoCatalogoList ->
                Paginacion(bancoCatalogoList, page, limit, totalCount.toInt())
            }
        }
    }

    fun createCatalogBank(catalog: BancoCatalogo): Mono<BancoCatalogo> =
        validateAndSaveCatalog(null, catalog)

    fun updateCatalogBank(id: Int, catalog: BancoCatalogo): Mono<BancoCatalogo> =
        validateAndSaveCatalog(id, catalog)

    fun validateAndSaveCatalog(id: Int?, catalog: BancoCatalogo): Mono<BancoCatalogo> {
        val bancoCatalogoMono = if (id != null) tbaBancoCatalogoRepository.findById(id) else Mono.just(BancoCatalogo())
        return bancoCatalogoMono.flatMap {
            val newCatalogBank = BancoCatalogo(
                idBancoCatalogo = id,
                nombreBancoCatalogo = catalog.nombreBancoCatalogo,
                codigoBancoCatalogo = catalog.codigoBancoCatalogo,
                estadoBanco = null,
                idUsuarioCreacion = null,
                idUsuarioModificacion = null,
                fechaCreacion = null,
                fechaModificacion = null
            )
            tbaBancoCatalogoRepository.save(newCatalogBank)
        }
    }
}
