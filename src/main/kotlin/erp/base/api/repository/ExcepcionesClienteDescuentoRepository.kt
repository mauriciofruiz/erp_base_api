package erp.base.api.repository

import erp.base.api.models.ExcepcionesClienteDescuento
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface ExcepcionesClienteDescuentoRepository : ReactiveCrudRepository<ExcepcionesClienteDescuento, Int> {

    fun findAllByIdCliente(idCliente: Long): Flux<ExcepcionesClienteDescuento>

    fun findByIdClienteAndEstado(idCliente: Long, estado: Boolean, pageable: Pageable): Flux<ExcepcionesClienteDescuento>

    fun countByIdClienteAndEstado(idCliente: Long, estado: Boolean): Mono<Long>
}
