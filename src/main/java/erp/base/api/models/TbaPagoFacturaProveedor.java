package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("PAGOFACTURAPROVEEDOR")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaPagoFacturaProveedor {
    @Id
    @Column("ID_PAGOFACPROVEEDOR")
    @JsonProperty("paymentInvoiceProviderId")
    private Integer idPagoFacturaProveedor;

    @Column("ID_PAGO")
    @JsonProperty("paymentId")
    private Integer idPago;

    @Column("ID_CUENTA")
    @JsonProperty("accountId")
    private Integer idCuenta;

    @Column("ID_USR")
    @JsonProperty("userId")
    private Integer idUsuario;

    @Column("ID_FACTURAPROVEEDOR")
    @JsonProperty("invoiceProviderId")
    private Integer idFacturaProveedor;

    @Column("NUMCUOTA_PAGOFACPROVEEDOR")
    @JsonProperty("paymentInvoiceProviderInstallment")
    private Integer numCuotaPagoFacturaProveedor;
}
