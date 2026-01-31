package erp.base.api.models

object TipoDocumento {
    private const val TIPO_CEDULA = 1
    private const val TIPO_RUC_NATURAL = 2
    private const val RUC_PRIVADA = 3
    private const val RUC_PUBLICA = 4

    @JvmStatic
    fun getTipoCedula(): Int = TIPO_CEDULA

    @JvmStatic
    fun getTipoRucNatural(): Int = TIPO_RUC_NATURAL

    @JvmStatic
    fun getRucPrivada(): Int = RUC_PRIVADA

    @JvmStatic
    fun getRucPublica(): Int = RUC_PUBLICA
}
