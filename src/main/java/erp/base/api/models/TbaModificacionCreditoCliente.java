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

@Table("TBA_MODIFICACIONCREDITOCLIENTE")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaModificacionCreditoCliente {
    @Id
    @Column("ID_MODIFICACIONCREDITOCLIENTE")
    @JsonProperty("modificationId")
    private Integer idModificacionCreditoCliente;

    @Column("ID_CREDITOCLIENTE")
    @JsonProperty("creditClientId")
    private Integer idCreditoCliente;

    @Column("ID_USR")
    @JsonProperty("userId")
    private Integer idUsuario;

    @Column("CAMPOMODIFICADO")
    @JsonProperty("modifiedField")
    private String campoModificado;

    @Column("VALORANTERIOR_MODIFICACIONCREDCLIENTE")
    @JsonProperty("previousValue")
    private BigDecimal valorAnteriorModificacionCreditoCliente;

    @Column("NUEVOVALOR_MODIFICACIONCREDITOCLIENTE")
    @JsonProperty("newValue")
    private BigDecimal nuevoValorModificacionCreditoCliente;
}
