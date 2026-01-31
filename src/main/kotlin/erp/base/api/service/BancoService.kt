package erp.base.api.service

import erp.base.api.constants.ErrorMessage
import erp.base.api.dto.BancoDto
import erp.base.api.exception.CustomException
import erp.base.api.models.Banco
import erp.base.api.repository.BancoRepository
import erp.base.api.repository.cont.CuentaRepository
import erp.base.api.repository.TipoCuentaBancariaRepository
import erp.base.api.utils.Paginacion
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class BancoService(
    private val tbaBancoRepository: BancoRepository,
    private val tbaTipoCuentaBancariaRepository: TipoCuentaBancariaRepository,
    private val conCuentaRepository: CuentaRepository
) {

    fun createBank(tbaBancoDto: BancoDto): Mono<Banco> {
        val existsCount = conCuentaRepository.findById(tbaBancoDto.idCuenta!!).hasElement()
        val existsKindCountBank = tbaTipoCuentaBancariaRepository.findById(tbaBancoDto.idTipoCuentaBancaria!!).hasElement()
        return existsCount.flatMap { existCount ->
            if (existCount) {
                existsKindCountBank.flatMap { existCountBanck ->
                    if (existCountBanck) {
                        tbaBancoRepository.save(
                            Banco(
                                idBanco = null,
                                idCuenta = tbaBancoDto.idCuenta,
                                idTipoCuentaBancaria = tbaBancoDto.idTipoCuentaBancaria,
                                nombreBanco = tbaBancoDto.nombreBanco,
                                numeroCuentaBanco = tbaBancoDto.numeroCuentaBanco,
                                saldoDisponibleBanco = tbaBancoDto.saldoDisponibleBanco,
                                saldoContableBanco = tbaBancoDto.saldoContableBanco,
                                contactoBanco = tbaBancoDto.contactoBanco,
                                tituloContactoBanco = tbaBancoDto.tituloContactoBanco,
                                telefonoContactoBanco = tbaBancoDto.telefonoContactoBanco,
                                numeroChequeImpresoBanco = tbaBancoDto.numeroChequeImpresoBanco,
                                ultimoNumeroChequeBanco = tbaBancoDto.ultimoNumeroChequeBanco,
                                esCajaChicaBanco = tbaBancoDto.esCajaChicaBanco,
                                estadoBanco = tbaBancoDto.estadoBanco,
                                chequeRecuperadoBanco = null,
                                textoDepositoBanco = tbaBancoDto.textoDepositoBanco,
                                textoTarjetaBanco = tbaBancoDto.textoTarjetaBanco,
                                saldoInicialBanco = tbaBancoDto.saldoInicialBanco,
                                idSucursal = tbaBancoDto.idSucursal
                            )
                        )
                    } else Mono.error(CustomException(HttpStatus.BAD_REQUEST, ErrorMessage.BANK_ACCOUNT_TYPE_NOT_FOUND + tbaBancoDto.idTipoCuentaBancaria))
                }
            } else Mono.error(CustomException(HttpStatus.BAD_REQUEST, ErrorMessage.COUNT_NOT_FOUND + tbaBancoDto.idCuenta))
        }
    }

    fun updateBank(id: Int, tbaBancoDto: BancoDto): Mono<Banco> {
        val existsBank = tbaBancoRepository.existsById(id)
        return existsBank.flatMap { existBank ->
            if (existBank) {
                tbaBancoRepository.save(
                    Banco(
                        idBanco = id,
                        idCuenta = tbaBancoDto.idCuenta,
                        idTipoCuentaBancaria = tbaBancoDto.idTipoCuentaBancaria,
                        nombreBanco = tbaBancoDto.nombreBanco,
                        numeroCuentaBanco = tbaBancoDto.numeroCuentaBanco,
                        saldoDisponibleBanco = tbaBancoDto.saldoDisponibleBanco,
                        saldoContableBanco = tbaBancoDto.saldoContableBanco,
                        contactoBanco = tbaBancoDto.contactoBanco,
                        tituloContactoBanco = tbaBancoDto.tituloContactoBanco,
                        telefonoContactoBanco = tbaBancoDto.telefonoContactoBanco,
                        numeroChequeImpresoBanco = tbaBancoDto.numeroChequeImpresoBanco,
                        ultimoNumeroChequeBanco = tbaBancoDto.ultimoNumeroChequeBanco,
                        esCajaChicaBanco = tbaBancoDto.esCajaChicaBanco,
                        estadoBanco = tbaBancoDto.estadoBanco,
                        chequeRecuperadoBanco = null,
                        textoDepositoBanco = tbaBancoDto.textoDepositoBanco,
                        textoTarjetaBanco = tbaBancoDto.textoTarjetaBanco,
                        saldoInicialBanco = tbaBancoDto.saldoInicialBanco,
                        idSucursal = tbaBancoDto.idSucursal
                    )
                )
            } else Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.BANK_NOT_FOUND + id))
        }
    }

    fun getById(id: Int): Mono<Banco> =
        tbaBancoRepository.findById(id).switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.BANK_NOT_FOUND + id)))

    fun getBankByParams(nameBank: String?, numCount: String?, page: Int, limit: Int): Mono<Paginacion<Banco>> {
        val isSearchByNameBank = nameBank != null && numCount == null
        val isSearchByNumCount = nameBank == null && numCount != null
        val pageable: Pageable = PageRequest.of(page - 1, limit)
        val (totalRegisters, tbaBancoFlux) = when {
            isSearchByNameBank -> tbaBancoRepository.countByNombreBanco(nameBank) to tbaBancoRepository.findByNombreBancoContaining(nameBank)
            isSearchByNumCount -> tbaBancoRepository.countByNumeroCuentaBanco(numCount) to tbaBancoRepository.findByNumeroCuentaBancoContaining(numCount)
            else -> tbaBancoRepository.count() to tbaBancoRepository.findAllBy(pageable)
        }
        return totalRegisters.flatMap { total ->
            tbaBancoFlux.collectList().map { tbaBancos -> Paginacion(tbaBancos, page, limit, total.toInt()) }
        }
    }

    fun getBankList(): Flux<Banco> =
        tbaBancoRepository.findByEstadoBanco(true)

    fun getBankListByIdSucursal(idSucursal: Int): Flux<Banco> =
        tbaBancoRepository.findByIdSucursalAndEstadoBanco(idSucursal, true)

    fun getBankListIsBank(): Flux<Banco> =
        tbaBancoRepository.findByEstadoBancoAndEsCajaChicaBanco(true, false)

    fun getLastCheckBank(bankId: Int): Mono<Int> =
        tbaBancoRepository.getLastNumberCheckBank(bankId, true).map { it ?: 0 }

    fun getRequestBySearchParams(
        nameBank: String?,
        numCount: String?,
        page: Int,
        limit: Int,
        sortDirection: String?,
        sortfield: String?
    ): Mono<Paginacion<Banco>> {
        val bancoFlux = tbaBancoRepository.getBySearchParams(nameBank, numCount, sortfield, sortDirection, limit, (page - 1) * limit)
        val total = tbaBancoRepository.getCountBySearchParams(nameBank, numCount)
        return total.flatMap { totalCount -> bancoFlux.collectList().map { bancoList -> Paginacion(bancoList, page, limit, totalCount.toInt()) } }
    }
}
