package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table("VEHICULOEMPRESA")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaVehiculoEmpresa {
    @Id
    @Column("ID_VEHICULO")
    @JsonProperty("vehicleId")
    private Integer idVehiculo;

    @Column("ID_TIPOVEHICULO")
    @JsonProperty("vehicleTypeId")
    private Integer idTipoVehiculo;

    @Column("ID_EMPLEADO")
    @JsonProperty("employeeId")
    private Integer idEmpleado;

    @Column("PLACA_VEHICULO")
    @JsonProperty("vehiclePlate")
    private String placaVehiculo;

    @Column("PESOMAX_VEHICULO")
    @JsonProperty("vehicleMaxWeight")
    private BigDecimal pesoMaxVehiculo;

    @Column("PESOMIN_VEHICULO")
    @JsonProperty("vehicleMinWeight")
    private BigDecimal pesoMinVehiculo;
}
