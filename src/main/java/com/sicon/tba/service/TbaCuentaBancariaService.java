package com.sicon.tba.service;


import com.sicon.tba.constants.ErrorMessage;
import com.sicon.tba.dto.TbaCuentaBancariaDto;
import com.sicon.tba.exception.CustomException;
import com.sicon.tba.models.rrh.RrhCuentaBancaria;
import com.sicon.tba.repository.rrh.RrhCuentaBancariaRepository;
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
