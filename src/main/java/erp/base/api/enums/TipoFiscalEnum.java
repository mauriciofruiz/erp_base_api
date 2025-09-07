package erp.base.api.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum TipoFiscalEnum {
    MASCULINO("04", "VENTA", "RUC"),
    CEDULA("05", "VENTA", "CÉDULA"),
    PASAPORTE("06", "VENTA","PASAPORTE"),
    CONSUMIDOR_FINAL("07", "VENTA","CONSUMIDOR FINAL"),;

    private String id;
    private String tipoTransaccion;
    private String tipoIdentificacion;

    TipoFiscalEnum(String id, String tipoTransaccion, String tipoIdentificacion) {
        this.id = id;
        this.tipoTransaccion = tipoTransaccion;
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public static String getTipoIdentificacionById(String id) {
        for (TipoFiscalEnum e : values()) {
            if (e.id.equals(id)) {
                return e.tipoIdentificacion;
            }
        }
        return null;
    }
}
