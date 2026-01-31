package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("ESTADOFACTURA")
data class EstadoFactura(
    @Id
    @Column("ID_ESTADOFACTURA")
    @JsonProperty("invoiceStatusId")
    val idEstadoFactura: Int,
    @Column("NOMBRE_ESTADOFACTURA")
    @JsonProperty("invoiceStatusName")
    val nombreEstadoFactura: String? = null,
    @Column("COD_ESTADOFACTURA")
    @JsonProperty("invoiceStatusCode")
    val codEstadoFactura: String? = null,
)
