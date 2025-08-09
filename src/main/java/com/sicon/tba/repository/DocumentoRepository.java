package com.sicon.tba.repository;

import com.sicon.tba.models.Documento;
import com.sicon.tba.repository.custom.DocumentoRepositoryCustom;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface DocumentoRepository extends ReactiveCrudRepository<Documento, Integer>, DocumentoRepositoryCustom {
}
