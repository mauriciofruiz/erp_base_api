package erp.base.api.enums

enum class Multidimensional(val identification: Int, val displayName: String) {
    CUSTOMER(1, "cliente"),
    EMPLOYEE(2, "empleado"),
    IMPORT(3, "importacion"),
    VENDOR(4, "proveedor"),
    PROJECT(5, "proyecto"),
    BRANCH(6, "sucursal")
}
