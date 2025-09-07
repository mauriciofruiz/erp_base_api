package erp.base.api.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum RetencionFuenteEnum {

    HONORARIOS_PROFESIONALES("1", "10% Honorarios profesionales y demás pagos por servicios relacionados con el título profesional"),
    SERVICIOS_PREDOMINA_INTELECTO("2", "8% Servicios predomina el intelecto no relacionados con el título profesional"),
    SERVICIOS_PREDOMINA_MANO_OBRA("3", "2% Servicios predomina la mano de obra"),
    SERVICIOS_PRESTADOS_MEDIOS_COMUNICACION("4", "1% Servicios prestados por medios de comunicación y agencias de publicidad"),
    SERVICIO_TRANSPORTE("5", "1% Servicio de transporte privado de pasajeros o transporte público o privado de carga"),
    TRANSFERENCIA_BIENES_MUEBLES("6", "1% Transferencia de bienes muebles de naturaleza corporal"),
    CUOTAS_ARRENDAMIENTO("7", "1% Cuotas de arrendamiento mercantil, inclusive la de opción de compra"),
    ARRENDAMIENTO_BIENES_INMUEBLES("8", "8% Por arrendamiento bienes inmuebles"),
    SEGUROS_REASEGUROS("9", "1% Seguros y reaseguros (primas y cesiones)"),
    DIVIDENDOS_DISTRIBUIDOS("10", "6.28% Dividendos distribuidos a personas naturales residentes cuando la sociedad que distribuye aplicó la tarifa IR"),
    OTRAS_RETENCIONES("11", "2% Otras retenciones aplicables el 2%"),
    PAGO_NO_RESIDENTES("12", "25% Pago a no residentes - Servicios técnicos, administrativos o de consultoría y regalías"),
    NO_APLICA("13", "NO APLICA"),
    RETENIDO_1("14", "RETENIDO 1%"),
    RETENIDO_2("15", "RETENIDO 2%"),
    TRANSFERENCIA_BIENES_MUEBLES_NATURALEZA_CORPORAL("16", "1.75%  Transf Bienes muebles de naturaleza corporal"),
    PAGOS_LIQUIDACION_COMPRA("17", "2% Pagos a través de liquidación de compra (nivel cultural o rusticidad)"),
    PUBLICIDAD_COMUNICACION("18", "1.75% PUBLICIDAD I COMINICAC."),
    MICROEMPRESAS("19", "1.75 % MICROEMPRESAS"),
    RETENIDO_1_75("20", "RETENIDO 1.75%"),
    OTRAS_RETENCIONES_APLICABLES("21", "2.75% OTRAS RET. APLIC"),
    SEGUROS("22", "1.75% SEGUROS"),
    RETENIDO_2_75_CLIENTES("23", "RETEINDO 2.75% CLIENTES"),
    OTRAS_RETENCIONES_APLICABLES_1("24", "Otras retenciones aplicables el 1%"),
    RETENCION_0("25", "0% RETENCION"),
    RETENCION_DEBITO_BANCARIO("26", "0% RETENCION DEBITO BANCARIO"),
    RETENCION_TARJETA_DEBITO_CREDITO("27", "0% RETENCION TARJETA DEBITO O CREDITO");

    private String id;
    private String descripcion;

    RetencionFuenteEnum(String id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public static String getDescriptionById(String id) {
        for (RetencionFuenteEnum e : values()) {
            if (e.id.equals(id)) {
                return e.descripcion;
            }
        }
        return null;
    }
}
