package erp.base.api.service

import erp.base.api.constants.ErrorMessage
import erp.base.api.exception.CustomException
import erp.base.api.models.CategoriaCliente
import erp.base.api.repository.CategoriaClienteRepository
import erp.base.api.utils.Paginacion
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.math.BigDecimal

@Service
class CategoriaClienteService(private val tbaCategoriaClienteRepository: CategoriaClienteRepository) {

    fun getCategoryClientById(id: Int): Mono<CategoriaCliente> =
        tbaCategoriaClienteRepository.findById(id)

    fun getAllCategoryClient(): Flux<CategoriaCliente> =
        tbaCategoriaClienteRepository.findByCategoriaEspecialCategoriaClienteAndActivoCategoriaCliente(false, true)
            .map { categoria ->
                categoria.descuentoCategoriaCliente = categoria.descuentoCategoriaCliente?.multiply(BigDecimal(100))
                categoria
            }

    fun getRequestBySearchParams(
        name: String?,
        page: Int,
        limit: Int,
        sortDirection: String?,
        sortfield: String?
    ): Mono<Paginacion<CategoriaCliente>> {
        val categoriaClienteFlux = tbaCategoriaClienteRepository.getBySearchParams(name, sortfield, sortDirection, limit, (page - 1) * limit)
        val total = tbaCategoriaClienteRepository.getCountBySearchParams(name)
        return total.flatMap { totalCount -> categoriaClienteFlux.collectList().map { categoriaClientes -> Paginacion(categoriaClientes, page, limit, totalCount.toInt()) } }
    }

    fun createClientCategory(clientCategoryDto: CategoriaCliente): Mono<CategoriaCliente> {
        val descuentoCategoriaCliente = clientCategoryDto.descuentoCategoriaCliente
        val descuentoCategoriaClienteEnPorcentaje = descuentoCategoriaCliente?.divide(BigDecimal("100"))
        val newCategoriaCliente = CategoriaCliente(
            idCategoriaCliente = null,
            nombreCategoriaCliente = clientCategoryDto.nombreCategoriaCliente,
            descuentoCategoriaCliente = descuentoCategoriaClienteEnPorcentaje,
            categoriaEspecialCategoriaCliente = clientCategoryDto.categoriaEspecialCategoriaCliente ?: false,
            activoCategoriaCliente = clientCategoryDto.activoCategoriaCliente ?: true
        )
        return tbaCategoriaClienteRepository.save(newCategoriaCliente)
    }

    fun updateClientCategory(id: Int, clientCategoryDto: CategoriaCliente): Mono<CategoriaCliente> {
        val descuentoCategoriaCliente = clientCategoryDto.descuentoCategoriaCliente
        val descuentoCategoriaClienteEnPorcentaje = descuentoCategoriaCliente?.divide(BigDecimal("100"))
        return tbaCategoriaClienteRepository.findById(id)
            .flatMap { existingCategoriaCliente ->
                existingCategoriaCliente.nombreCategoriaCliente = clientCategoryDto.nombreCategoriaCliente
                existingCategoriaCliente.descuentoCategoriaCliente = descuentoCategoriaClienteEnPorcentaje
                existingCategoriaCliente.categoriaEspecialCategoriaCliente = clientCategoryDto.categoriaEspecialCategoriaCliente ?: false
                existingCategoriaCliente.activoCategoriaCliente = clientCategoryDto.activoCategoriaCliente ?: true
                tbaCategoriaClienteRepository.save(existingCategoriaCliente)
            }
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.CATEGORY_CLIENT_NOT_FOUND + id)))
    }
}
