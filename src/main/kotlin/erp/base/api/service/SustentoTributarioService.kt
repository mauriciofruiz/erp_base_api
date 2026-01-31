package erp.base.api.service

import erp.base.api.models.SustentoTributario
import erp.base.api.repository.SustentoTributarioRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class SustentoTributarioService(private val sustentoTributarioRepository: SustentoTributarioRepository) {

    fun getAllSustentoTributario(): Flux<SustentoTributario> =
        sustentoTributarioRepository.findAll()
}
