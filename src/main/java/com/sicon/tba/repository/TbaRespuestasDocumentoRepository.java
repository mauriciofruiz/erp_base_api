package com.sicon.tba.repository;

import com.sicon.tba.models.TbaRespuestasDocumento;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaRespuestasDocumentoRepository extends ReactiveCrudRepository<TbaRespuestasDocumento, Integer> {
}
