package com.sicon.tba.repository.custom;

import com.sicon.tba.dto.TbaPersonaDto;
import com.sicon.tba.dto.response.PersonaClienteResponse;
import io.r2dbc.spi.Row;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class TbaPersonaCustom {

    private final DatabaseClient databaseClient;


    public Flux<PersonaClienteResponse> getClientPersonListByNameOrIdentification(String completeName, String identification) {

        DatabaseClient.GenericExecuteSpec spec = databaseClient.sql(buildQueryForSearch(completeName, identification));
        if (completeName != null)
            spec = spec.bind("nombre", completeName);
        if (identification != null)
            spec = spec.bind("identificacion", identification);
        return spec.map((row, rowMetadata) -> mapRowToEntity(row)).all();
    }

    private String buildQueryForSearch(String completeName, String identification) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT TP.ID_PERSONA as personaId, cli.ID_CLIENTE as clienteId, TP.IDENTIFICACION_PERSONA as identificacion, TP.NOMBRE_PERSONA as nombre, TP.CORREO_PERSONA as email, TP.TELEFONO_PERSONA as telefono, ");
        queryBuilder.append("cli.NOMBRECOMERCIAL_CLIENTE as nombreCliente, credcli.CUPOAUTORIZADO_CREDITOCLIENTE as cupoAutorizado, credcli.CUPOUTILIZADO_CREDITOCLIENTE as cupoUtilizado, ");
        queryBuilder.append("credcli.SOBRECUPO_CREDITOCLIENTE as sobreCupo, credcli.CUPODISPONIBLE_CREDITOCLIENTE as cupoDisponible, catcli.NOMBRE_CATEGORIACLIENTE as categoriaCliente, ");
        queryBuilder.append("cli.CONTACTO_CLIENTE as contactoCliente, credcli.DIAS_CREDITOCLIENTE as diasCredito, cli.cliente_placa as placa, idf.cod_identificacionfiscal as codigoIdentificacionTributaria");
        queryBuilder.append(" FROM TBA_PERSONA TP");
        queryBuilder.append(" JOIN TBA_CLIENTE cli ON TP.ID_PERSONA = cli.ID_PERSONA");
        queryBuilder.append(" JOIN TBA_CATEGORIACLIENTE catcli ON cli.ID_CATEGORIACLIENTE = catcli.ID_CATEGORIACLIENTE");
        queryBuilder.append(" LEFT JOIN TBA_CREDITOCLIENTE credcli ON cli.ID_CLIENTE = credcli.ID_CLIENTE");
        queryBuilder.append(" LEFT JOIN trb_identificacionfiscal idf ON TP.id_identificacionfiscal = idf.id_identificacionfiscal");
        queryBuilder.append(" WHERE TP.ESCLIENTE_PERSONA");
        if (completeName != null) {
            queryBuilder.append(" AND UPPER(TP.NOMBRE_PERSONA) LIKE UPPER(CONCAT('%',:nombre,'%'))");
        }
        if (identification != null) {
            queryBuilder.append(" AND UPPER(TP.IDENTIFICACION_PERSONA) LIKE UPPER(CONCAT('%',:identificacion,'%'))");
        }
        return queryBuilder.toString();
    }

    private PersonaClienteResponse mapRowToEntity(Row row) {
        return PersonaClienteResponse.builder()
                .personaId(row.get("personaId", Integer.class))
                .clienteId(row.get("clienteId", Integer.class))
                .identificacion(row.get("identificacion", String.class))
                .nombre(row.get("nombre", String.class))
                .email(row.get("email", String.class))
                .telefono(row.get("telefono", String.class))
                .nombreCliente(row.get("nombreCliente", String.class))
                .cupoAutorizado(row.get("cupoAutorizado", BigDecimal.class) != null ? row.get("cupoAutorizado", BigDecimal.class) : BigDecimal.ZERO)
                .cupoUtilizado(row.get("cupoUtilizado", BigDecimal.class) != null ? row.get("cupoUtilizado", BigDecimal.class) : BigDecimal.ZERO)
                .sobreCupo(row.get("sobreCupo", BigDecimal.class) != null ? row.get("sobreCupo", BigDecimal.class) : BigDecimal.ZERO)
                .cupoDisponible(row.get("cupoDisponible", BigDecimal.class) != null ? row.get("cupoDisponible", BigDecimal.class) : BigDecimal.ZERO)
                .categoriaCliente(row.get("categoriaCliente", String.class))
                .contactoCliente(row.get("contactoCliente", String.class))
                .diasCredito(row.get("diasCredito", Integer.class) != null ? row.get("diasCredito", Integer.class) : 0)
                .placa(row.get("placa", String.class))
                .codigoIdentificacionTributaria(row.get("codigoIdentificacionTributaria", String.class))
                .build();
    }

    public Flux<TbaPersonaDto> getPersonByParams(String completeName, String identification) {
        DatabaseClient.GenericExecuteSpec spec = databaseClient.sql(buildQueryForSearchPerson(completeName, identification));
        if (completeName != null)
            spec = spec.bind("nombre", completeName);
        if (identification != null)
            spec = spec.bind("identificacion", identification);
        return spec.map((row, rowMetadata) -> mapRowToEntityPerson(row)).all();
    }

    private String buildQueryForSearchPerson(String completeName, String identification) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT TP.ID_PERSONA as personaId, TP.DIRECCION_PERSONA as direccion, TP.IDENTIFICACION_PERSONA as identificacion, TP.NOMBRE_PERSONA as nombre, TP.CORREO_PERSONA as email, TP.TELEFONO_PERSONA as telefono ");
        queryBuilder.append(" FROM TBA_PERSONA TP");
        if (completeName != null) {
            queryBuilder.append(" WHERE UPPER(TP.NOMBRE_PERSONA) LIKE UPPER(CONCAT('%',:nombre,'%'))");
        }
        if (identification != null) {
            queryBuilder.append(" WHERE UPPER(TP.IDENTIFICACION_PERSONA) LIKE UPPER(CONCAT('%',:identificacion,'%'))");
        }
        return queryBuilder.toString();
    }

    private TbaPersonaDto mapRowToEntityPerson(Row row) {
        return TbaPersonaDto.builder()
                .idPersona(row.get("personaId", Integer.class))
                .identificacionPersona(row.get("identificacion", String.class))
                .nombrePersona(row.get("nombre", String.class))
                .correoPersona(row.get("email", String.class))
                .telefonoPersona(row.get("telefono", String.class))
                .direccionPersona(row.get("direccion", String.class))
                .build();
    }
}
