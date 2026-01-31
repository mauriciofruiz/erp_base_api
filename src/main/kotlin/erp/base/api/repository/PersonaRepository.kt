package erp.base.api.repository

import erp.base.api.models.Persona
import org.springframework.data.domain.Pageable
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface PersonaRepository : ReactiveCrudRepository<Persona, Int> {

    fun findByIdentificacionPersona(identificacionPersona: String): Mono<Persona>

    fun findAllBy(pageable: Pageable): Flux<Persona>

    @Query(value = "SELECT * FROM PERSONA WHERE TBA_PERSONA.ID_PERSONA IN (SELECT DISTINCT (PERSONA.ID_PERSONA) FROM PROVEEDOR PROVEEDOR JOIN PERSONA PERSONA ON PROVEEDOR.ID_PERSONA = PERSONA.ID_PERSONA AND PERSONA.ESPROVEEDOR_PERSONA JOIN PROVEEDORPRODUCTO PROVPRODUCTO ON PROVEEDOR.ID_PROVEEDOR = PROVPRODUCTO.ID_PROVEEDOR AND PROVPRODUCTO.ACTIVO_PROVEEDORPRODUCTO JOIN PRODUCTO PRODUCTO ON PROVPRODUCTO.ID_PRODUCTO = PRODUCTO.ID_PRODUCTO AND PRODUCTO.ESTADO_PRODUCTO JOIN DETSOLICITUDCOMPRAPRODUCTO DETALLE ON PRODUCTO.ID_PRODUCTO = DETALLE.ID_PRODUCTO AND DETALLE.ESTADO_DETSOLICITUDCOMPRAPROD and (DETALLE.gestionado_detsolicitudcompraprod or DETALLE.gestionado_detsolicitudcompraprod is null) AND DETALLE.cantidadaprobada_detsolicitudcompraprod - DETALLE.cantidadgestionada_detsolicitudcompraprod > 0 JOIN SOLICITUDCOMPRA SOLICITUD ON DETALLE.ID_SOLICITUDCOMPRA = SOLICITUD.ID_SOLICITUDCOMPRA AND SOLICITUD.ID_ESTADOSOLICITUD IN (3, 6) AND SOLICITUD.id_tipoadquisicion = 1 WHERE PROVEEDOR.ESTADO_PROVEEDOR) ORDER BY TBA_PERSONA.NOMBRE_PERSONA;")
    fun findAllPendingPurchaseOrderProvidersPersons(): Flux<Persona>

    @Query(value = "SELECT TP.* FROM PERSONA TP WHERE TP.ESPROVEEDOR_PERSONA AND ( :nombre IS NULL OR UPPER(TP.NOMBRE_PERSONA) LIKE UPPER(CONCAT('%',:nombre,'%'))) AND ( :identificacion IS NULL OR UPPER(TP.IDENTIFICACION_PERSONA) LIKE UPPER(CONCAT('%',:identificacion,'%')));")
    fun getAllPersonProviders(nombre: String?, identificacion: String?): Flux<Persona>

    @Query(value = "SELECT TP.* FROM PERSONA TP WHERE TP.ESCLIENTE_PERSONA AND ( :nombre IS NULL OR UPPER(TP.NOMBRE_PERSONA) LIKE UPPER(CONCAT('%',:nombre,'%'))) AND ( :identificacion IS NULL OR UPPER(TP.IDENTIFICACION_PERSONA) LIKE UPPER(CONCAT('%',:identificacion,'%')));")
    fun getAllPersonClients(nombre: String?, identificacion: String?): Flux<Persona>

    fun existsByIdentificacionPersonaAndEsProveedorPersona(identificacionPersona: String, esProveedorPersona: Boolean): Mono<Boolean>

    fun existsByIdentificacionPersonaAndEsClientePersona(identificacionPersona: String, esClientePersona: Boolean): Mono<Boolean>

    @Query(value = "select distinct on (pers.id_persona) pers.* from imp_listapedido listapedido join tba_proveedor proveedor on listapedido.id_proveedor = proveedor.id_proveedor join tba_persona pers on proveedor.id_persona = pers.id_persona join tba_pais pais on pers.id_pais = pais.id_pais where listapedido.id_listapedido in (select distinct (detalle.id_listapedido) from imp_detlistapedido detalle where detalle.anulado_detlistapedido = false and detalle.cantidad_detlistapedido - detalle.cantoc_detlistapedido > 0) and listapedido.id_estado in (1, 2) and proveedor.estado_proveedor and listapedido.id_aperturaimportacion is not null and pais.codigopais <> '593';")
    fun getAllByListaPedidoAprobadas(): Flux<Persona>
}
