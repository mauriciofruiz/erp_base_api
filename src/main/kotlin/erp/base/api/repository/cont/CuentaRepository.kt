package erp.base.api.repository.cont

import erp.base.api.dto.CuentaDatosContablesDto
import erp.base.api.models.Cuenta
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface CuentaRepository : ReactiveCrudRepository<Cuenta, Int> {

    @Query(
        "SELECT CE.ID_EMPLEADOCUENTA, CE.ID_EMPLEADO, CE.ID_CUENTA, C.ID_TIPOCUENTA, C.NUMERO_CUENTA, C.NOMBRE_CUENTA, C.NUMERO_CUENTAPADRE " +
            "FROM EMPLEADOCUENTA CE JOIN CUENTA C ON C.ID_CUENTA = CE.ID_CUENTA WHERE CE.ID_EMPLEADO =:idEmpleado"
    )
    fun getDatosContables(idEmpleado: Int): Flux<CuentaDatosContablesDto>

    fun findByNumeroCuenta(numeroCuenta: String): Flux<Cuenta>
}
