package erp.base.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum StateEnum {

    ACTIVATED(1, "Activado"),
    DISABLED(2, "Desactivado");

    private Integer identification;
    private String name;

}
