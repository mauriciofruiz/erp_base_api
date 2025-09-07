package erp.base.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum TipoBodegaEnum {
    VENTAS(1, "VENTAS", "BV"),
    DESPACHO(2, "DESPACHO", "BD"),
    IMPORTACION(3, "IMPORTACION", "BI"),
    TRANSITO(4, "TRANSITO", "BT"),
    PRODUCCION(5, "PRODUCCION", "BP"),
    MATERIA_PRIMA(6, "MATERIA PRIMA", "BMP"),
    OTRA(7, "OTRA", "BO");

    private Integer idTipoBodega;
    private String nombreTipoBodega;
    private String siglaTipoBodega;
}
