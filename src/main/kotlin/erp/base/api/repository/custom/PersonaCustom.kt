package erp.base.api.repository.custom

import erp.base.api.dto.PersonaDto
import erp.base.api.dto.response.PersonaClienteResponse
import io.r2dbc.spi.Row
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import java.math.BigDecimal

@Component
class PersonaCustom(private val databaseClient: DatabaseClient) {

    fun getClientPersonListByNameOrIdentification(completeName: String?, identification: String?): Flux<PersonaClienteResponse> {
        var spec = databaseClient.sql(buildQueryForSearch(completeName, identification))
        if (completeName != null) spec = spec.bind("nombre", completeName)
        if (identification != null) spec = spec.bind("identificacion", identification)
        return spec.map { row, _ -> mapRowToEntity(row) }.all()
    }

    fun getPersonByParams(completeName: String?, identification: String?): Flux<PersonaDto> {
        var spec = databaseClient.sql(buildQueryForSearchPerson(completeName, identification))
        if (completeName != null) spec = spec.bind("nombre", completeName)
        if (identification != null) spec = spec.bind("identificacion", identification)
        return spec.map { row, _ -> mapRowToEntityPerson(row) }.all()
    }

    private fun buildQueryForSearch(completeName: String?, identification: String?): String {
        var q = "SELECT TP.ID_PERSONA as personaId, cli.ID_CLIENTE as clienteId, TP.IDENTIFICACION_PERSONA as identificacion, TP.NOMBRE_PERSONA as nombre, TP.CORREO_PERSONA as email, TP.TELEFONO_PERSONA as telefono, "
        q += "cli.NOMBRECOMERCIAL_CLIENTE as nombreCliente, credcli.CUPOAUTORIZADO_CREDITOCLIENTE as cupoAutorizado, credcli.CUPOUTILIZADO_CREDITOCLIENTE as cupoUtilizado, credcli.SOBRECUPO_CREDITOCLIENTE as sobreCupo, credcli.CUPODISPONIBLE_CREDITOCLIENTE as cupoDisponible, catcli.NOMBRE_CATEGORIACLIENTE as categoriaCliente, cli.CONTACTO_CLIENTE as contactoCliente, credcli.DIAS_CREDITOCLIENTE as diasCredito, cli.cliente_placa as placa, idf.cod_identificacionfiscal as codigoIdentificacionTributaria FROM PERSONA TP JOIN CLIENTE cli ON TP.ID_PERSONA = cli.ID_PERSONA JOIN CATEGORIACLIENTE catcli ON cli.ID_CATEGORIACLIENTE = catcli.ID_CATEGORIACLIENTE LEFT JOIN CREDITOCLIENTE credcli ON cli.ID_CLIENTE = credcli.ID_CLIENTE LEFT JOIN trb_identificacionfiscal idf ON TP.id_identificacionfiscal = idf.id_identificacionfiscal WHERE TP.ESCLIENTE_PERSONA"
        if (completeName != null) q += " AND UPPER(TP.NOMBRE_PERSONA) LIKE UPPER(CONCAT('%',:nombre,'%'))"
        if (identification != null) q += " AND UPPER(TP.IDENTIFICACION_PERSONA) LIKE UPPER(CONCAT('%',:identificacion,'%'))"
        return q
    }

    private fun buildQueryForSearchPerson(completeName: String?, identification: String?): String {
        var q = "SELECT TP.ID_PERSONA as personaId, TP.DIRECCION_PERSONA as direccion, TP.IDENTIFICACION_PERSONA as identificacion, TP.NOMBRE_PERSONA as nombre, TP.CORREO_PERSONA as email, TP.TELEFONO_PERSONA as telefono FROM PERSONA TP"
        var first = true
        if (completeName != null) { q += " WHERE UPPER(TP.NOMBRE_PERSONA) LIKE UPPER(CONCAT('%',:nombre,'%'))"; first = false }
        if (identification != null) q += if (first) " WHERE UPPER(TP.IDENTIFICACION_PERSONA) LIKE UPPER(CONCAT('%',:identificacion,'%'))" else " AND UPPER(TP.IDENTIFICACION_PERSONA) LIKE UPPER(CONCAT('%',:identificacion,'%'))"
        return q
    }

    private fun mapRowToEntity(row: Row): PersonaClienteResponse = PersonaClienteResponse(
        personaId = row.get("personaId", Int::class.javaObjectType),
        clienteId = row.get("clienteId", Int::class.javaObjectType),
        identificacion = row.get("identificacion", String::class.java),
        nombre = row.get("nombre", String::class.java),
        email = row.get("email", String::class.java),
        telefono = row.get("telefono", String::class.java),
        nombreCliente = row.get("nombreCliente", String::class.java),
        cupoAutorizado = row.get("cupoAutorizado", BigDecimal::class.java) ?: BigDecimal.ZERO,
        cupoUtilizado = row.get("cupoUtilizado", BigDecimal::class.java) ?: BigDecimal.ZERO,
        sobreCupo = row.get("sobreCupo", BigDecimal::class.java) ?: BigDecimal.ZERO,
        cupoDisponible = row.get("cupoDisponible", BigDecimal::class.java) ?: BigDecimal.ZERO,
        categoriaCliente = row.get("categoriaCliente", String::class.java),
        contactoCliente = row.get("contactoCliente", String::class.java),
        diasCredito = row.get("diasCredito", Int::class.javaObjectType) ?: 0,
        placa = row.get("placa", String::class.java),
        codigoIdentificacionTributaria = row.get("codigoIdentificacionTributaria", String::class.java)
    )

    private fun mapRowToEntityPerson(row: Row): PersonaDto = PersonaDto(
        idPersona = row.get("personaId", Int::class.javaObjectType),
        identificacionPersona = row.get("identificacion", String::class.java),
        nombrePersona = row.get("nombre", String::class.java),
        correoPersona = row.get("email", String::class.java),
        telefonoPersona = row.get("telefono", String::class.java),
        direccionPersona = row.get("direccion", String::class.java)
    )
}
