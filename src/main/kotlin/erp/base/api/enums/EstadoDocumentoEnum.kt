package erp.base.api.enums

enum class EstadoDocumentoEnum(val id: Int, val description: String) {
    GENERADO(1, "GENERADO"),
    CONFIRMADO(2, "CONFIRMADO"),
    AUTORIZADO(3, "AUTORIZADO"),
    ENVIADO(4, "ENVIADO"),
    RECHAZADO(5, "RECHAZADO"),
    ANULADO(6, "ANULADO");

    companion object {
        @JvmStatic
        fun getDescriptionById(id: Int): String? = entries.find { it.id == id }?.description
    }
}
