package erp.base.api.repository.custom

import erp.base.api.models.Proveedor
import io.r2dbc.spi.Row
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@Component
class ProveedorCustom(private val databaseClient: DatabaseClient) {

    fun findByIdPaisNotInAndEstado(
        idPaisLista: List<Int>,
        idEstadoProveedor: Boolean,
        sortField: String,
        sortDirection: String,
        limit: Int,
        offset: Long
    ): Flux<Proveedor> {
        val idPaisCsv = idPaisLista.joinToString(",")
        val query = "SELECT pr.* FROM tba_proveedor pr JOIN tba_persona pe ON pr.id_persona = pe.id_persona WHERE pe.id_pais NOT IN ($idPaisCsv) AND pr.estado_proveedor = \$1 ORDER BY $sortField $sortDirection LIMIT \$2 OFFSET \$3"
        return databaseClient.sql(query)
            .bind("\$1", idEstadoProveedor)
            .bind("\$2", limit)
            .bind("\$3", offset)
            .map { row, _ -> mapRowToProvider(row) }.all()
    }

    private fun mapRowToProvider(row: Row): Proveedor = Proveedor(
        idUsuarioCreacion = row.get("id_usuario_creacion", Int::class.javaObjectType),
        idUsuarioModificacion = row.get("id_usuario_modificacion", Int::class.javaObjectType),
        fechaCreacion = row.get("fecha_creacion", LocalDateTime::class.java),
        fechaModificacion = row.get("fecha_modificacion", LocalDateTime::class.java),
        idProveedor = row.get("id_proveedor", Int::class.javaObjectType),
        idTipoIdFiscal = row.get("id_tipoidfiscal", String::class.java),
        idRetencionIva = row.get("id_retencioniva", Int::class.javaObjectType),
        idPersona = row.get("id_persona", Int::class.javaObjectType),
        idBancoCatalogo = row.get("id_bancocatalogo", Int::class.javaObjectType),
        idRetencionFuente = row.get("id_retencionfuente", Int::class.javaObjectType),
        idTipoProveedor = row.get("id_tipoprov", Int::class.javaObjectType),
        idTipoCuentaBancaria = row.get("id_tipocuentabancaria", Int::class.javaObjectType),
        idCondicionPago = row.get("id_condicionpago", Int::class.javaObjectType),
        idFormaPagoCobro = row.get("id_formapagocobro", String::class.java),
        nombreComercialProveedor = row.get("nombrecomercial_proveedor", String::class.java),
        direccion2Proveedor = row.get("direccion2_proveedor", String::class.java),
        correo2Proveedor = row.get("correo2_proveedor", String::class.java),
        celular2Proveedor = row.get("celular2_proveedor", String::class.java),
        telefono2Proveedor = row.get("telefono2_proveedor", String::class.java),
        sitioWebProveedor = row.get("sitioweb_proveedor", String::class.java),
        contactoProveedor = row.get("contacto_proveedor", String::class.java),
        cargoContactoProveedor = row.get("cargocontacto_proveedor", String::class.java),
        telefonoContactoProveedor = row.get("telefonocontacto_proveedor", String::class.java),
        correoContactoProveedor = row.get("correocontacto_proveedor", String::class.java),
        celularContactoProveedor = row.get("celularcontacto_proveedor", String::class.java),
        creditoProveedor = row.get("credito_proveedor", Boolean::class.javaObjectType),
        montoCreditoProveedor = row.get("montocredito_proveedor", BigDecimal::class.java),
        fechaVencimientoCreditoProveedor = row.get("fechavencimientocredito_proveedor", LocalDate::class.java),
        diasCreditoProveedor = row.get("diascredito_proveedor", Int::class.javaObjectType),
        montoGarantiaProveedor = row.get("montogarantia_proveedor", BigDecimal::class.java),
        numeroCuentaBancariaProveedor = row.get("numcuentabancaria_proveedor", String::class.java),
        referenciaPagoProveedor = row.get("referenciapago_proveedor", String::class.java),
        saldoAnticipoProveedor = row.get("saldoanticipo_proveedor", BigDecimal::class.java),
        nombreChequeProveedor = row.get("nombrecheque_proveedor", String::class.java),
        contribucionEspecialProveedor = row.get("contribespecial_proveedor", String::class.java),
        esTransporteProveedor = row.get("estransporte_proveedor", Boolean::class.javaObjectType),
        estadoProveedor = row.get("estado_proveedor", Boolean::class.javaObjectType),
        numeroCuentaPago = row.get("numero_cuenta_pago", String::class.java),
        idIdentificacionFiscalPago = row.get("id_identificacionfiscal_pago", Int::class.javaObjectType),
        cuentaPasivaProveedor = row.get("cuentapasiva_proveedor", Int::class.javaObjectType),
        cuentaGastoProveedor = row.get("cuentagasto_proveedor", Int::class.javaObjectType)
    )
}
