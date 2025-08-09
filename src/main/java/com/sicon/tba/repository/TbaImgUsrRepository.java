package com.sicon.tba.repository;

import com.sicon.tba.models.TbaImgUsr;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbaImgUsrRepository extends ReactiveCrudRepository<TbaImgUsr, Integer> {
}
