package com.sicon.tba.repository;

import com.sicon.tba.models.VtsFactura;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VtsFacturaRepository extends ReactiveCrudRepository<VtsFactura, Integer> {
}
