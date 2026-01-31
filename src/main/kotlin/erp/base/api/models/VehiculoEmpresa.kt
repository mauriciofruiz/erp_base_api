package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal

@Table("VEHICULOEMPRESA")
data class VehiculoEmpresa(
    @Id
    @Column("ID_VEHICULO")
    @JsonProperty("vehicleId")
    val idVehiculo: Int,
    @Column("ID_TIPOVEHICULO")
    @JsonProperty("vehicleTypeId")
    val idTipoVehiculo: Int? = null,
    @Column("ID_EMPLEADO")
    @JsonProperty("employeeId")
    val idEmpleado: Int? = null,
    @Column("PLACA_VEHICULO")
    @JsonProperty("vehiclePlate")
    val placaVehiculo: String? = null,
    @Column("PESOMAX_VEHICULO")
    @JsonProperty("vehicleMaxWeight")
    val pesoMaxVehiculo: BigDecimal? = null,
    @Column("PESOMIN_VEHICULO")
    @JsonProperty("vehicleMinWeight")
    val pesoMinVehiculo: BigDecimal? = null,
)
