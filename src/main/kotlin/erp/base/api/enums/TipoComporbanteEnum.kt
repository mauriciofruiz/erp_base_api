package erp.base.api.enums

enum class TipoComporbanteEnum(val id: Int, val description: String, val codigo: String) {
    FACTURA(1, "GENERADO", "01"),
    NOTA_VENTA(2, "CONFIRMADO", "02"),
    LIQUIDACION(3, "AUTORIZADO", "03"),
    NOTA_CREDITO(4, "ENVIADO", "04"),
    NOTA_DEBITO(5, "RECHAZADO", "05"),
    GUIAS_REMISION(6, "ANULADO", "06"),
    COMPROBANTE_RETENCION(7, "ANULADO", "07");

    companion object {
        @JvmStatic
        fun getCodigoById(id: Int): String? = entries.find { it.id == id }?.codigo
    }
}
