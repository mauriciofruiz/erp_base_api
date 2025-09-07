package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("TBA_LISTAPRECIOSPROV")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaListaPreciosProv {
    @Id
    @Column("ID_LISTAPRECIOS")
    @JsonProperty("priceListId")
    private Integer idListaPrecios;

    @Column("ID_PROVEEDOR")
    @JsonProperty("providerId")
    private Integer idProveedor;

    @Column("ID_PRODUCTO")
    @JsonProperty("productId")
    private Integer idProducto;

    @Column("RANGO_INICIAL")
    @JsonProperty("initialRange")
    private Double rangoInicial;

    @Column("RANGO_FINAL")
    @JsonProperty("finalRange")
    private Double rangoFinal;

    @Column("ACTUALIZADO")
    @JsonProperty("updated")
    private Boolean actualizado;
}
