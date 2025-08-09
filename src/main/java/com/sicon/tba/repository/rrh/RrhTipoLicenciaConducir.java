package com.sicon.tba.repository.rrh;

import io.r2dbc.spi.Parameter;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RrhTipoLicenciaConducir extends ReactiveCrudRepository<RrhTipoLicenciaConducir, Integer> {
}
