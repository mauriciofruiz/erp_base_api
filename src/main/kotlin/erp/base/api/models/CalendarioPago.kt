package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.LocalDate

@Table("calendariopago")
data class CalendarioPago(
    @Id
    @Column("id_calendariopago")
    @JsonProperty("calendarPaymentId")
    val idCalendarioPago: Int,
    @Column("id_pago")
    @JsonProperty("paymentId")
    val idPago: Int? = null,
    @Column("id_facturaproveedor")
    @JsonProperty("invoiceProviderId")
    val idFacturaProveedor: Int? = null,
    @Column("ncuota_calendariopago")
    @JsonProperty("installmentNumber")
    val numeroCuota: Int? = null,
    @Column("totalcuotas_calendariopago")
    @JsonProperty("totalInstallments")
    val totalCuotas: Int? = null,
    @Column("fechavence_calendariopago")
    @JsonProperty("dueDate")
    val fechaVencimiento: LocalDate? = null,
    @Column("valorfactura_calendariopago")
    @JsonProperty("invoiceValue")
    val valorFactura: BigDecimal? = null,
    @Column("saldo_calendariopago")
    @JsonProperty("balance")
    val saldo: BigDecimal? = null,
    @Column("valcruzado_calendariopago")
    @JsonProperty("crossedValue")
    val valorCruzado: BigDecimal? = null,
    @Column("codanticipo_calendariopago")
    @JsonProperty("advanceCode")
    val codigoAnticipo: String? = null,
    @Column("obs_calendariopago")
    @JsonProperty("observation")
    val observacion: String? = null,
    @Column("codnc_calendariopago")
    @JsonProperty("ncCode")
    val codigoNC: String? = null,
    @Column("devolucionnc_calendarpago")
    @JsonProperty("ncRefund")
    val devolucionNC: BigDecimal? = null,
    @Column("saldoanticipo_calendarpago")
    @JsonProperty("advanceBalance")
    val saldoAnticipo: BigDecimal? = null,
    @Column("saldoncdesc_calendarpago")
    @JsonProperty("ncDiscountBalance")
    val saldoNCDescripcion: BigDecimal? = null,
    @Column("valorpago_calendarpago")
    @JsonProperty("paymentValue")
    val valorPago: BigDecimal? = null,
    @Column("estado_calendariopago")
    @JsonProperty("state")
    val estado: Boolean? = null,
    @Column("dias_calendariopago")
    @JsonProperty("days")
    val dias: Int? = null,
    @Column("montofactura_calendarpago")
    @JsonProperty("invoiceAmount")
    val montoFactura: BigDecimal? = null,
    @Column("pagado_calendariopago")
    @JsonProperty("paid")
    val pagado: Boolean? = null,
    @Column("saldofactura_calendariopago")
    @JsonProperty("invoiceBalance")
    val saldoFactura: BigDecimal? = null,
)
