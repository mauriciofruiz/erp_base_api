package erp.base.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CuentaProveedorEnum {

    PROVEEDOR_NACIONAL_NO_RELACIONADO(107, "2.01.01.01.001"),
    PROVEEDOR_EXTERIOR(109, "2.01.01.01.003");

    private Integer id;
    private String countNumber;
}
