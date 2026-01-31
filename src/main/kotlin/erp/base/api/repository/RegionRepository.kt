package erp.base.api.repository

import erp.base.api.models.Region
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface RegionRepository : ReactiveCrudRepository<Region, Int> {

    fun findByNombreRegionContaining(nombreRegion: String): Flux<Region>
}
