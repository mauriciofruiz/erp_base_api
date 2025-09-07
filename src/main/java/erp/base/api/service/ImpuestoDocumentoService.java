package erp.base.api.service;

import erp.base.api.constants.ErrorMessage;
import erp.base.api.exception.CustomException;
import erp.base.api.models.ImpuestoDocumento;
import erp.base.api.models.Rol;
import erp.base.api.repository.ImpuestoDocumentoRepository;
import erp.base.api.repository.RolRepository;
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
