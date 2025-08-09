package com.sicon.tba.repository.cont;

import com.sicon.tba.dto.ConCuentaDatosContablesDto;
import com.sicon.tba.models.ConCuenta;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ConCuentaRepository extends ReactiveCrudRepository<ConCuenta, Integer> {

    @Query("SELECT CE.ID_EMPLEADOCUENTA, CE.ID_EMPLEADO, CE.ID_CUENTA, C.ID_TIPOCUENTA," +
            " C.NUMERO_CUENTA, C.NOMBRE_CUENTA, C.NUMERO_CUENTAPADRE" +
            " FROM CON_EMPLEADOCUENTA CE" +
            " JOIN CON_CUENTA C ON C.ID_CUENTA = CE.ID_CUENTA" +
            " WHERE CE.ID_EMPLEADO =:idEmpleado")
   Flux<ConCuentaDatosContablesDto> getDatosContables(Integer idEmpleado);

    Flux<ConCuenta> findByNumeroCuenta(String numeroCuenta);

}
