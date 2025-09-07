package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@EqualsAndHashCode(callSuper = true)
@Table("TBA_TARJETA")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaTarjeta extends BaseEntity {
    @Id
    @Column("ID_TARJETA")
    @JsonProperty("cardId")
    private Integer idTarjeta;

    @Column("ID_CUENTA")
    @JsonProperty("accountId")
    private Integer idCuenta;

    @Column("NOMBRE_TARJETA")
    @JsonProperty("cardName")
    private String nombreTarjeta;

    @Column("ESCREDITO_TARJETA")
    @JsonProperty("isCreditCard")
    private Boolean esCreditoTarjeta;

    @Column("estado_tarjeta")
    @JsonProperty("cardStatus")
    private Boolean estadoTarjeta;
}
