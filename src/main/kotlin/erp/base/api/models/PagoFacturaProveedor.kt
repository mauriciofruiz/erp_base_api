package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("PAGOFACTURAPROVEEDOR")
data class PagoFacturaProveedor(
    @Id
    @Column("ID_PAGOFACPROVEEDOR")
    @JsonProperty("paymentInvoiceProviderId")
    val idPagoFacturaProveedor: Int,
    @Column("ID_PAGO")
    @JsonProperty("paymentId")
    val idPago: Int? = null,
    @Column("ID_CUENTA")
    @JsonProperty("accountId")
    val idCuenta: Int? = null,
    @Column("ID_USR")
    @JsonProperty("userId")
    val idUsuario: Int? = null,
    @Column("ID_FACTURAPROVEEDOR")
    @JsonProperty("invoiceProviderId")
    val idFacturaProveedor: Int? = null,
    @Column("NUMCUOTA_PAGOFACPROVEEDOR")
    @JsonProperty("paymentInvoiceProviderInstallment")
    val numCuotaPagoFacturaProveedor: Int? = null,
)
