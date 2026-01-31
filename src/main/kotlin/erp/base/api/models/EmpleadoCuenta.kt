package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("EMPLEADOCUENTA")
data class EmpleadoCuenta(
    @Id
    @Column("ID_EMPLEADOCUENTA")
    @JsonProperty("employeeCountId")
    var idEmpleadoCuenta: Int? = null,
    @Column("ID_EMPLEADO")
    @JsonProperty("employeeId")
    var idEmpleado: Int? = null,
    @Column("ID_CUENTA")
    @JsonProperty("countId")
    var idCuenta: Int? = null,
)
