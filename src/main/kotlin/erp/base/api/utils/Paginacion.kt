package erp.base.api.utils

/**
 * Clase que contiene la información de paginación
 */
data class Paginacion<T>(
    var list: List<T>? = null,
    var page: Int? = null,
    var limit: Int? = null,
    var totalRecords: Int? = null,
    var totalPages: Int? = null,
) {
    constructor(list: List<T>, page: Int, limit: Int, totalRecords: Int) : this(
        list = list,
        page = page,
        limit = limit,
        totalRecords = totalRecords,
        totalPages = kotlin.math.ceil(totalRecords.toDouble() / limit).toInt()
    )
}
