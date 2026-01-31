package erp.base.api.service

import erp.base.api.enums.Multidimensional
import erp.base.api.models.Multidimension
import erp.base.api.repository.cont.MultidimensionRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class MultidimensionService(private val conMultidimensionRepository: MultidimensionRepository) {

    fun createMultidimensionEmployee(idEmployee: Int): Mono<Multidimension> =
        conMultidimensionRepository.save(
            Multidimension(
                idMultidimension = null,
                tipoMultidimension = Multidimensional.EMPLOYEE.displayName,
                valorMultidimension = idEmployee
            )
        )
}
