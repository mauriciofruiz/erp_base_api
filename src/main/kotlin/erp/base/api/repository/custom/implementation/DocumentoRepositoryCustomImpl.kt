package erp.base.api.repository.custom.implementation

import erp.base.api.dto.DocumentoDto
import erp.base.api.enums.TipoComporbanteEnum
import erp.base.api.repository.custom.DocumentoRepositoryCustom
import io.r2dbc.spi.Row
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDateTime

@Repository
class DocumentoRepositoryCustomImpl(private val databaseClient: DatabaseClient) : DocumentoRepositoryCustom {

    override fun getBySearchParams(
        documentNumber: String?,
        documentType: String?,
        name: String?,
        identification: String?,
        accesKey: String?,
        isAuthorized: Boolean?,
        sortField: String?,
        sortDirection: String?,
        limit: Int?,
        offset: Int?
    ): Flux<DocumentoDto> {
        var spec = databaseClient.sql(
            buildQueryForSearch(documentNumber, documentType, name, identification, accesKey, isAuthorized, sortField, sortDirection, limit, offset, false)
        )
        if (documentNumber != null) spec = spec.bind("documentNumber", documentNumber)
        if (documentType != null) spec = spec.bind("documentType", documentType)
        if (name != null) spec = spec.bind("name", name)
        if (identification != null) spec = spec.bind("identification", identification)
        if (accesKey != null) spec = spec.bind("accesKey", accesKey)
        if (limit != null && offset != null) spec = spec.bind("limit", limit).bind("offset", offset)
        return spec.map { row, _ -> mapRowToEntity(row) }.all()
    }

    override fun getCountBySearchParams(
        documentNumber: String?,
        documentType: String?,
        name: String?,
        identification: String?,
        accesKey: String?,
        isAuthorized: Boolean?
    ): Mono<Long> {
        var spec = databaseClient.sql(
            buildQueryForSearch(documentNumber, documentType, name, identification, accesKey, isAuthorized, null, null, null, null, true)
        )
        if (documentNumber != null) spec = spec.bind("documentNumber", documentNumber)
        if (documentType != null) spec = spec.bind("documentType", documentType)
        if (name != null) spec = spec.bind("name", name)
        if (identification != null) spec = spec.bind("identification", identification)
        if (accesKey != null) spec = spec.bind("accesKey", accesKey)
        return spec.map { row, _ -> row.get(0, Long::class.javaObjectType) }.one()
    }

    private fun buildQueryForSearch(
        documentNumber: String?,
        documentType: String?,
        name: String?,
        identification: String?,
        accesKey: String?,
        isAuthorized: Boolean?,
        sortField: String?,
        sortDirection: String?,
        limit: Int?,
        offset: Int?,
        isCount: Boolean
    ): String {
        val allowedTiposComprobante = listOf(
            TipoComporbanteEnum.FACTURA.codigo,
            TipoComporbanteEnum.NOTA_DEBITO.codigo,
            TipoComporbanteEnum.NOTA_CREDITO.codigo
        )
        val q = StringBuilder(
            if (isCount) "SELECT COUNT(*)" else "SELECT doc.doc_id_documento as docId, doc.doc_numerodocumento as numberDoc, doc.doc_id_tipocomprobante as docType, tip.nombre_tipocomprobante as docTypeName, doc.doc_id_estadodocumento as docState, doc.doc_nombrecomercial_emisor as docComercialName, doc.doc_identificacion as docIdentification, doc.doc_fechaemision as docIssueDate, doc.doc_claveacceso as docAccesKey"
        )
        q.append(" FROM documento doc JOIN trb_tipocomprobante tip ON doc.doc_id_tipocomprobante = tip.cod_tipocomprobante")
        if (isAuthorized == true) {
            q.append(" WHERE doc.doc_id_estadodocumento = 3")
        } else {
            q.append(" WHERE ( (doc.doc_id_tipocomprobante in (")
            q.append(allowedTiposComprobante.joinToString(", ") { "'$it'" })
            q.append(") AND doc.doc_idproveedor is null AND doc.doc_idcliente is not null AND doc.doc_id_estadodocumento = 2) OR (doc.doc_id_tipocomprobante in ('")
            q.append(TipoComporbanteEnum.LIQUIDACION.codigo).append("', '").append(TipoComporbanteEnum.GUIAS_REMISION.codigo)
            q.append("') AND doc.doc_id_estadodocumento = 2) OR (doc.doc_id_tipocomprobante = '")
            q.append(TipoComporbanteEnum.COMPROBANTE_RETENCION.codigo)
            q.append("' AND doc.doc_idproveedor is not null AND doc.doc_idcliente is null AND doc.doc_id_estadodocumento = 2))")
        }
        if (documentNumber != null) q.append(" AND doc.doc_numerodocumento like CONCAT('%',:documentNumber,'%')")
        if (documentType != null) q.append(" AND doc.doc_id_tipocomprobante = :documentType")
        if (name != null) q.append(" AND UPPER(doc_nombrecomercial_emisor) LIKE UPPER(CONCAT('%',:name,'%'))")
        if (identification != null) q.append(" AND doc.doc_identificacion like CONCAT('%',:identification,'%')")
        if (accesKey != null) q.append(" AND doc.doc_claveacceso like CONCAT('%',:accesKey,'%')")
        if (sortField != null && sortDirection != null) q.append(" ORDER BY ").append(sortField).append(" ").append(sortDirection)
        if (limit != null && offset != null) q.append(" LIMIT :limit OFFSET :offset")
        return q.toString()
    }

    private fun mapRowToEntity(row: Row): DocumentoDto = DocumentoDto(
        idDocumento = row.get("docId", Int::class.javaObjectType),
        numeroDocumento = row.get("numberDoc", String::class.java),
        tipoComprobante = row.get("docType", String::class.java),
        nombreTipoDoc = row.get("docTypeName", String::class.java),
        estadoDocumento = row.get("docState", Int::class.javaObjectType),
        nombreComercial = row.get("docComercialName", String::class.java),
        identificacionDocumento = row.get("docIdentification", String::class.java),
        fechaEmision = row.get("docIssueDate", LocalDateTime::class.java),
        claveAcceso = row.get("docAccesKey", String::class.java)
    )
}
