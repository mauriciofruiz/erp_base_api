package erp.base.api.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum TipoConstribuyenteEnum {
    NATURAL_OBLIGADA_A_LLEVAR_CONTABILIDAD("01", "NATURAL OBLIGADA A LLEVAR CONTABILIDAD", "N"),
    NATURAL_NO_OBLIGADA_A_LLEVAR_CONTABILIDAD("02", "NATURAL NO OBLIGADA A LLEVAR CONTABILIDAD", "N"),
    SOCIEDAD_OBLIGADA_A_LLEVAR_CONTABILIDAD("03", "SOCIEDAD OBLIGADA A LLEVAR CONTABILIDAD", "J"),
    SOCIEDAD_CONTRIBUYENTE_ESPECIAL("04", "SOCIEDAD CONTRIBUYENTE ESPECIAL", "J"),
    PERSONA_NATURAL_AGENTE_DE_RETENCION("05", "PERSONA NATURAL AGENTE DE RETENCION", "N"),
    SOCIEDAD_AGENTE_DE_RETENCION("06", "SOCIEDAD AGENTE DE RETENCION", "J"),
    REGIMEN_RIMPE_NEGOCIO_POPULAR("07", "RÉGIMEN RIMPE NEGOCIO POPULAR", "N"),
    REGIMEN_RIMPE_EMPRENDEDOR("08", "RÉGIMEN RIMPE EMPRENDEDOR", "N"),
    CONTRIBUYENTE_ESPECIAL("09", "CONTRIBUYENTE ESPECIAL", "J"),
    GRANDES_CONTRIBUYENTES("10", "GRANDES CONTRIBUYENTES", "J");

    private String id;
    private String nombre;
    private String codigo;

    TipoConstribuyenteEnum(String id, String nombre, String codigo) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public static String getNameById(String id) {
        for (TipoConstribuyenteEnum e : values()) {
            if (e.id.equals(id)) {
                return e.nombre;
            }
        }
        return null;
    }
}
