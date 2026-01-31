package erp.base.api.repository

import erp.base.api.models.Cliente
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface ClienteRepository : ReactiveCrudRepository<Cliente, Int> {

    fun findByIdPersona(idPersona: Int): Mono<Cliente>
    fun findAllBy(pageable: Pageable): Flux<Cliente>
    fun findByNombreComercialClienteContaining(nombreComercialCliente: String, pageable: Pageable): Flux<Cliente>
    fun findByIdCategoriaCliente(idCategoriaCliente: Int, pageable: Pageable): Flux<Cliente>

    fun countByIdCategoriaCliente(idCategoriaCliente: Int): Mono<Long>
    fun countByNombreComercialCliente(nombreComercialCliente: String): Mono<Long>
}
