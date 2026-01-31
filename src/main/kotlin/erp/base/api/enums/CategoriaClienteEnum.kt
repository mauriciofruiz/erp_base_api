package erp.base.api.enums

enum class CategoriaClienteEnum(val id: Int, val description: String) {
    NORMAL(1, "NORMAL"),
    PREFERENCIAL(2, "PREFERENCIAL"),
    CONSTRUCTOR(3, "CONSTRUCTOR"),
    DISTRIBUIDOR(5, "DISTRIBUIDOR"),
    ACCIONISTA(6, "ACCIONISTA"),
    DISTRIBUIDOR_FERRETERO(7, "DISTRIBUIDOR FERRETERO"),
    VENTA_DISTRIBUIDORES(8, "VENTA DISTRIBUIDORES"),
    DISTRIBUIDOR_STDO_LZ(9, "DISTRIBUIDOR STDO LZ"),
    DSCTO_DISTRIBUIDOR_FF(10, "DSCTO DISTRIBUIDOR FF"),
    DISTRIBUIDOR_CORPORATIVO(11, "DISTRIBUIDOR CORPORATIVO");

    companion object {
        @JvmStatic
        fun getDescriptionById(id: Int): String? = entries.find { it.id == id }?.description
    }
}
