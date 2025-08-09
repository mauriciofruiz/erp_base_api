package com.sicon.tba.repository;

import com.sicon.tba.models.TbaDocumentosElectronicos;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaDocumentosElectronicosRepository extends ReactiveCrudRepository<TbaDocumentosElectronicos, Integer> {
}
