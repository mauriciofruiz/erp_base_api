package erp.base.api.service

import erp.base.api.dto.CuentaBancariaDto
import erp.base.api.models.rrh.CuentaBancaria
import erp.base.api.repository.rrh.CuentaBancariaRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class CuentaBancariaService(private val rrhCuentaBancariaRepository: CuentaBancariaRepository) {

    fun createBankAccountEmployee(tbaCuentaBancariaDtoList: List<CuentaBancariaDto>, employeeId: Int): Flux<CuentaBancaria> {
        val listaRrhCuentaBancarias = tbaCuentaBancariaDtoList.map { dto ->
            CuentaBancaria(
                idCuentaBancaria = dto.idCuentabancaria,
                idCatalogoBanco = dto.idBancocatalogo,
                idEmpleado = employeeId,
                idTipoCuentaBancaria = dto.idTipocuentabancaria,
                numeroCuentaBancaria = dto.numeroCuentabancaria,
                estadoCuentaBancaria = dto.estadoCuentabancaria
            )
        }
        return rrhCuentaBancariaRepository.saveAll(listaRrhCuentaBancarias)
    }
}
