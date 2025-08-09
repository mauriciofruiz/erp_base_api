package com.sicon.tba.repository;

import com.sicon.tba.models.Proveedor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@Repository
public interface TbaProveedorRepository extends ReactiveCrudRepository<Proveedor, Integer> {

    Flux<Proveedor> findAllBy(Pageable pageable);

    Mono<Proveedor> findByIdPersona(Integer personaId);

    //Verifica si el ya existe una persona asociada al proveedor que se va a crear
    Mono<Boolean> existsByIdPersona(Integer personaId);

    @Query("SELECT COUNT(*) " +
            "FROM documento FACTURA " +
            "WHERE FACTURA.doc_id_estadodocumento NOT IN (6) " +
            "AND FACTURA.doc_id_asiento IS NOT NULL " +
            "AND FACTURA.doc_fechaemision between DATE_TRUNC('MONTH', now()) and now()" +
            "AND FACTURA.doc_idproveedor = :idProveedor" +
            " AND FACTURA.doc_id_tipocomprobante = '01'" +
            " and FACTURA.doc_idproveedor is not null")
    Mono<Integer> numberInvoicesByProvider(Integer idProveedor);

    @Query("SELECT pr.* FROM tba_proveedor pr " +
            "JOIN tba_persona pe ON pr.id_persona = pe.id_persona " +
            "WHERE pe.id_pais NOT IN (:idPaisLista) " +
            "AND pr.estado_proveedor = :idEstadoProveedor " +
            "ORDER BY  LIMIT :limit OFFSET :offset")
    Flux<Proveedor> findByIdPaisNotInAndEstado(List<Integer> idPaisLista, Boolean idEstadoProveedor, String sortField, String sortDirection, Integer limit, Long offset);

    @Query("SELECT COUNT(pr.id_proveedor) FROM tba_proveedor pr " +
            "JOIN tba_persona pe ON pr.id_persona = pe.id_persona " +
            "WHERE pe.id_pais NOT IN (:idPaisLista) " +
            "AND pr.estado_proveedor = :idEstadoProveedor")
    Mono<Long> countByIdPaisNotInAndEstado(List<Integer> idPaisLista, Boolean idEstadoProveedor);

    @Query("SELECT pr.* FROM tba_proveedor pr " +
            "JOIN imp_listapedido pe ON pr.id_proveedor = pe.id_proveedor " +
            "WHERE pe.id_listapedido = :idListaPedido " +
            "AND pe.id_estado = :idEstado")
    Flux<Proveedor> findByOrderListId(Integer idListaPedido, Integer idEstado);

    Flux<Proveedor> findByEsTransporteProveedorAndEstadoProveedor(Boolean esTransporteProveedor, Boolean estadoProveedor);
}
