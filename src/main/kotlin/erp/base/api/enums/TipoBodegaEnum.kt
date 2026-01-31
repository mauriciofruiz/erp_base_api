package erp.base.api.enums

enum class TipoBodegaEnum(val idTipoBodega: Int, val nombreTipoBodega: String, val siglaTipoBodega: String) {
    VENTAS(1, "VENTAS", "BV"),
    DESPACHO(2, "DESPACHO", "BD"),
    IMPORTACION(3, "IMPORTACION", "BI"),
    TRANSITO(4, "TRANSITO", "BT"),
    PRODUCCION(5, "PRODUCCION", "BP"),
    MATERIA_PRIMA(6, "MATERIA PRIMA", "BMP"),
    OTRA(7, "OTRA", "BO")
}
