package erp.base.api.enums

enum class PaisEnum(val id: String, val description: String) {
    ECUADOR("593", "ECUADOR");

    companion object {
        @JvmStatic
        fun getDescriptionById(id: String): String? = entries.find { it.id == id }?.description
    }
}
