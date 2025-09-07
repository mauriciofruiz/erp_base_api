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
import java.time.LocalDate;


@Table("adq_calendariopago")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AdqCalendarioPago {

    @Id
    @Column("id_calendariopago")
    @JsonProperty("calendarPaymentId")
    private Integer idCalendarioPago;

    @Column("id_pago")
    @JsonProperty("paymentId")
    private Integer idPago;

    @Column("id_facturaproveedor")
    @JsonProperty("invoiceProviderId")
    private Integer idFacturaProveedor;

    @Column("ncuota_calendariopago")
    @JsonProperty("installmentNumber")
    private Integer numeroCuota;

    @Column("totalcuotas_calendariopago")
    @JsonProperty("totalInstallments")
    private Integer totalCuotas;

    @Column("fechavence_calendariopago")
    @JsonProperty("dueDate")
    private LocalDate fechaVencimiento;

    @Column("valorfactura_calendariopago")
    @JsonProperty("invoiceValue")
    private BigDecimal valorFactura;

    @Column("saldo_calendariopago")
    @JsonProperty("balance")
    private BigDecimal saldo;

    @Column("valcruzado_calendariopago")
    @JsonProperty("crossedValue")
    private BigDecimal valorCruzado;

    @Column("codanticipo_calendariopago")
    @JsonProperty("advanceCode")
    private String codigoAnticipo;

    @Column("obs_calendariopago")
    @JsonProperty("observation")
    private String observacion;

    @Column("codnc_calendariopago")
    @JsonProperty("ncCode")
    private String codigoNC;

    @Column("devolucionnc_calendarpago")
    @JsonProperty("ncRefund")
    private BigDecimal devolucionNC;

    @Column("saldoanticipo_calendarpago")
    @JsonProperty("advanceBalance")
    private BigDecimal saldoAnticipo;

    @Column("saldoncdesc_calendarpago")
    @JsonProperty("ncDiscountBalance")
    private BigDecimal saldoNCDescripcion;

    @Column("valorpago_calendarpago")
    @JsonProperty("paymentValue")
    private BigDecimal valorPago;

    @Column("estado_calendariopago")
    @JsonProperty("state")
    private Boolean estado;

    @Column("dias_calendariopago")
    @JsonProperty("days")
    private Integer dias;

    @Column("montofactura_calendarpago")
    @JsonProperty("invoiceAmount")
    private BigDecimal montoFactura;

    @Column("pagado_calendariopago")
    @JsonProperty("paid")
    private Boolean pagado;

    @Column("saldofactura_calendariopago")
    @JsonProperty("invoiceBalance")
    private BigDecimal saldoFactura;
}
