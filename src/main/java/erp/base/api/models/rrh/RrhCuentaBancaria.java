package erp.base.api.models.rrh;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("RRH_CUENTABANCARIA")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RrhCuentaBancaria {

    @Id
    @Column("ID_CUENTABANCARIA")
    @JsonProperty("countBankId")
    private Integer idCuentaBancaria;

    @Column("ID_BANCOCATALOGO")
    @JsonProperty("catalogBank")
    private Integer idCatalogoBanco;

    @Column("ID_EMPLEADO")
    @JsonProperty("employeeId")
    private Integer idEmpleado;

    @Column("ID_TIPOCUENTABANCARIA")
    @JsonProperty("bankAccountTypeId")
    private Integer idTipoCuentaBancaria;

    @Column("NUMERO_CUENTABANCARIA")
    @JsonProperty("numberCountBank")
    private String numeroCuentaBancaria;

    @Column("ESTADO_CUENTABANCARIA")
    @JsonProperty("countStateBank")
    private Boolean estadoCuentaBancaria;
}
