package erp.base.api.repository

import erp.base.api.models.Proveedor
import org.springframework.data.domain.Pageable
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface ProveedorRepository : ReactiveCrudRepository<Proveedor, Int> {

    fun findAllBy(pageable: Pageable): Flux<Proveedor>
    fun findByIdPersona(personaId: Int): Mono<Proveedor>
    fun existsByIdPersona(personaId: Int): Mono<Boolean>

    @Query("SELECT COUNT(*) FROM documento FACTURA WHERE FACTURA.doc_id_estadodocumento NOT IN (6) AND FACTURA.doc_id_asiento IS NOT NULL AND FACTURA.doc_fechaemision between DATE_TRUNC('MONTH', now()) and now() AND FACTURA.doc_idproveedor = :idProveedor AND FACTURA.doc_id_tipocomprobante = '01' and FACTURA.doc_idproveedor is not null")
    fun numberInvoicesByProvider(idProveedor: Int): Mono<Int>

    @Query("SELECT pr.* FROM tba_proveedor pr JOIN tba_persona pe ON pr.id_persona = pe.id_persona WHERE pe.id_pais NOT IN (:idPaisLista) AND pr.estado_proveedor = :idEstadoProveedor ORDER BY  LIMIT :limit OFFSET :offset")
    fun findByIdPaisNotInAndEstado(idPaisLista: List<Int>, idEstadoProveedor: Boolean, sortField: String, sortDirection: String, limit: Int, offset: Long): Flux<Proveedor>

    @Query("SELECT COUNT(pr.id_proveedor) FROM tba_proveedor pr JOIN tba_persona pe ON pr.id_persona = pe.id_persona WHERE pe.id_pais NOT IN (:idPaisLista) AND pr.estado_proveedor = :idEstadoProveedor")
    fun countByIdPaisNotInAndEstado(idPaisLista: List<Int>, idEstadoProveedor: Boolean): Mono<Long>

    @Query("SELECT pr.* FROM tba_proveedor pr JOIN imp_listapedido pe ON pr.id_proveedor = pe.id_proveedor WHERE pe.id_listapedido = :idListaPedido AND pe.id_estado = :idEstado")
    fun findByOrderListId(idListaPedido: Int, idEstado: Int): Flux<Proveedor>

    fun findByEsTransporteProveedorAndEstadoProveedor(esTransporteProveedor: Boolean, estadoProveedor: Boolean): Flux<Proveedor>
}
