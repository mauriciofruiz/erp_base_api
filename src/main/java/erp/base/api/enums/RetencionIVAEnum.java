package erp.base.api.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum RetencionIVAEnum {

    RETENCION_IVA_CLIENTE_10(1, "10% RETENCION IVA CLIENTE"),
    RETENCION_IVA_PROVEEDOR_10(2, "10% RETENCION IVA PROVEEDOR"),
    RETENCION_IVA_PROVEEDOR_20(3, "20% RETENCION IVA PROVEEDOR"),
    RETENCION_IVA_CLIENTE_30(4, "30% RETENCION IVA CLIENTE"),
    RETENCION_IVA_PROVEEDOR_30(5, "30% RETENCION IVA PROVEEDOR"),
    RETENCION_IVA_PROVEEDOR_70(7, "70% RETENCION IVA PROVEEDOR"),
    RETENCION_IVA_CLIENTE_100(8, "100% RETENCION IVA CLIENTE"),
    RETENCION_IVA_PROVEEDOR_100(9, "100% RETENCION IVA PROVEEDOR"),
    NO_APLICA(10, "NO APLICA"),
    RETENCION_IVA_CLIENTE_20(11, "20% RETENCION IVA CLIENTE");

    private Integer id;
    private String descripcion;

    RetencionIVAEnum(Integer id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public static String getDescriptionById(Integer id) {
        for (RetencionIVAEnum e : values()) {
            if (e.id.equals(id)) {
                return e.descripcion;
            }
        }
        return null;
    }
}
