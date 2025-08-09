package com.sicon.tba.service;

import com.sicon.tba.constants.ErrorMessage;
import com.sicon.tba.exception.CustomException;
import com.sicon.tba.models.ImpuestoDocumento;
import com.sicon.tba.models.Rol;
import com.sicon.tba.repository.ImpuestoDocumentoRepository;
import com.sicon.tba.repository.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ImpuestoDocumentoService {

    private final ImpuestoDocumentoRepository impuestoDocumentoRepository;

    public Flux<ImpuestoDocumento> impuestoDocumentoByIdDocumento(Long idDocumento) {
        return impuestoDocumentoRepository.findAllByIdDocumentoAndEstadoImpuesto(idDocumento, true);
    }

    public Flux<ImpuestoDocumento> impuestoDocumentoByIdTipoDocumentoAndIdVoucher(Integer idTipoDocumento, Integer idVoucher) {
        return impuestoDocumentoRepository.findAllByIdTipoDocumentoAndIdVoucherAndEstadoImpuesto(idTipoDocumento, idVoucher, true);
    }

}
