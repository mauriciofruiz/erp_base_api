package com.sicon.tba.repository.custom;

import com.sicon.tba.models.Proveedor;
import io.r2dbc.spi.Row;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TbaProveedorCustom {

    private final DatabaseClient databaseClient;

    /**
     * Método para construir y guardar un proveedor
     * @param idPaisLista Lista de ID de país
     * @param idEstadoProveedor Estado del proveedor
     * @param sortField Campo por el cual se va a ordenar
     * @param sortDirection Dirección de la ordenación
     * @param limit Límite de registros
     * @param offset Desplazamiento
     * @return Lista de proveedores
     */
    public Flux<Proveedor> findByIdPaisNotInAndEstado(List<Integer> idPaisLista,
                                                      Boolean idEstadoProveedor,
                                                      String sortField,
                                                      String sortDirection,
                                                      Integer limit,
                                                      Long offset) {
        // Convertir la lista de IDs de país en una cadena separada por comas
        String idPaisCsv = idPaisLista.stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));

        String query = "SELECT pr.* FROM tba_proveedor pr " +
                "JOIN tba_persona pe ON pr.id_persona = pe.id_persona " +
                "WHERE pe.id_pais NOT IN (" + idPaisCsv + ") " +
                "AND pr.estado_proveedor = $1 " +
                "ORDER BY " + sortField + " " + sortDirection + " " +
                "LIMIT $2 OFFSET $3";

        return databaseClient.sql(query)
                .bind("$1", idEstadoProveedor)
                .bind("$2", limit)
                .bind("$3", offset)
                .map((row, rowMetadata) -> mapRowToProvider(row)).all();
    }

    private Proveedor mapRowToProvider(Row row) {
        return Proveedor.builder()
                .idProveedor(row.get("id_proveedor", Integer.class))
                .idTipoIdFiscal(row.get("id_tipoidfiscal", String.class))
                .idRetencionIva(row.get("id_retencioniva", Integer.class))
                .idPersona(row.get("id_persona", Integer.class))
                .idBancoCatalogo(row.get("id_bancocatalogo", Integer.class))
                .idRetencionFuente(row.get("id_retencionfuente", Integer.class))
                .idTipoProveedor(row.get("id_tipoprov", Integer.class))
                .idTipoCuentaBancaria(row.get("id_tipocuentabancaria", Integer.class))
                .idCondicionPago(row.get("id_condicionpago", Integer.class))
                .idFormaPagoCobro(row.get("id_formapagocobro", String.class))
                .nombreComercialProveedor(row.get("nombrecomercial_proveedor", String.class))
                .direccion2Proveedor(row.get("direccion2_proveedor", String.class))
                .correo2Proveedor(row.get("correo2_proveedor", String.class))
                .celular2Proveedor(row.get("celular2_proveedor", String.class))
                .telefono2Proveedor(row.get("telefono2_proveedor", String.class))
                .sitioWebProveedor(row.get("sitioweb_proveedor", String.class))
                .contactoProveedor(row.get("contacto_proveedor", String.class))
                .telefonoContactoProveedor(row.get("telefonocontacto_proveedor", String.class))
                .correoContactoProveedor(row.get("correocontacto_proveedor", String.class))
                .celularContactoProveedor(row.get("celularcontacto_proveedor", String.class))
                .creditoProveedor(row.get("credito_proveedor", Boolean.class))
                .montoCreditoProveedor(row.get("montocredito_proveedor", BigDecimal.class))
                .fechaVencimientoCreditoProveedor(row.get("fechavencimientocredito_proveedor", LocalDate.class))
                .diasCreditoProveedor(row.get("diascredito_proveedor", Integer.class))
                .montoGarantiaProveedor(row.get("montogarantia_proveedor", BigDecimal.class))
                .numeroCuentaBancariaProveedor(row.get("numcuentabancaria_proveedor", String.class))
                .referenciaPagoProveedor(row.get("referenciapago_proveedor", String.class))
                .saldoAnticipoProveedor(row.get("saldoanticipo_proveedor", BigDecimal.class))
                .nombreChequeProveedor(row.get("nombrecheque_proveedor", String.class))
                .contribucionEspecialProveedor(row.get("contribespecial_proveedor", String.class))
                .esTransporteProveedor(row.get("estransporte_proveedor", Boolean.class))
                .fechaCreacion(row.get("fecha_creacion", LocalDateTime.class))
                .fechaModificacion(row.get("fecha_modificacion", LocalDateTime.class))
                .idUsuarioCreacion(row.get("id_usuario_creacion", Integer.class))
                .idUsuarioModificacion(row.get("id_usuario_modificacion", Integer.class))
                .estadoProveedor(row.get("estado_proveedor", Boolean.class))
                .numeroCuentaPago(row.get("numero_cuenta_pago", String.class))
                .idIdentificacionFiscalPago(row.get("id_identificacionfiscal_pago", Integer.class))
                .cuentaPasivaProveedor(row.get("cuentapasiva_proveedor", Integer.class))
                .cuentaGastoProveedor(row.get("cuentagasto_proveedor", Integer.class))
                .build();
    }

}
