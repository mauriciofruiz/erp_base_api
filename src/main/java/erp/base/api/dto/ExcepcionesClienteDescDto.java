package erp.base.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExcepcionesClienteDescDto {

    @JsonProperty("exceptionId")
    private Integer idExcepcion;

    @JsonProperty("clientId")
    private Long idCliente;

    @JsonProperty("exceptionTypeId")
    private Integer idTipoExcepcion;

    @JsonProperty("parameterId")
    private Integer idParametro;

    @JsonProperty("parameterValue")
    private String valorParametro;

    @Column("porcion_descuento")
    @JsonProperty("discountPortion")
    private BigDecimal porcionDescuento;

    @JsonProperty("startDate")
    private ZonedDateTime fechaInicio;

    @JsonProperty("endDate")
    private ZonedDateTime fechaFin;

    @JsonProperty("status")
    private Boolean estado;
}
