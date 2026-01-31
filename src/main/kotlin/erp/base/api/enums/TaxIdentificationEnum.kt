package erp.base.api.enums

enum class TaxIdentificationEnum(val id: Int, val typeName: String) {
    RUC(1, "RUC"),
    CEDULA(2, "CÉDULA"),
    PASAPORTE(3, "PASAPORTE"),
    IDENTIFICATION_EXT(4, "IDENTIFICACION EXT"),
    CONSUMIDOR_FINAL(5, "CONSUMIDOR FINAL");

    companion object {
        @JvmStatic
        fun getNameById(id: Int): String? = entries.find { it.id == id }?.typeName
        @JvmStatic
        fun getIdByName(name: String): Int? = entries.find { it.typeName == name }?.id
    }
}
