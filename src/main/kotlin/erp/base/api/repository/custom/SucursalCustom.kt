package erp.base.api.repository.custom

import erp.base.api.enums.TipoBodegaEnum
import erp.base.api.models.Sucursal
import io.r2dbc.spi.Row
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class SucursalCustom(private val databaseClient: DatabaseClient) {

    fun getBySearchParams(
        cityId: Int?,
        regionId: Int?,
        categoryBranchId: String?,
        name: String?,
        mnemonic: String?,
        isOutlet: Boolean?,
        sortField: String?,
        sortDirection: String?,
        limit: Int?,
        offset: Int?
    ): Flux<Sucursal> {
        var spec = databaseClient.sql(buildQueryForSearch(cityId, regionId, categoryBranchId, name, mnemonic, isOutlet, sortField, sortDirection, limit, offset, false))
        if (cityId != null) spec = spec.bind("cityId", cityId)
        if (regionId != null) spec = spec.bind("regionId", regionId)
        if (categoryBranchId != null) spec = spec.bind("categoryBranchId", categoryBranchId)
        if (name != null) spec = spec.bind("name", name)
        if (mnemonic != null) spec = spec.bind("mnemonic", mnemonic)
        if (isOutlet == true) spec = spec.bind("isOutlet", isOutlet)
        if (limit != null && offset != null) spec = spec.bind("limit", limit).bind("offset", offset)
        return spec.map { row, _ -> mapRowToEntity(row) }.all()
    }

    fun getCountBySearchParams(
        cityId: Int?,
        regionId: Int?,
        categoryBranchId: String?,
        name: String?,
        mnemonic: String?,
        isOutlet: Boolean?
    ): Mono<Long> {
        var spec = databaseClient.sql(buildQueryForSearch(cityId, regionId, categoryBranchId, name, mnemonic, isOutlet, null, null, null, null, true))
        if (cityId != null) spec = spec.bind("cityId", cityId)
        if (regionId != null) spec = spec.bind("regionId", regionId)
        if (categoryBranchId != null) spec = spec.bind("categoryBranchId", categoryBranchId)
        if (name != null) spec = spec.bind("name", name)
        if (mnemonic != null) spec = spec.bind("mnemonic", mnemonic)
        if (isOutlet == true) spec = spec.bind("isOutlet", isOutlet)
        return spec.map { row, _ -> row.get(0, Long::class.javaObjectType) }.one()
    }

    fun getBranchProduction(): Flux<Sucursal> =
        databaseClient.sql("SELECT DISTINCT su.id_sucursal, su.* FROM SUCURSAL su JOIN inv_bodega bo ON su.id_sucursal = bo.id_sucursal JOIN inv_tipo_bodega tbo on tbo.id_tipo_bodega = bo.id_tipo_bodega WHERE tbo.id_tipo_bodega = :warehouseTypeId")
            .bind("warehouseTypeId", TipoBodegaEnum.PRODUCCION.idTipoBodega)
            .map { row, _ -> mapRowToEntity(row) }.all()

    private fun buildQueryForSearch(
        cityId: Int?,
        regionId: Int?,
        categoryBranchId: String?,
        name: String?,
        mnemonic: String?,
        isOutlet: Boolean?,
        sortField: String?,
        sortDirection: String?,
        limit: Int?,
        offset: Int?,
        isCount: Boolean
    ): String {
        val q = StringBuilder(if (isCount) "SELECT COUNT(*)" else "SELECT DISTINCT su.id_sucursal, su.*")
        q.append(" FROM SUCURSAL su WHERE su.estado_sucursal = true")
        if (cityId != null) q.append(" AND su.id_ciudad = :cityId")
        if (regionId != null) q.append(" AND su.id_region = :regionId")
        if (categoryBranchId != null) q.append(" AND UPPER(su.id_categoriasucursal) LIKE UPPER(CONCAT('%',:categoryBranchId,'%'))")
        if (name != null) q.append(" AND UPPER(su.nombre_sucursal) LIKE UPPER(CONCAT('%',:name,'%'))")
        if (mnemonic != null) q.append(" AND UPPER(su.nemonico_sucursal) LIKE UPPER(CONCAT('%',:mnemonic,'%'))")
        if (isOutlet == true) q.append(" AND su.esoutlet_sucursal = :isOutlet")
        if (sortField != null && sortDirection != null) q.append(" ORDER BY ").append(sortField).append(" ").append(sortDirection)
        if (limit != null && offset != null) q.append(" LIMIT :limit OFFSET :offset")
        return q.toString()
    }

    private fun mapRowToEntity(row: Row): Sucursal = Sucursal(
        idSucursal = row.get("id_sucursal", Int::class.javaObjectType),
        idCiudad = row.get("id_ciudad", Int::class.javaObjectType),
        idCategoriaSucursal = row.get("id_categoriasucursal", String::class.java),
        idRegion = row.get("id_region", Int::class.javaObjectType),
        nombreSucursal = row.get("nombre_sucursal", String::class.java),
        nemonicoSucursal = row.get("nemonico_sucursal", String::class.java),
        direccionSucursal = row.get("direccion_sucursal", String::class.java),
        telefonoSucursal = row.get("telefono_sucursal", String::class.java),
        telefono2Sucursal = row.get("telefono2_sucursal", String::class.java),
        celularSucursal = row.get("celular_sucursal", String::class.java),
        correoSucursal = row.get("correo_sucursal", String::class.java),
        correo2Sucursal = row.get("correo2_sucursal", String::class.java),
        responsableSucursal = row.get("responsable_sucursal", String::class.java),
        esOutletSucursal = row.get("esoutlet_sucursal", Boolean::class.javaObjectType),
        estadoSucursal = row.get("estado_sucursal", Boolean::class.javaObjectType),
        impresionTermicaSucursal = row.get("impresiontermica_sucursal", Boolean::class.javaObjectType),
        codigoSucursal = row.get("codigo_sucursal", String::class.java)
    )
}
