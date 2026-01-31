package erp.base.api.enums

enum class TipoFiscalEnum(val id: String, val tipoTransaccion: String, val tipoIdentificacion: String) {
    MASCULINO("04", "VENTA", "RUC"),
    CEDULA("05", "VENTA", "CÉDULA"),
    PASAPORTE("06", "VENTA", "PASAPORTE"),
    CONSUMIDOR_FINAL("07", "VENTA", "CONSUMIDOR FINAL");

    companion object {
        @JvmStatic
        fun getTipoIdentificacionById(id: String): String? = entries.find { it.id == id }?.tipoIdentificacion
    }
}
