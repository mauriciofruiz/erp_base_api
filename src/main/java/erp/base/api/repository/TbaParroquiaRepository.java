package erp.base.api.repository;

import erp.base.api.dto.TbaParroquiaDto;
import erp.base.api.models.TbaParroquia;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface TbaParroquiaRepository extends ReactiveCrudRepository<TbaParroquia, Integer> {

    @Query(" SELECT  TP.ID_PARROQUIA, TP.ID_CIUDAD, TP.NOMBRE_PARROQUIA FROM PARROQUIA TP" +
            " WHERE TP.NOMBRE_PARROQUIA LIKE CONCAT('%', :nombreParroquia, '%')")
    Flux<TbaParroquiaDto> findParishByName(String nombreParroquia);


    Flux<TbaParroquia> findByIdCiudad(Integer idCiudad);
}
