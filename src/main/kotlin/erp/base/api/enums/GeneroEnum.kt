package erp.base.api.enums

enum class GeneroEnum(val id: Int, val description: String, val codigo: String) {
    MASCULINO(1, "MASCULINO", "M"),
    FEMENINO(2, "FEMENINO", "F"),
    NO_APLICABLE(3, "NO APLICABLE", "0");

    companion object {
        @JvmStatic
        fun getDescriptionById(id: Int): String? = entries.find { it.id == id }?.description
    }
}
