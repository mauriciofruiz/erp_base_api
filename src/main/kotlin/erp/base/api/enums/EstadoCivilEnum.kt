package erp.base.api.enums

enum class EstadoCivilEnum(val id: Int, val description: String, val codigo: String) {
    SOLTERO(1, "SOLTERO", "S"),
    CASADO(2, "CASADO", "C"),
    DIVORCIADO(3, "DIVORCIADO", "D"),
    VIUDO(4, "VIUDO", "V"),
    UNION_HECHO(5, "UNION DE HECHO", "U"),
    NO_APLICA(6, "NO APLICA", "0");

    companion object {
        @JvmStatic
        fun getDescriptionById(id: Int): String? = entries.find { it.id == id }?.description
    }
}
