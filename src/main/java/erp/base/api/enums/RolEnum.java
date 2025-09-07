package erp.base.api.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum RolEnum {

    SISTEMAS(1, "Rol para administrador del sistema"),
    MANUFACTURA(2, "MANUFACTURA"),
    LOGISTICA(3, "LOGISTICA"),
    COMERCIAL(4, "COMERCIAL"),
    ADQUISICIONES(5, "ADQUISICIONES"),
    TALENTO_HUMANO(6, "TALENTO HUMANO"),
    FINANCIERO_CONTABLE(7, "FINANCIERO CONTABLE"),
    MARKETING_Y_PUBLICIDAD(8, "MARKETING Y PUBLICIDAD"),
    DIRECTIVO(9, "DIRECTIVO"),
    VENTAS(10, "VENTAS"),
    COMPRAS_LOCALES(11, "COMPRAS LOCALES"),
    ADMINISTRATIVO(12, "ADMINISTRATIVO"),
    JEFE(13, "JEFE"),
    COMPRAS_EXTERIORES(14, "COMPRAS EXTERIORES");

    private Integer id;
    private String description;

    RolEnum(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

}
