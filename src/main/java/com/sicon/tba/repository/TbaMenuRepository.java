package com.sicon.tba.repository;

import com.sicon.tba.models.TbaMenu;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TbaMenuRepository extends ReactiveCrudRepository<TbaMenu, Integer> {
    Flux<TbaMenu> findByPadreMenu(Integer padreMenu);
}
