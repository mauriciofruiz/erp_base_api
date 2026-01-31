package erp.base.api.repository

import erp.base.api.models.Menu
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface MenuRepository : ReactiveCrudRepository<Menu, Int> {

    fun findByPadreMenu(padreMenu: Int): Flux<Menu>
}
