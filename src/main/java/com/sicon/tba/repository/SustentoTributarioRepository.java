package com.sicon.tba.repository;

import com.sicon.tba.models.SustentoTributario;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SustentoTributarioRepository extends ReactiveCrudRepository<SustentoTributario, Integer> {
}
