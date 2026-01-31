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
import java.time.LocalDateTime;

@Table("SORTEO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaSorteo {
    @Id
    @Column("ID_SORTEO")
    @JsonProperty("lotteryId")
    private Integer idSorteo;

    @Column("ANULADO_SORTEO")
    @JsonProperty("cancelled")
    private Boolean anuladoSorteo;

    @Column("DESCRIPCION_SORTEO")
    @JsonProperty("description")
    private String descripcionSorteo;

    @Column("FECHAFIN_SORTEO")
    @JsonProperty("endDate")
    private LocalDateTime fechaFinSorteo;

    @Column("FECHAINICIO_SORTEO")
    @JsonProperty("startDate")
    private LocalDateTime fechaInicioSorteo;

    @Column("MONTOPARTICIPANTE_SORTEO")
    @JsonProperty("participantAmount")
    private BigDecimal montoParticipanteSorteo;

    @Column("NOMBRE_SORTEO")
    @JsonProperty("name")
    private String nombreSorteo;

    @Column("ID_CUPON_SORTEO")
    @JsonProperty("couponId")
    private Integer idCuponSorteo;
}
