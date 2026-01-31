package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table("CREDITOCLIENTE")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class TbaCreditoCliente extends BaseEntity {

    @Id
    @Column("ID_CREDITOCLIENTE")
    @JsonProperty("creditClientId")
    private Integer idCreditoCliente;

    @Column("ID_CLIENTE")
    @JsonProperty("clientId")
    private Integer idCliente;

    @Column("CUPOAUTORIZADO_CREDITOCLIENTE")
    @JsonProperty("authorizedCreditLimit")
    private BigDecimal cupoAutorizado;

    @Column("CUPODISPONIBLE_CREDITOCLIENTE")
    @JsonProperty("availableCreditLimit")
    private BigDecimal cupoDisponible;

    @Column("CUPOUTILIZADO_CREDITOCLIENTE")
    @JsonProperty("usedCreditLimit")
    private BigDecimal cupoUtilizado;

    @Column("MONTOGARANTIA_CREDITOCLIENTE")
    @JsonProperty("guaranteeAmount")
    private BigDecimal montoGarantia;

    @Column("FECHA_GARANTIA")
    @JsonProperty("guaranteeDate")
    private LocalDateTime fechaGarantia;

    @Column("DIAS_CREDITOCLIENTE")
    @JsonProperty("creditDays")
    private Integer diasCredito;

    @Column("OBS_CREDITOCLIENTE")
    @JsonProperty("creditObservation")
    private String observacion;

    @Column("SOBRECUPO_CREDITOCLIENTE")
    @JsonProperty("overlimit")
    private BigDecimal sobreCupo;

    @Column("FECHAVCTOSOBRECUPO_CREDITOCLIENTE")
    @JsonProperty("overlimitExpirationDate")
    private LocalDateTime fechaVencimientoSobreCupo;
}
