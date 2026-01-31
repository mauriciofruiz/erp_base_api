package erp.base.api.service

import erp.base.api.models.Empresa
import erp.base.api.repository.EmpresaRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class EmpresaService(private val tbaEmpresaRepository: EmpresaRepository) {

    fun findById(id: Long): Mono<Empresa> =
        tbaEmpresaRepository.findById(id)

    fun save(tbaEmpresa: Empresa): Mono<Void> =
        tbaEmpresaRepository.save(tbaEmpresa).then()
}
