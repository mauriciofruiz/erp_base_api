package com.sicon.tba.repository;

import com.sicon.tba.models.ImpuestoDocumento;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ImpuestoDocumentoRepository extends ReactiveCrudRepository<ImpuestoDocumento, Integer> {

    Flux<ImpuestoDocumento> findAllByIdDocumentoAndEstadoImpuesto(Long idDocumento, Boolean estadoImpuesto);

    Flux<ImpuestoDocumento> findAllByIdTipoDocumentoAndIdVoucherAndEstadoImpuesto(Integer idTipoDocumento, Integer idVoucher, Boolean estadoImpuesto);
}