package erp.base.api.repository

import erp.base.api.models.TipoVehiculo
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TipoVehiculoRepository : ReactiveCrudRepository<TipoVehiculo, Int>
