package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;


//@EqualsAndHashCode(callSuper = true)
@Table("excepciones_cliente_descuento")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ExcepcionesClienteDescuento extends BaseEntity {
    @Id
    @Column("id_excepcion")
    @JsonProperty("exceptionId")
    private Integer idExcepcion;

    @Column("id_cliente")
    @JsonProperty("clientId")
    private Long idCliente;

    @Column("id_producto")
    @JsonProperty("productId")
    private Integer idProducto;

    @Column("id_subgrupo")
    @JsonProperty("subgroupId")
    private Integer idSubgrupo;

    @Column("id_grupo")
    @JsonProperty("groupId")
    private Integer idGrupo;

    @Column("porcion_descuento")
    @JsonProperty("discountPortion")
    private BigDecimal porcionDescuento;

    @Column("fecha_inicio")
    @JsonProperty("startDate")
    private ZonedDateTime fechaInicio;

    @Column("fecha_fin")
    @JsonProperty("endDate")
    private ZonedDateTime fechaFin;

    @Column("estado")
    @JsonProperty("status")
    private Boolean estado;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExcepcionesClienteDescuento that = (ExcepcionesClienteDescuento) o;
        return idExcepcion.equals(that.idExcepcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idExcepcion);
    }

}
