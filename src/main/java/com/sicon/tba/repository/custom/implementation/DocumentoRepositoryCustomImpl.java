package com.sicon.tba.repository.custom.implementation;

import com.sicon.tba.dto.DocumentoDto;
import com.sicon.tba.enums.TipoComporbanteEnum;
import com.sicon.tba.repository.custom.DocumentoRepositoryCustom;
import io.r2dbc.spi.Row;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class DocumentoRepositoryCustomImpl implements DocumentoRepositoryCustom {
    private final DatabaseClient databaseClient;

    @Override
    public Flux<DocumentoDto> getBySearchParams(
            String documentNumber,
            String documentType,
            String name,
            String identification,
            String accesKey,
            Boolean isAuthorized,
            String sortField,
            String sortDirection,
            Integer limit,
            Integer offset
    ) {
        DatabaseClient.GenericExecuteSpec spec = bindSearchParamsToSpec(documentNumber, documentType, name, identification, accesKey, isAuthorized, sortField, sortDirection, limit, offset, false);
        return spec.map((row, rowMetadata) -> mapRowToEntity(row)).all();
    }

    @Override
    public Mono<Long> getCountBySearchParams(
            String documentNumber,
            String documentType,
            String name,
            String identification,
            String accesKey,
            Boolean isAuthorized
    ) {
        DatabaseClient.GenericExecuteSpec spec = bindSearchParamsToSpec(documentNumber, documentType, name, identification, accesKey, isAuthorized, null, null, null, null, true);
        return spec.map((row, rowMetadata) -> row.get(0, Long.class)).one();
    }

    private DatabaseClient.GenericExecuteSpec bindSearchParamsToSpec(
            String documentNumber,
            String documentType,
            String name,
            String identification,
            String accesKey,
            Boolean isAuthorized,
            String sortField,
            String sortDirection,
            Integer limit,
            Integer offset,
            Boolean isCount
    ) {
        DatabaseClient.GenericExecuteSpec spec = databaseClient.sql(buildQueryForSearch(documentNumber, documentType, name, identification, accesKey, isAuthorized, sortField, sortDirection, limit, offset, isCount));
        if (documentNumber != null)
            spec = spec.bind("documentNumber", documentNumber);
        if (documentType != null)
            spec = spec.bind("documentType", documentType);
        if (name != null)
            spec = spec.bind("name", name);
        if (identification != null)
            spec = spec.bind("identification", identification);
        if (accesKey != null)
            spec = spec.bind("accesKey", accesKey);
        if (limit != null && offset != null)
            spec = spec.bind("limit", limit)
                    .bind("offset", offset);
        return spec;
    }

    private String buildQueryForSearch(
            String documentNumber,
            String documentType,
            String name,
            String identification,
            String accesKey,
            Boolean isAuthorized,
            String sortField,
            String sortDirection,
            Integer limit,
            Integer offset,
            Boolean isCount
    ) {
        List<String> allowedTiposComprobante = Arrays.asList(
                TipoComporbanteEnum.FACTURA.getCodigo(),
                TipoComporbanteEnum.NOTA_DEBITO.getCodigo(),
                TipoComporbanteEnum.NOTA_CREDITO.getCodigo()
        );
        StringBuilder queryBuilder = new StringBuilder(isCount ? "SELECT COUNT(*)" : "SELECT doc.doc_id_documento as docId, doc.doc_numerodocumento as numberDoc, doc.doc_id_tipocomprobante as docType, tip.nombre_tipocomprobante as docTypeName, doc.doc_id_estadodocumento as docState, doc.doc_nombrecomercial_emisor as docComercialName, doc.doc_identificacion as docIdentification, doc.doc_fechaemision as docIssueDate, doc.doc_claveacceso as docAccesKey");
        queryBuilder.append(" FROM documento doc");
        queryBuilder.append(" JOIN trb_tipocomprobante tip ON");
        queryBuilder.append(" doc.doc_id_tipocomprobante = tip.cod_tipocomprobante");
        if (isAuthorized)
            queryBuilder.append(" WHERE  doc.doc_id_estadodocumento = 3");
        else {
            queryBuilder.append(" WHERE (");
            queryBuilder.append(" (doc.doc_id_tipocomprobante in (");
            for (int i = 0; i < allowedTiposComprobante.size(); i++) {
                queryBuilder.append("'").append(allowedTiposComprobante.get(i)).append("'");
                if (i < allowedTiposComprobante.size() - 1) {
                    queryBuilder.append(", ");
                }
            }
            queryBuilder.append(")");
            queryBuilder.append(" AND doc.doc_idproveedor is null");
            queryBuilder.append(" AND doc.doc_idcliente is not null");
            queryBuilder.append(" AND  doc.doc_id_estadodocumento = 2)");
            queryBuilder.append(" OR");
            queryBuilder.append(" (doc.doc_id_tipocomprobante in (");
            queryBuilder.append("'").append(TipoComporbanteEnum.LIQUIDACION.getCodigo()).append("'");
            queryBuilder.append(", ");
            queryBuilder.append("'").append(TipoComporbanteEnum.GUIAS_REMISION.getCodigo()).append("'");
            queryBuilder.append(")");
            queryBuilder.append(" AND  doc.doc_id_estadodocumento = 2)");
            queryBuilder.append(" OR");
            queryBuilder.append(" (doc.doc_id_tipocomprobante = '");
            queryBuilder.append(TipoComporbanteEnum.COMPROBANTE_RETENCION.getCodigo());
            queryBuilder.append("'");
            queryBuilder.append(" AND doc.doc_idproveedor is not null");
            queryBuilder.append(" AND doc.doc_idcliente is null");
            queryBuilder.append("  AND  doc.doc_id_estadodocumento = 2))");
        }
        if (documentNumber != null)
            queryBuilder.append(" AND doc.doc_numerodocumento like CONCAT('%',:documentNumber,'%')");
        if (documentType != null)
            queryBuilder.append(" AND doc.doc_id_tipocomprobante = :documentType");
        if (name != null)
            queryBuilder.append(" AND UPPER(doc_nombrecomercial_emisor) LIKE UPPER(CONCAT('%',:name,'%'))");
        if (identification != null)
            queryBuilder.append(" AND doc.doc_identificacion like CONCAT('%',:identification,'%')");
        if (accesKey != null)
            queryBuilder.append(" AND doc.doc_claveacceso like CONCAT('%',:accesKey,'%')");
        if (sortField != null && sortDirection != null)
            queryBuilder.append(" ORDER BY ").append(sortField).append(" ").append(sortDirection);
        if (limit != null && offset != null)
            queryBuilder.append(" LIMIT :limit OFFSET :offset");

        return queryBuilder.toString();
    }

    private DocumentoDto mapRowToEntity(Row row) {
        return DocumentoDto.builder()
                .idDocumento(row.get("docId", Integer.class))
                .numeroDocumento(row.get("numberDoc", String.class))
                .tipoComprobante(row.get("docType", String.class))
                .nombreTipoDoc(row.get("docTypeName", String.class))
                .estadoDocumento(row.get("docState", Integer.class))
                .nombreComercial(row.get("docComercialName", String.class))
                .identificacionDocumento(row.get("docIdentification", String.class))
                .fechaEmision(row.get("docIssueDate", LocalDateTime.class))
                .claveAcceso(row.get("docAccesKey", String.class))
                .build();
    }
}
