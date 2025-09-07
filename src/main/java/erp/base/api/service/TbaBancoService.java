package erp.base.api.service;

import erp.base.api.constants.ErrorMessage;
import erp.base.api.dto.TbaBancoDto;
import erp.base.api.exception.CustomException;
import erp.base.api.models.Periodo;
import erp.base.api.models.TbaBanco;
import erp.base.api.repository.cont.ConCuentaRepository;
import erp.base.api.repository.TbaBancoRepository;
import erp.base.api.repository.TbaTipoCuentaBancariaRepository;
import erp.base.api.utils.Paginacion;
import erp.base.api.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class TbaBancoService {

    private final TbaBancoRepository tbaBancoRepository;
    private final TbaTipoCuentaBancariaRepository tbaTipoCuentaBancariaRepository;
    private final ConCuentaRepository conCuentaRepository;

    /**
     * Método para nuevos Bancos
     *
     * @return Objeto con los nuevos datos del Banco
     * @Param tbaBanco Objeto con los datos del Banco
     */
    public Mono<TbaBanco> createBank(TbaBancoDto tbaBancoDto) {
        Mono<Boolean> existsCount = conCuentaRepository.findById(tbaBancoDto.getIdCuenta()).hasElement();
        Mono<Boolean> existsKindCountBank = tbaTipoCuentaBancariaRepository.findById(tbaBancoDto.getIdTipoCuentaBancaria()).hasElement();

        return existsCount.flatMap(existCount ->
                existCount ? existsKindCountBank.flatMap(existCountBanck ->
                        existCountBanck ? tbaBancoRepository.save(
                                TbaBanco.builder()
                                        .idCuenta(tbaBancoDto.getIdCuenta())
                                        .idTipoCuentaBancaria(tbaBancoDto.getIdTipoCuentaBancaria())
                                        .nombreBanco(tbaBancoDto.getNombreBanco())
                                        .numeroCuentaBanco(tbaBancoDto.getNumeroCuentaBanco())
                                        .saldoDisponibleBanco(tbaBancoDto.getSaldoDisponibleBanco())
                                        .saldoContableBanco(tbaBancoDto.getSaldoContableBanco())
                                        .contactoBanco(tbaBancoDto.getContactoBanco())
                                        .tituloContactoBanco(tbaBancoDto.getTituloContactoBanco())
                                        .telefonoContactoBanco(tbaBancoDto.getTelefonoContactoBanco())
                                        .numeroChequeImpresoBanco(tbaBancoDto.getNumeroChequeImpresoBanco())
                                        .ultimoNumeroChequeBanco(tbaBancoDto.getUltimoNumeroChequeBanco())
                                        .esCajaChicaBanco(tbaBancoDto.getEsCajaChicaBanco())
                                        .estadoBanco(tbaBancoDto.getEstadoBanco())
                                        .textoDepositoBanco(tbaBancoDto.getTextoDepositoBanco())
                                        .textoTarjetaBanco(tbaBancoDto.getTextoTarjetaBanco())
                                        .saldoInicialBanco(tbaBancoDto.getSaldoInicialBanco())
                                        .idSucursal(tbaBancoDto.getIdSucursal())
                                        .build()

                        ) : Mono.error(new CustomException(HttpStatus.BAD_REQUEST, ErrorMessage.BANK_ACCOUNT_TYPE_NOT_FOUND + tbaBancoDto.getIdTipoCuentaBancaria())))
                        : Mono.error(new CustomException(HttpStatus.BAD_REQUEST, ErrorMessage.COUNT_NOT_FOUND + tbaBancoDto.getIdCuenta()))
        );

    }

    /**
     * Método para actualizar Banco
     *
     * @return Objeto con los nuevos datos del Banco
     * @Param tbaBanco Objeto con los datos del Banco actualizado
     */
    public Mono<TbaBanco> updateBank(Integer id, TbaBancoDto tbaBancoDto) {
        Mono<Boolean> existsBank = tbaBancoRepository.existsById(id);

        return existsBank.flatMap(existBank -> existBank ?
                tbaBancoRepository.save(
                        TbaBanco.builder()
                                .idBanco(id)
                                .idCuenta(tbaBancoDto.getIdCuenta())
                                .idTipoCuentaBancaria(tbaBancoDto.getIdTipoCuentaBancaria())
                                .nombreBanco(tbaBancoDto.getNombreBanco())
                                .numeroCuentaBanco(tbaBancoDto.getNumeroCuentaBanco())
                                .saldoDisponibleBanco(tbaBancoDto.getSaldoDisponibleBanco())
                                .saldoContableBanco(tbaBancoDto.getSaldoContableBanco())
                                .contactoBanco(tbaBancoDto.getContactoBanco())
                                .tituloContactoBanco(tbaBancoDto.getTituloContactoBanco())
                                .telefonoContactoBanco(tbaBancoDto.getTelefonoContactoBanco())
                                .numeroChequeImpresoBanco(tbaBancoDto.getNumeroChequeImpresoBanco())
                                .ultimoNumeroChequeBanco(tbaBancoDto.getUltimoNumeroChequeBanco())
                                .esCajaChicaBanco(tbaBancoDto.getEsCajaChicaBanco())
                                .estadoBanco(tbaBancoDto.getEstadoBanco())
                                .textoDepositoBanco(tbaBancoDto.getTextoDepositoBanco())
                                .textoTarjetaBanco(tbaBancoDto.getTextoTarjetaBanco())
                                .saldoInicialBanco(tbaBancoDto.getSaldoInicialBanco())
                                .idSucursal(tbaBancoDto.getIdSucursal())
                                .build()
                )
                : Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.BANK_NOT_FOUND + id))
        );

    }

    /**
     * Método para obtener El banco por Id
     *
     * @return Objeto con los datos del Banco a buscar
     * @Param id del Banco a buscar
     */
    public Mono<TbaBanco> getById(Integer id) {
        return tbaBancoRepository.findById(id).switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.BANK_NOT_FOUND + id)));

    }


    /**
     * Método para encontrar los Bancos por parametros
     *
     * @return Objeto con los nuevos datos del Banco
     * @Param
     */
    public Mono<Paginacion<TbaBanco>> getBankByParams(String nameBank, String numCount, Integer page, Integer limit) {
        Mono<Long> totalRegisters = null;
        Flux<TbaBanco> tbaBancoFlux;
        boolean isSearchByNameBank = nameBank != null && numCount == null;
        boolean isSearchByNumCount = nameBank == null && numCount != null;

        Pageable pageable = PageRequest.of(page - 1, limit);

        if (isSearchByNameBank) {
            totalRegisters = tbaBancoRepository.countByNombreBanco(nameBank);
            tbaBancoFlux = tbaBancoRepository.findByNombreBancoContaining(nameBank);
        } else if (isSearchByNumCount) {
            totalRegisters = tbaBancoRepository.countByNumeroCuentaBanco(numCount);
            tbaBancoFlux = tbaBancoRepository.findByNumeroCuentaBancoContaining(numCount);
        } else {
            totalRegisters = tbaBancoRepository.count();
            tbaBancoFlux = tbaBancoRepository.findAllBy(pageable);

        }
        return totalRegisters.flatMap(total -> tbaBancoFlux.collectList().map(tbaBancos ->
                new Paginacion<>(tbaBancos, page, limit, total.intValue())
        ));

    }

    public Flux<TbaBanco> getBankList() {
        return tbaBancoRepository.findByEstadoBanco(Boolean.TRUE);
    }

    public Flux<TbaBanco> getBankListByIdSucursal(Integer idSucursal) {
        return tbaBancoRepository.findByIdSucursalAndEstadoBanco(idSucursal, Boolean.TRUE);
    }

    public Flux<TbaBanco> getBankListIsBank() {
        return tbaBancoRepository.findByEstadoBancoAndEsCajaChicaBanco(Boolean.TRUE, Boolean.FALSE);
    }

    public Mono<Integer> getLastCheckBank(Integer bankId) {
        return tbaBancoRepository.getLastNumberCheckBank(bankId, true);
    }

    public Mono<Paginacion<TbaBanco>> getRequestBySearchParams(
            String nameBank,
            String numCount,
            Integer page,
            Integer limit,
            String sortDirection,
            String sortfield
    ) {
        Flux<TbaBanco> bancoFlux = tbaBancoRepository.getBySearchParams(nameBank, numCount, sortfield, sortDirection, limit, (page - 1) * limit);
        Mono<Long> total = tbaBancoRepository.getCountBySearchParams(nameBank, numCount);
        return total.flatMap(totalCount -> bancoFlux.collectList().map(bancoList -> new Paginacion<>(bancoList, page, limit, totalCount.intValue())));
    }
}
