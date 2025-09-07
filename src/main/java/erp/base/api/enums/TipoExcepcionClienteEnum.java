package erp.base.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum TipoExcepcionClienteEnum {

    PRODUCTO(1),
    SUBGRUPO(2),
    GRUPO(3);

    private Integer identification;

}
