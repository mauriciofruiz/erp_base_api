package erp.base.api.service;


import erp.base.api.constants.ErrorMessage;
import erp.base.api.dto.TbaCuentaBancariaDto;
import erp.base.api.exception.CustomException;
import erp.base.api.models.rrh.RrhCuentaBancaria;
import erp.base.api.repository.rrh.RrhCuentaBancariaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
class TbaCuentaBancariaService {

    private final RrhCuentaBancariaRepository rrhCuentaBancariaRepository;

    private RrhCuentaBancaria rrhCuentaBancaria;

    public Flux<RrhCuentaBancaria> createBankAccountEmployee(List<TbaCuentaBancariaDto> tbaCuentaBancariaDtoList, Integer employeeId) {
        List<RrhCuentaBancaria> listaRrhCuentaBancarias = new ArrayList<>();
        for (TbaCuentaBancariaDto tbaCuentaBancariaDto1 : tbaCuentaBancariaDtoList) {
            rrhCuentaBancaria = RrhCuentaBancaria.builder()
                    .idCuentaBancaria(tbaCuentaBancariaDto1.getIdCuentabancaria())
                    .idEmpleado(employeeId)
                    .idCatalogoBanco(tbaCuentaBancariaDto1.getIdBancocatalogo())
                    .idTipoCuentaBancaria(tbaCuentaBancariaDto1.getIdTipocuentabancaria())
                    .numeroCuentaBancaria(tbaCuentaBancariaDto1.getNumeroCuentabancaria())
                    .estadoCuentaBancaria(tbaCuentaBancariaDto1.getEstadoCuentabancaria())
                    .build();
            listaRrhCuentaBancarias.add(rrhCuentaBancaria);

        }
        return rrhCuentaBancariaRepository.saveAll(listaRrhCuentaBancarias);
    }
}
