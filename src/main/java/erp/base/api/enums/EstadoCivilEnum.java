package erp.base.api.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum EstadoCivilEnum {
    SOLTERO(1, "SOLTERO", "S"),
    CASADO(2, "CASADO", "C"),
    DIVORCIADO(3, "DIVORCIADO","D"),
    VIUDO(4, "VIUDO","V"),
    UNION_HECHO(5, "UNION DE HECHO","U"),
    NO_APLICA(6, "NO APLICA","0");

    private Integer id;
    private String description;
    private String codigo;

    EstadoCivilEnum(Integer id, String description, String codigo) {
        this.id = id;
        this.description = description;
        this.codigo = codigo;
    }

    public static String getDescriptionById(Integer id) {
        for (EstadoCivilEnum e : values()) {
            if (e.id.equals(id)) {
                return e.description;
            }
        }
        return null;
    }
}
