-- Initial DDL: erp schema (erp_base_db.erp.table_name)
-- Generated from current public schema structure

CREATE TABLE erp."tipocontribuyente" (
    "id_tipocontribuyente" VARCHAR(255) NOT NULL,
    "nombre_tipocontribuyente" VARCHAR(255) NOT NULL,
    "cod_tipocontribuyente" VARCHAR(255) NOT NULL,
    PRIMARY KEY ("id_tipocontribuyente")
);

CREATE TABLE erp."acceso_documento" (
    "id_acceso_documento" SERIAL NOT NULL,
    "id_tipo_documento" INTEGER,
    "id_documento" INTEGER,
    "name_usuario" VARCHAR(255),
    PRIMARY KEY ("id_acceso_documento")
);

CREATE TABLE erp."adquiriente" (
    "id_adquiriente" SERIAL NOT NULL,
    "nombre_adquiriente" VARCHAR(255),
    "estado_adquiriente" BOOLEAN,
    "id_usuario_creacion" INTEGER,
    "id_usuario_modificacion" INTEGER,
    "fecha_creacion" TIMESTAMP,
    "fecha_modificacion" TIMESTAMP,
    PRIMARY KEY ("id_adquiriente")
);

CREATE TABLE erp."anticipotransporte" (
    "id_anticipotransporte" SERIAL NOT NULL,
    "id_factura" INTEGER,
    "id_asiento" INTEGER,
    "id_detcobro" INTEGER,
    "desc_anticipotransporte" VARCHAR(255),
    "val_anticipotransporte" NUMERIC(19,2),
    "liquidado_anticipotransporte" BOOLEAN,
    "anulado_anticipotransporte" BOOLEAN,
    "fecha_anticipotransporte" DATE,
    "codsucursal_anticipotransporte" VARCHAR(255),
    PRIMARY KEY ("id_anticipotransporte")
);

CREATE TABLE erp."aperturacaja" (
    "id_aperturacaja" SERIAL NOT NULL,
    "id_caja" INTEGER,
    "id_usr" INTEGER,
    "id_asiento" INTEGER,
    "valorinicial_aperturacaja" NUMERIC(19,2),
    "valorfinal_aperturacaja" NUMERIC(19,2),
    "fechaapertura_aperturacaja" TIMESTAMP,
    "fechacierre_aperturacaja" TIMESTAMP,
    "estado_aperturacaja" BOOLEAN,
    "esmanual_aperturacaja" BOOLEAN,
    PRIMARY KEY ("id_aperturacaja")
);

CREATE TABLE erp."area" (
    "id_area" SERIAL NOT NULL,
    "nombre_area" VARCHAR(255),
    "descripcion_area" VARCHAR(255),
    PRIMARY KEY ("id_area")
);

CREATE TABLE erp."areagrupo" (
    "id_areagrupo" SERIAL NOT NULL,
    "nombre_areagrupo" VARCHAR(255),
    PRIMARY KEY ("id_areagrupo")
);

CREATE TABLE erp."asiento" (
    "id_asiento" SERIAL NOT NULL,
    "id_estadoasiento" INTEGER,
    "id_usr" INTEGER,
    "id_tipomovimiento" INTEGER,
    "id_sucursal" INTEGER,
    "referencia_asiento" VARCHAR(255),
    "obs_asiento" TEXT,
    "debetotal_asiento" NUMERIC(19,2),
    "habertotal_asiento" NUMERIC(19,2),
    "fecha_asiento" DATE,
    "fechamodificacion_asiento" TIMESTAMP,
    "fechacontabilidad_asiento" TIMESTAMP,
    "automatico_asiento" BOOLEAN,
    "motivomodificacion_asiento" TEXT,
    "motivoanulacion_asiento" TEXT,
    "docexterno_asiento" VARCHAR(255),
    "codusrcrea_asiento" VARCHAR(255),
    "anulado_asiento" BOOLEAN,
    "numdoc_asiento" VARCHAR(255),
    "revisado_asiento" BOOLEAN,
    "codusranula_asiento" INTEGER,
    PRIMARY KEY ("id_asiento")
);

CREATE TABLE erp."banco" (
    "id_banco" SERIAL NOT NULL,
    "id_cuenta" INTEGER,
    "id_tipocuentabancaria" INTEGER,
    "nombre_banco" VARCHAR(255),
    "numerocuenta_banco" VARCHAR(255),
    "saldodisponible_banco" NUMERIC(19,2),
    "saldocontable_banco" NUMERIC(19,2),
    "contacto_banco" VARCHAR(255),
    "titulocontacto_banco" VARCHAR(255),
    "telefonocontacto_banco" VARCHAR(20),
    "numchequeimp_banco" INTEGER,
    "ultimonumcheque_banco" INTEGER,
    "escajachica_banco" BOOLEAN,
    "estado_banco" BOOLEAN,
    "chequerecuperado_banco" VARCHAR(255),
    "txtdeposito_banco" VARCHAR(255),
    "txttarjeta_banco" VARCHAR(255),
    "saldoinicial_banco" NUMERIC(19,2),
    "id_sucursal" INTEGER,
    PRIMARY KEY ("id_banco")
);

CREATE TABLE erp."bancocatalogo" (
    "id_bancocatalogo" SERIAL NOT NULL,
    "nombre_bancocatalogo" VARCHAR(255),
    "codigo_bancocatalogo" VARCHAR(50),
    "estado_banco" BOOLEAN,
    "id_usuario_creacion" INTEGER,
    "id_usuario_modificacion" INTEGER,
    "fecha_creacion" TIMESTAMP,
    "fecha_modificacion" TIMESTAMP,
    PRIMARY KEY ("id_bancocatalogo")
);

CREATE TABLE erp."bitacora" (
    "id_bitacora" SERIAL NOT NULL,
    "id_usr" INTEGER,
    "identificador_bitacora" INTEGER,
    "table_bitacora" VARCHAR(255),
    "campo_bitacora" VARCHAR(255),
    "oldval_bitacora" VARCHAR(255),
    "newval_bitacora" VARCHAR(255),
    "fecha_bitacora" TIMESTAMPTZ,
    "ipdispositivo_bitacora" VARCHAR(255),
    "nombredispositivo_bitacora" VARCHAR(255),
    PRIMARY KEY ("id_bitacora")
);

CREATE TABLE erp."bloqueo" (
    "id_bloqueo" SERIAL NOT NULL,
    "ip_bloqueo" VARCHAR(255),
    "descripcion_bloqueo" VARCHAR(255),
    "estado_bloqueo" BOOLEAN,
    PRIMARY KEY ("id_bloqueo")
);

CREATE TABLE erp."caja" (
    "id_caja" SERIAL NOT NULL,
    "id_cuenta" INTEGER,
    "id_sucursal" INTEGER,
    "nombre_caja" VARCHAR(255),
    "numero_caja" INTEGER,
    "secfac_caja" INTEGER,
    "secguia_caja" INTEGER,
    "secnc_caja" INTEGER,
    "secnd_caja" INTEGER,
    "secret_caja" INTEGER,
    "estado_caja" BOOLEAN,
    "secliq_caja" INTEGER,
    PRIMARY KEY ("id_caja")
);

CREATE TABLE erp."calendariopago" (
    "id_calendariopago" SERIAL NOT NULL,
    "id_pago" INTEGER,
    "id_facturaproveedor" INTEGER,
    "ncuota_calendariopago" INTEGER,
    "totalcuotas_calendariopago" INTEGER,
    "fechavence_calendariopago" DATE,
    "valorfactura_calendariopago" NUMERIC(19,2),
    "saldo_calendariopago" NUMERIC(19,2),
    "valcruzado_calendariopago" NUMERIC(19,2),
    "codanticipo_calendariopago" VARCHAR(255),
    "obs_calendariopago" VARCHAR(255),
    "codnc_calendariopago" VARCHAR(255),
    "devolucionnc_calendarpago" NUMERIC(19,2),
    "saldoanticipo_calendarpago" NUMERIC(19,2),
    "saldoncdesc_calendarpago" NUMERIC(19,2),
    "valorpago_calendarpago" NUMERIC(19,2),
    "estado_calendariopago" BOOLEAN,
    "dias_calendariopago" INTEGER,
    "montofactura_calendarpago" NUMERIC(19,2),
    "pagado_calendariopago" BOOLEAN,
    "saldofactura_calendariopago" NUMERIC(19,2),
    PRIMARY KEY ("id_calendariopago")
);

CREATE TABLE erp."cargafamiliar" (
    "id_cargafml" SERIAL NOT NULL,
    "id_parentesco" INTEGER,
    "id_empleado" INTEGER,
    "nombre_cargafml" VARCHAR(255),
    "cedula_cargafml" VARCHAR(255),
    "fechanacimiento_cargafml" DATE,
    "capacidadespecial_cargafml" BOOLEAN,
    "archivo_cargafml" VARCHAR(255),
    "activa_cargafml" BOOLEAN,
    PRIMARY KEY ("id_cargafml")
);

CREATE TABLE erp."cargo" (
    "id_cargo" SERIAL NOT NULL,
    "id_departamento" INTEGER,
    "nombre_cargo" VARCHAR(255),
    "estado_cargo" BOOLEAN,
    PRIMARY KEY ("id_cargo")
);

CREATE TABLE erp."casosverificacion" (
    "id_casosverificacion" SERIAL NOT NULL,
    "nombre_casosverificacion" VARCHAR(255),
    PRIMARY KEY ("id_casosverificacion")
);

CREATE TABLE erp."categoriacliente" (
    "id_categoriacliente" SERIAL NOT NULL,
    "nombre_categoriacliente" VARCHAR(255),
    "descuento_categoriacliente" NUMERIC(19,2),
    "categoriaespecial_categoriacliente" BOOLEAN,
    "activocategoria_categoriacliente" BOOLEAN,
    PRIMARY KEY ("id_categoriacliente")
);

CREATE TABLE erp."categoriasucursal" (
    "id_categoriasucursal" VARCHAR(255) NOT NULL,
    "nombre_categoriasucursal" VARCHAR(255),
    "descripcion_categoriasucursal" VARCHAR(255),
    PRIMARY KEY ("id_categoriasucursal")
);

CREATE TABLE erp."ciudad" (
    "id_ciudad" SERIAL NOT NULL,
    "id_provincia" VARCHAR(255),
    "nombre_ciudad" VARCHAR(255),
    "codigo_ciudad" VARCHAR(50),
    PRIMARY KEY ("id_ciudad")
);

CREATE TABLE erp."cliente" (
    "id_cliente" SERIAL NOT NULL,
    "id_categoriacliente" INTEGER,
    "id_origeningreso" INTEGER,
    "id_tipoidfiscal" VARCHAR(255),
    "id_persona" INTEGER,
    "id_cuenta" INTEGER,
    "nombrecomercial_cliente" VARCHAR(255),
    "direccion2_cliente" VARCHAR(500),
    "correo2_cliente" VARCHAR(100),
    "celular2_cliente" VARCHAR(20),
    "telefono2_cliente" VARCHAR(20),
    "saldoafavor_cliente" NUMERIC(19,2),
    "contacto_cliente" VARCHAR(255),
    "cargocontacto_cliente" VARCHAR(255),
    "telefonocontacto_cliente" VARCHAR(20),
    "correocontacto_cliente" VARCHAR(100),
    "celularcontacto_cliente" VARCHAR(20),
    "descuentoespecial_cliente" NUMERIC(19,2),
    "fechacaducidaddesc_cliente" DATE,
    "obsdesc_cliente" TEXT,
    "alerta_general" VARCHAR(255),
    "alerta_facturacion" VARCHAR(255),
    "alerta_cobro" VARCHAR(255),
    "coddesbloqueo_cliente" VARCHAR(255),
    "numcambio_cliente" INTEGER,
    "motivoinactiva_cliente" TEXT,
    "estado_cliente" BOOLEAN,
    "id_usuario_ventas" INTEGER,
    "faccontado_cliente" BOOLEAN,
    "categoriaespecial_cliente" VARCHAR(255),
    "politica_cliente" VARCHAR(255),
    "descuento_cliente" NUMERIC(19,2),
    "id_retencioniva" INTEGER,
    "id_retencionfuente" INTEGER,
    "cliente_placa" VARCHAR(255),
    "id_usuario_creacion" INTEGER,
    "id_usuario_modificacion" INTEGER,
    "fecha_creacion" TIMESTAMP,
    "fecha_modificacion" TIMESTAMP,
    PRIMARY KEY ("id_cliente")
);

CREATE TABLE erp."cobro" (
    "id_cobro" SERIAL NOT NULL,
    "id_usr" INTEGER,
    "id_asiento" INTEGER,
    "id_caja" INTEGER,
    "id_cliente" INTEGER,
    "id_aperturacaja" INTEGER,
    "monto_cobro" NUMERIC(19,2),
    "fecha_cobro" TIMESTAMP,
    "formapago_cobro" VARCHAR(255),
    "anticipo_cobro" BOOLEAN,
    "anulado_cobro" BOOLEAN,
    "anticipotransporte_cobro" BOOLEAN,
    "codnc_cobro" VARCHAR(255),
    "esrecuperacion_cobro" BOOLEAN,
    "motivoanulacion_cobro" TEXT,
    "fechaedicionlote_cobro" TIMESTAMP,
    "usuarioeditalote_cobro" VARCHAR(255),
    PRIMARY KEY ("id_cobro")
);

CREATE TABLE erp."codigosectorial" (
    "id_codigosectorial" VARCHAR(50) NOT NULL,
    "nombre_codigosectorial" VARCHAR(50),
    "cod_codigosectorial" VARCHAR(50),
    PRIMARY KEY ("id_codigosectorial")
);

CREATE TABLE erp."condicionpago" (
    "id_condicionpago" SERIAL NOT NULL,
    "nombre_condicionpago" VARCHAR(255),
    PRIMARY KEY ("id_condicionpago")
);

CREATE TABLE erp."contactoemergencia" (
    "id_contactoemergencia" SERIAL NOT NULL,
    "id_empleado" INTEGER,
    "id_parentesco" INTEGER,
    "nombre_contactoemergencia" VARCHAR(255),
    "celular_contactoemergencia" VARCHAR(20),
    "telefono_contactoemergencia" VARCHAR(20),
    PRIMARY KEY ("id_contactoemergencia")
);

CREATE TABLE erp."contratolaboral" (
    "id_contrato" SERIAL NOT NULL,
    "id_tipocontrato" INTEGER,
    "id_modalidadcontrato" INTEGER,
    "id_empleado" INTEGER,
    "fechainicio_contrato" DATE,
    "finprueba_contrato" DATE,
    "fechafin_contrato" DATE,
    "estado_contrato" BOOLEAN,
    "nrohoras_contrato" NUMERIC(19,2),
    PRIMARY KEY ("id_contrato")
);

CREATE TABLE erp."creditocliente" (
    "id_creditocliente" SERIAL NOT NULL,
    "id_cliente" INTEGER,
    "cupoautorizado_creditocliente" NUMERIC(19,2),
    "cupodisponible_creditocliente" NUMERIC(19,2),
    "cupoutilizado_creditocliente" NUMERIC(19,2),
    "montogarantia_creditocliente" NUMERIC(19,2),
    "fecha_garantia" TIMESTAMP,
    "dias_creditocliente" INTEGER,
    "obs_creditocliente" TEXT,
    "sobrecupo_creditocliente" NUMERIC(19,2),
    "fechavctosobrecupo_creditocliente" TIMESTAMP,
    "id_usuario_creacion" INTEGER,
    "id_usuario_modificacion" INTEGER,
    "fecha_creacion" TIMESTAMP,
    "fecha_modificacion" TIMESTAMP,
    PRIMARY KEY ("id_creditocliente")
);

CREATE TABLE erp."cuenta" (
    "id_cuenta" SERIAL NOT NULL,
    "id_usr" INTEGER,
    "id_indicefinanciero" INTEGER,
    "id_tipocuenta" INTEGER,
    "numero_cuenta" VARCHAR(255),
    "numero_cuentapadre" VARCHAR(255),
    "nombre_cuenta" VARCHAR(255),
    "descripcion_cuenta" VARCHAR(255),
    "saldoinicial_cuenta" NUMERIC(19,2),
    "balance_cuenta" NUMERIC(19,2),
    "manual_cuenta" BOOLEAN,
    "automatica_cuenta" BOOLEAN,
    "esmanobra_cuenta" BOOLEAN,
    "tieneactivofijo_cuenta" BOOLEAN,
    "obligaproyecto_cuenta" BOOLEAN,
    "obligaempleado_cuenta" BOOLEAN,
    "obligadepar_cuenta" BOOLEAN,
    "obligaimportacion_cuenta" BOOLEAN,
    "obligasucursal_cuenta" BOOLEAN,
    "obligainventario_cuenta" BOOLEAN,
    "obligacliente_cuenta" BOOLEAN,
    "obligaproveedor_cuenta" BOOLEAN,
    "obligacaja_cuenta" BOOLEAN,
    "fechacreacion_cuenta" DATE,
    "fechamodificacion_cuenta" DATE,
    "estado_cuenta" BOOLEAN,
    PRIMARY KEY ("id_cuenta")
);

CREATE TABLE erp."cuentabancaria" (
    "id_cuentabancaria" SERIAL NOT NULL,
    "id_bancocatalogo" INTEGER,
    "id_empleado" INTEGER,
    "id_tipocuentabancaria" INTEGER,
    "numero_cuentabancaria" VARCHAR(255),
    "estado_cuentabancaria" BOOLEAN,
    PRIMARY KEY ("id_cuentabancaria")
);

CREATE TABLE erp."cupon" (
    "id_cupon" SERIAL NOT NULL,
    "anulado_cupon" BOOLEAN,
    "enviadocorreo_cupon" VARCHAR(100),
    "num_cupon" INTEGER,
    "id_factura_cupon" INTEGER,
    "sorteoactivo_cupon" BOOLEAN,
    PRIMARY KEY ("id_cupon")
);

CREATE TABLE erp."departamento" (
    "id_departamento" SERIAL NOT NULL,
    "nombre_departamento" VARCHAR(255),
    "estado_departamento" BOOLEAN,
    "id_areagrupo" INTEGER,
    PRIMARY KEY ("id_departamento")
);

CREATE TABLE erp."deposito" (
    "id_deposito" SERIAL NOT NULL,
    "id_sucursal" INTEGER,
    "id_banco" INTEGER,
    "id_usr" INTEGER,
    "id_asiento" INTEGER,
    "id_estadodeposito" INTEGER,
    "numref_deposito" VARCHAR(255),
    "fecha_deposito" DATE,
    "monto_deposito" NUMERIC(19,2),
    "obs_deposito" TEXT,
    "fecharegistro_deposito" TIMESTAMP,
    "fechaanula_deposito" TIMESTAMP,
    "motivoanula_deposito" TEXT,
    "fechaconcilia_deposito" DATE,
    "referencia_deposito" VARCHAR(255),
    PRIMARY KEY ("id_deposito")
);

CREATE TABLE erp."desbloqueo" (
    "id_desbloqueo" SERIAL NOT NULL,
    "ip_desbloqueo" VARCHAR(255),
    "descripcion_desbloqueo" VARCHAR(255),
    "estado_desbloqueo" BOOLEAN,
    PRIMARY KEY ("id_desbloqueo")
);

CREATE TABLE erp."detcobro" (
    "id_detcobro" SERIAL NOT NULL,
    "id_factura" INTEGER,
    "id_tarjeta" INTEGER,
    "id_adquiriente" INTEGER,
    "id_bancocatalogo" INTEGER,
    "id_formapagocobro" VARCHAR(255),
    "id_cobro" INTEGER,
    "valor_detcobro" NUMERIC(19,2),
    "numerodocumento_detcobro" VARCHAR(255),
    "fechatransaccion_detcobro" TIMESTAMP,
    "fechacobro_detcobro" DATE,
    "numerotc_detcobro" VARCHAR(255),
    "lotetarjeta_detcobro" VARCHAR(255),
    "mesesdiferido_detcobro" INTEGER,
    "numcuota_detcobro" INTEGER,
    "obs_detcobro" TEXT,
    "valoranticipo_detcobro" NUMERIC(19,2),
    "valortrans_detcobro" NUMERIC(19,2),
    "codnc_detcobro" NUMERIC(19,2),
    "estado_detcobro" BOOLEAN,
    "id_facturacuota" INTEGER,
    PRIMARY KEY ("id_detcobro")
);

CREATE TABLE erp."detdeposito" (
    "id_detdeposito" SERIAL NOT NULL,
    "id_motivo" TEXT,
    "id_deposito" INTEGER,
    "id_cuenta" INTEGER,
    "monto_detdeposito" DOUBLE PRECISION,
    "obs_detdeposito" TEXT,
    "anulado_detdeposito" BOOLEAN,
    "codempleado_detdeposito" INTEGER,
    "codimportacion_detdeposito" VARCHAR(255),
    "codproyecto_detdeposito" VARCHAR(255),
    "codsucursal_detdeposito" INTEGER,
    "codanticipo_detdeposito" VARCHAR(255),
    "saldoanticipo_detdeposito" DOUBLE PRECISION,
    "valcruzado_detdeposito" DOUBLE PRECISION,
    "codcliente_detdeposito" INTEGER,
    "codproveedor_detdeposito" INTEGER,
    PRIMARY KEY ("id_detdeposito")
);

CREATE TABLE erp."detfactura" (
    "id_detfactura" SERIAL NOT NULL,
    "id_producto" INTEGER,
    "id_factura" INTEGER,
    "cantidad_detfactura" NUMERIC(19,2),
    "precio_detfactura" NUMERIC(19,2),
    "porcdescuento_detfactura" NUMERIC(19,2),
    "descuento_detfactura" NUMERIC(19,2),
    "porcpromocion_detfactura" NUMERIC(19,2),
    "descpromocion_detfactura" NUMERIC(19,2),
    "preciocliente_detfactura" NUMERIC(19,2),
    "iva_detfactura" BOOLEAN,
    "total_detfactura" NUMERIC(19,2),
    "espromo_detfactura" BOOLEAN,
    "cantdevuelta_detfactura" NUMERIC(19,2),
    "cantdespachada_detfactura" NUMERIC(19,2),
    "cantxdespachar_detfactura" NUMERIC(19,2),
    "cantrecepcionada_detfactura" NUMERIC(19,2),
    "obs_detfactura" TEXT,
    "codaf_detfactura" INTEGER,
    "estado_detfactura" BOOLEAN,
    "descpromespecial_detfactura" NUMERIC(19,2),
    "porcdescpromespecial_detfactura" NUMERIC(19,2),
    PRIMARY KEY ("id_detfactura")
);

CREATE TABLE erp."discapacidad" (
    "id_discapacidad" SERIAL NOT NULL,
    "nombre_discapacidad" VARCHAR(255),
    PRIMARY KEY ("id_discapacidad")
);

CREATE TABLE erp."documento" (
    "doc_id_documento" BIGINT NOT NULL,
    "doc_id_tipocomprobante" VARCHAR(255),
    "doc_id_tipoidentificacionfiscal" VARCHAR(255),
    "doc_id_estadodocumento" INTEGER,
    "doc_id_asiento" INTEGER,
    "doc_cod_sucursal" VARCHAR(255),
    "doc_punto_emision" VARCHAR(255),
    "doc_secuencial" VARCHAR(255),
    "doc_id_formapagocobro" VARCHAR(255),
    "doc_id_condicionpago" INTEGER,
    "doc_id_tipopago" INTEGER,
    "doc_id_tipoliquidacion" INTEGER,
    "doc_id_sustentotributario" VARCHAR(255),
    "doc_ambiente" INTEGER,
    "doc_conceptodocumento" VARCHAR(255),
    "doc_tipoemision" INTEGER,
    "doc_numerodocumento" VARCHAR(255),
    "doc_claveacceso" VARCHAR(255),
    "doc_razonsocial_emisor" VARCHAR(255),
    "doc_nombrecomercial_emisor" VARCHAR(255),
    "doc_identificacion_emisor" VARCHAR(255),
    "doc_direccion_matriz" VARCHAR(255),
    "doc_fechaemision" TIMESTAMPTZ,
    "doc_fechavencimiento" TIMESTAMPTZ,
    "doc_direccion_sucursal" VARCHAR(255),
    "doc_contribuyenteespecial" VARCHAR(255),
    "doc_obligadocontabilidad" BOOLEAN,
    "doc_identificacion" VARCHAR(255),
    "doc_razonsocial" VARCHAR(255),
    "doc_correo" VARCHAR(255),
    "doc_direccion" VARCHAR(255),
    "doc_telefono" VARCHAR(255),
    "doc_placa" VARCHAR(255),
    "doc_agenteretencion" INTEGER,
    "doc_guiaremision" VARCHAR(255),
    "doc_noobjetoiva" NUMERIC(19,2),
    "doc_totalsiniva" NUMERIC(19,2),
    "doc_totalconiva" NUMERIC(19,2),
    "doc_totaldescuento" NUMERIC(19,2),
    "doc_montoiva" NUMERIC(19,2),
    "doc_valor" NUMERIC(19,2),
    "doc_valor_nc" NUMERIC(19,2),
    "doc_valor_nd" NUMERIC(19,2),
    "doc_valor_retencion" NUMERIC(19,2),
    "doc_valor_apagar" NUMERIC(19,2),
    "doc_saldo" NUMERIC(19,2),
    "doc_valorflete" NUMERIC(19,2),
    "doc_id_aperturaimportacion" INTEGER,
    "doc_id_estadoentrega" INTEGER,
    "doc_id_anticipotransporte" INTEGER,
    "doc_id_aperturacaja" BIGINT,
    "doc_id_cotizacion" BIGINT,
    "doc_valorcredito" NUMERIC(19,2),
    "doc_numerocuotas" INTEGER,
    "doc_fechaanulacion" TIMESTAMPTZ,
    "doc_motivoanula" VARCHAR(255),
    "doc_fechaautorizacion" TIMESTAMPTZ,
    "doc_estadocomprobantesri" VARCHAR(255),
    "doc_errorautorizacion" INTEGER,
    "doc_puntopartida" VARCHAR(255),
    "doc_fechainicio" TIMESTAMP,
    "doc_fechafin" TIMESTAMP,
    "doc_motivotraslado" VARCHAR(255),
    "doc_destino" VARCHAR(255),
    "doc_documentoaduanero" VARCHAR(255),
    "doc_establecimientodestino" VARCHAR(255),
    "doc_ruta" VARCHAR(255),
    "doc_plazo" INTEGER,
    "doc_iddocreembolso" INTEGER,
    "doc_sinimpuestosliq" NUMERIC(19,2),
    "doc_totaldescuentoliq" NUMERIC(19,2),
    "doc_totalcomprobantereembolso" NUMERIC(19,2),
    "doc_baseimponiblereembolso" NUMERIC(19,2),
    "doc_totalimpuestoreembolso" NUMERIC(19,2),
    "doc_campoadicionalemailcliente" VARCHAR(255),
    "doc_campoadicionaldircliente" VARCHAR(255),
    "doc_campoadicionaltelcliente" VARCHAR(255),
    "doc_campoadicionalnumerocliente" VARCHAR(255),
    "doc_id_retencion" BIGINT,
    "doc_id_documento_relacionado" BIGINT,
    "doc_id_documento_relacionado2" BIGINT,
    "doc_hospedado" BOOLEAN,
    "doc_idproveedor" INTEGER,
    "doc_idcliente" BIGINT,
    "doc_host" VARCHAR(255),
    "doc_sucursal" VARCHAR(255),
    "doc_id_sucursal" INTEGER,
    "id_asiento_reserva" INTEGER,
    "id_asiento_extra" INTEGER,
    "id_cuenta_tarjeta" INTEGER,
    "asignado_liquidacion" BOOLEAN,
    "id_tipo_nc" INTEGER,
    "valor_pagado" NUMERIC(19,2),
    "doc_porcentaje_descuento" NUMERIC(19,2),
    "valor_cruzado" NUMERIC(19,2),
    "doc_id_caja" INTEGER,
    "doc_tiene_credito" BOOLEAN,
    "doc_id_categoria_cliente" INTEGER,
    "es_caja_chica" BOOLEAN,
    "is_enviado_correo" BOOLEAN,
    "id_usuario_creacion" INTEGER,
    "id_usuario_modificacion" INTEGER,
    "fecha_creacion" TIMESTAMP,
    "fecha_modificacion" TIMESTAMP,
    PRIMARY KEY ("doc_id_documento")
);

CREATE TABLE erp."documentocasosverificacion" (
    "id_documentocasosverificacion" SERIAL NOT NULL,
    "estado_documentocasosverificacion" INTEGER,
    "idcaso_documentocasosverificacion" INTEGER,
    "iddocumento_documentocasosverificacion" INTEGER,
    PRIMARY KEY ("id_documentocasosverificacion")
);

CREATE TABLE erp."documentos_electronicos" (
    "id_documento_electronico" SERIAL NOT NULL,
    "anulado_doc" BOOLEAN,
    "claveacceso_doc" VARCHAR(255),
    "envio_correo_doc" VARCHAR(100),
    "fecha_autorizacion_doc" TIMESTAMP,
    "fecha_emision_doc" TIMESTAMP,
    "id_documento_doc" INTEGER,
    "identificacion_doc" VARCHAR(50),
    "nombre_doc" VARCHAR(255),
    "nrointentos_dia_doc" INTEGER,
    "nrointentos_doc" INTEGER,
    "num_doc" VARCHAR(255),
    "tipo_doc" VARCHAR(255),
    "xml_autorizada_doc" VARCHAR(255),
    "xml_firmado_doc" VARCHAR(255),
    "xml_generado_doc" VARCHAR(255),
    "id_estadofactura" INTEGER,
    "id_estadosri" INTEGER,
    PRIMARY KEY ("id_documento_electronico")
);

CREATE TABLE erp."documentoverificacion" (
    "id_documentoverificacion" SERIAL NOT NULL,
    "numero_documentoverificacion" INTEGER,
    "tipo_documentoverificacion" VARCHAR(255),
    PRIMARY KEY ("id_documentoverificacion")
);

CREATE TABLE erp."empleado" (
    "id_empleado" SERIAL NOT NULL,
    "id_posesiondomicilio" INTEGER,
    "id_codigosectorial" VARCHAR(50),
    "id_tiposangre" INTEGER,
    "id_tipolicenciaconducir" INTEGER,
    "id_cargo" INTEGER,
    "id_sucursal" INTEGER,
    "id_persona" INTEGER,
    "id_discapacidad" INTEGER,
    "numerohijos_empleado" INTEGER,
    "celular_empleado" VARCHAR(20),
    "telefono_empleado" VARCHAR(20),
    "correo_empleado" VARCHAR(100),
    "fechacaducalicencia_empleado" DATE,
    "fechaingresoempresa_empleado" DATE,
    "fechareingreso_empleado" DATE,
    "salario_empleado" NUMERIC(19,2),
    "horaordinaria_empleado" NUMERIC(19,2),
    "esdiscapacitado_empleado" BOOLEAN,
    "porcdiscapacidad_empleado" NUMERIC(19,2),
    "decimo3acumulado_empleado" BOOLEAN,
    "decimo4acumulado_empleado" BOOLEAN,
    "fracumulados_empleado" BOOLEAN,
    "provisionvacaciones_empleado" BOOLEAN,
    "quincena_empleado" BOOLEAN,
    "valorquincena_empleado" NUMERIC(19,2),
    "correoempresa_empleado" VARCHAR(100),
    "usuarioskype_empleado" VARCHAR(255),
    "telefonoempresa_empleado" VARCHAR(20),
    "extension_empleado" VARCHAR(255),
    "celularempresa_empleado" VARCHAR(20),
    "usuariosistema_empleado" VARCHAR(255),
    "fechasalida_empleado" DATE,
    "motivosalida_empleado" TEXT,
    "estado_empleado" BOOLEAN,
    "titulo_empleado" VARCHAR(255),
    "id_nivelestudio" INTEGER,
    "iesspatronal_empleado" BOOLEAN,
    "iesspersonal_empleado" BOOLEAN,
    "motivoreingreso_empleado" TEXT,
    "impuestorentaanual_empleado" NUMERIC(19,2),
    "impuestorenta_empleado" NUMERIC(19,2),
    "impuestorentarebaja_empleado" NUMERIC(19,2),
    "pathcedula_empleado" VARCHAR(255),
    "pathlicencia_empleado" VARCHAR(255),
    "pathcv_empleado" VARCHAR(255),
    PRIMARY KEY ("id_empleado")
);

CREATE TABLE erp."empleadocuenta" (
    "id_empleadocuenta" SERIAL NOT NULL,
    "id_empleado" INTEGER,
    "id_cuenta" INTEGER,
    PRIMARY KEY ("id_empleadocuenta")
);

CREATE TABLE erp."empresa" (
    "emp_id" BIGINT NOT NULL,
    "emp_codigo" VARCHAR(50),
    "emp_razonsocial" VARCHAR(255),
    "emp_ciudad" VARCHAR(255),
    "emp_direccion" VARCHAR(500),
    "emp_rucempresa" VARCHAR(255),
    "emp_telefono" VARCHAR(20),
    "emp_correoelectronico" VARCHAR(100),
    "emp_representante" VARCHAR(255),
    "emp_rucrepresentante" VARCHAR(255),
    "emp_numdecimalespvp" INTEGER,
    "emp_obligadocontabilidad" BOOLEAN,
    "emp_contribuyenteespecial" VARCHAR(255),
    "emp_piefirmafe" VARCHAR(255),
    "emp_agenteretiene" BOOLEAN,
    "emp_resolucionagente" VARCHAR(255),
    "emp_host" VARCHAR(255),
    PRIMARY KEY ("emp_id")
);

CREATE TABLE erp."errores" (
    "id_errores" SERIAL NOT NULL,
    "descripcionerror_errores" VARCHAR(255),
    "descripcion_errores" VARCHAR(255),
    "error_errores" VARCHAR(255),
    "iddocumento_errores" INTEGER,
    "modulo_errores" VARCHAR(255),
    "revisado_errores" BOOLEAN,
    "tipodocumento_errores" VARCHAR(255),
    PRIMARY KEY ("id_errores")
);

CREATE TABLE erp."estado" (
    "id_estado" SERIAL NOT NULL,
    "nombre_estado" VARCHAR(255),
    PRIMARY KEY ("id_estado")
);

CREATE TABLE erp."estadoasiento" (
    "id_estadoasiento" SERIAL NOT NULL,
    "nombre_estadoasiento" VARCHAR(255),
    PRIMARY KEY ("id_estadoasiento")
);

CREATE TABLE erp."estadocivil" (
    "id_estadocivil" SERIAL NOT NULL,
    "nombre_estadocivil" VARCHAR(255),
    "cod_estadocivil" VARCHAR(255),
    PRIMARY KEY ("id_estadocivil")
);

CREATE TABLE erp."estadodeposito" (
    "id_estadodeposito" SERIAL NOT NULL,
    "nombre_estadodeposito" VARCHAR(255),
    PRIMARY KEY ("id_estadodeposito")
);

CREATE TABLE erp."estadoentrega" (
    "id_estadoentrega" SERIAL NOT NULL,
    "nombre_estadoentrega" VARCHAR(255),
    PRIMARY KEY ("id_estadoentrega")
);

CREATE TABLE erp."estadofactura" (
    "id_estadofactura" SERIAL NOT NULL,
    "nombre_estadofactura" VARCHAR(255),
    "cod_estadofactura" VARCHAR(255),
    PRIMARY KEY ("id_estadofactura")
);

CREATE TABLE erp."estadopago" (
    "id_estadopago" SERIAL NOT NULL,
    "nombre_estadopago" VARCHAR(255),
    PRIMARY KEY ("id_estadopago")
);

CREATE TABLE erp."estadosri" (
    "id_estadosri" SERIAL NOT NULL,
    "nombre_estadosri" VARCHAR(255),
    PRIMARY KEY ("id_estadosri")
);

CREATE TABLE erp."excepciones_cliente_descuento" (
    "id_excepcion" SERIAL NOT NULL,
    "id_cliente" BIGINT,
    "id_producto" INTEGER,
    "id_subgrupo" INTEGER,
    "id_grupo" INTEGER,
    "porcion_descuento" NUMERIC(19,2),
    "fecha_inicio" TIMESTAMPTZ,
    "fecha_fin" TIMESTAMPTZ,
    "estado" BOOLEAN,
    "id_usuario_creacion" INTEGER,
    "id_usuario_modificacion" INTEGER,
    "fecha_creacion" TIMESTAMP,
    "fecha_modificacion" TIMESTAMP,
    PRIMARY KEY ("id_excepcion")
);

CREATE TABLE erp."factura" (
    "id_factura" SERIAL NOT NULL,
    "id_anticipotransporte" INTEGER,
    "id_estadoentrega" INTEGER,
    "id_caja" INTEGER,
    "id_aperturacaja" INTEGER,
    "id_cliente" INTEGER,
    "id_sucursal" INTEGER,
    "id_asiento" INTEGER,
    "id_tipocomprobante" INTEGER,
    "id_usr" INTEGER,
    "id_sustentotributario" VARCHAR(255),
    "id_estadofactura" INTEGER,
    "numero_factura" VARCHAR(255),
    "numeroglobal_factura" VARCHAR(255),
    "subtotal_factura" NUMERIC(19,2),
    "subtotalnoobjetoiva_factura" NUMERIC(19,2),
    "subtotalexentoiva_factura" NUMERIC(19,2),
    "porcdescuento_factura" NUMERIC(19,2),
    "descuento_factura" NUMERIC(19,2),
    "porcdesccliente_factura" NUMERIC(19,2),
    "desccliente_factura" NUMERIC(19,2),
    "porcdescespecial_factura" NUMERIC(19,2),
    "descespecial_factura" NUMERIC(19,2),
    "porcpromocion_factura" NUMERIC(19,2),
    "descpromocion_factura" NUMERIC(19,2),
    "porcdescconta_factura" NUMERIC(19,2),
    "descconta_factura" NUMERIC(19,2),
    "ventaneta_factura" NUMERIC(19,2),
    "tarifacero_factura" NUMERIC(19,2),
    "ventatarifaiva_factura" NUMERIC(19,2),
    "valorret_factura" NUMERIC(19,2),
    "iva_factura" NUMERIC(19,2),
    "total_factura" NUMERIC(19,2),
    "valpagar_factura" NUMERIC(19,2),
    "fechaemision_factura" TIMESTAMP,
    "numeroautorizacion_factura" VARCHAR(255),
    "claveacceso_factura" VARCHAR(255),
    "fechaautorizacion_factura" VARCHAR(255),
    "saldo_factura" NUMERIC(19,2),
    "diascredito_factura" INTEGER,
    "fechavencimiento_factura" DATE,
    "obs_factura" TEXT,
    "xrecepcionar" BOOLEAN,
    "cod_cotizacion" VARCHAR(255),
    "cod_retencion" VARCHAR(255),
    "xml_factura" VARCHAR(255),
    "enviadacorreo_factura" VARCHAR(100),
    "esservicio_factura" BOOLEAN,
    "anulada_factura" BOOLEAN,
    "valornd_factura" NUMERIC(19,2),
    "id_tipodoc" INTEGER,
    "escredito_factura" BOOLEAN,
    "valorcredito_factura" NUMERIC(19,2),
    "acuotas_factura" BOOLEAN,
    "descpromespecial_factura" NUMERIC(19,2),
    PRIMARY KEY ("id_factura")
);

CREATE TABLE erp."facturaproveedor" (
    "id_facturaproveedor" SERIAL NOT NULL,
    "id_asiento" INTEGER,
    "id_formapagocobro" VARCHAR(255),
    "id_proveedor" INTEGER,
    "id_usr" INTEGER,
    "id_condicionpago" INTEGER,
    "id_tipopago" INTEGER,
    "id_estadofacprov" INTEGER,
    "id_sucursal" INTEGER,
    "id_tipoliquidacion" INTEGER,
    "id_tipocomprobante" INTEGER,
    "id_sustentotributario" VARCHAR(255),
    "numero_facturaproveedor" VARCHAR(255),
    "claveacceso_facturaproveedor" VARCHAR(255),
    "numeroautorizacion_facproveedor" VARCHAR(255),
    "fechaemision_facturaproveedor" TIMESTAMP,
    "fechavencimiento_facturaproveedor" TIMESTAMP,
    "descripcion_facturaproveedor" VARCHAR(255),
    "tarifaiva0_facproveedor" NUMERIC(19,2),
    "tarifaiva0serv_facproveedor" NUMERIC(19,2),
    "tarifanoiva_facproveedor" NUMERIC(19,2),
    "tarifanoivaserv_facproveedor" NUMERIC(19,2),
    "subtotal_facturaproveedor" NUMERIC(19,2),
    "iva_facturaproveedor" NUMERIC(19,2),
    "subtotal2_facturaproveedor" NUMERIC(19,2),
    "iva2_facturaproveedor" NUMERIC(19,2),
    "total_facturaproveedor" NUMERIC(19,2),
    "apagar_facturaproveedor" NUMERIC(19,2),
    "retencion_facturaproveedor" NUMERIC(19,2),
    "documentos_relacionados" VARCHAR(255),
    "motivoanulacion_facturaproveedor" VARCHAR(255),
    "codimportacion_facproveedor" VARCHAR(255),
    "diaspago_facproveedor" INTEGER,
    "desc_facproveedor" NUMERIC(19,2),
    "espagocajachica_facproveedor" BOOLEAN,
    "escrucecontable_facproveedor" BOOLEAN,
    "tarifaiva_facproveedor" NUMERIC(19,2),
    "tarifaivaserv_facproveedor" NUMERIC(19,2),
    "xmlliquida_facproveedor" VARCHAR(255),
    "fecharegistro_facproveedor" TIMESTAMP,
    "ivaservct_facproveedor" NUMERIC(19,2),
    "id_tipodoc" INTEGER,
    "cod332_facproveedor" NUMERIC(19,2),
    "saldo_facturaproveedor" NUMERIC(19,2),
    "codasientoimpo_facproveedor" INTEGER,
    "coddoc_facproveedor" INTEGER,
    "autorizado_facproveedor" BOOLEAN,
    PRIMARY KEY ("id_facturaproveedor")
);

CREATE TABLE erp."formapagocobro" (
    "id_formapagocobro" VARCHAR(255) NOT NULL,
    "nombre_formapagocobro" VARCHAR(255),
    "alias_formapagocobro" VARCHAR(255),
    "descuento_formapagocobro" NUMERIC(19,2),
    "cod_formapagocobro" VARCHAR(255),
    "estado_formapagocobro" BOOLEAN,
    PRIMARY KEY ("id_formapagocobro")
);

CREATE TABLE erp."genero" (
    "id_genero" SERIAL NOT NULL,
    "nombre_genero" VARCHAR(255),
    "cod_genero" VARCHAR(255),
    PRIMARY KEY ("id_genero")
);

CREATE TABLE erp."grupo" (
    "id_grupo" SERIAL NOT NULL,
    "nombre_grupo" VARCHAR(255),
    "nemonico_grupo" VARCHAR(255),
    "ctaing_grupo" INTEGER,
    "ctainv_grupo" INTEGER,
    "ctacos_grupo" INTEGER,
    PRIMARY KEY ("id_grupo")
);

CREATE TABLE erp."identificacionfiscal" (
    "id_identificacionfiscal" VARCHAR(50) NOT NULL,
    "cod_identificacionfiscal" VARCHAR(50),
    "nombre_identificacionfiscal" VARCHAR(50),
    "codats_identificacionfiscal" VARCHAR(50),
    PRIMARY KEY ("id_identificacionfiscal")
);

CREATE TABLE erp."imgusr" (
    "id_imgusr" SERIAL NOT NULL,
    "id_usr" INTEGER,
    PRIMARY KEY ("id_imgusr")
);

CREATE TABLE erp."impuesto_documento" (
    "id_impuesto" SERIAL NOT NULL,
    "id_documento" BIGINT,
    "id_tipo_documento" INTEGER,
    "id_voucher" INTEGER,
    "porcentaje_impuesto" NUMERIC(19,2),
    "descripcion_impuesto" VARCHAR(255),
    "codigo_impuesto" VARCHAR(255),
    "id_cuenta" INTEGER,
    "base_impuesto" NUMERIC(19,2),
    "monto_impuesto" NUMERIC(19,2),
    "estado_impuesto" BOOLEAN,
    "id_usuario_creacion" INTEGER,
    "id_usuario_modificacion" INTEGER,
    "fecha_creacion" TIMESTAMP,
    "fecha_modificacion" TIMESTAMP,
    PRIMARY KEY ("id_impuesto")
);

CREATE TABLE erp."indicefinanciero" (
    "id_indicefinanciero" SERIAL NOT NULL,
    "nombre_indicefinanciero" VARCHAR(255),
    PRIMARY KEY ("id_indicefinanciero")
);

CREATE TABLE erp."listapreciosprov" (
    "id_listaprecios" SERIAL NOT NULL,
    "id_proveedor" INTEGER,
    "id_producto" INTEGER,
    "rango_inicial" DOUBLE PRECISION,
    "rango_final" DOUBLE PRECISION,
    "actualizado" BOOLEAN,
    PRIMARY KEY ("id_listaprecios")
);

CREATE TABLE erp."matricula" (
    "id_matricula" SERIAL NOT NULL,
    "id_vehiculo" INTEGER,
    "fechaemision_matricula" DATE,
    "fechacaduca_matricula" DATE,
    PRIMARY KEY ("id_matricula")
);

CREATE TABLE erp."menu" (
    "id_menu" SERIAL NOT NULL,
    "icon_menu" VARCHAR(255),
    "nivel_menu" INTEGER,
    "nombre_menu" VARCHAR(255),
    "url_menu" VARCHAR(255),
    "padre_menu" INTEGER,
    "component_menu" VARCHAR(255),
    "orden_menu" INTEGER,
    "estado_menu" BOOLEAN,
    PRIMARY KEY ("id_menu")
);

CREATE TABLE erp."menu_usuario" (
    "id_menu_usuario" SERIAL NOT NULL,
    "estado_menu_usuario" BOOLEAN,
    "id_menu" INTEGER,
    "id_usuario" INTEGER,
    PRIMARY KEY ("id_menu_usuario")
);

CREATE TABLE erp."modificacioncreditocliente" (
    "id_modificacioncreditocliente" SERIAL NOT NULL,
    "id_creditocliente" INTEGER,
    "id_usr" INTEGER,
    "campomodificado" VARCHAR(255),
    "valoranterior_modificacioncredcliente" NUMERIC(19,2),
    "nuevovalor_modificacioncreditocliente" NUMERIC(19,2),
    PRIMARY KEY ("id_modificacioncreditocliente")
);

CREATE TABLE erp."modulo" (
    "id_modulo" SERIAL NOT NULL,
    "nombre_modulo" VARCHAR(255),
    "id_usuario_creacion" INTEGER,
    "id_usuario_modificacion" INTEGER,
    "fecha_creacion" TIMESTAMP,
    "fecha_modificacion" TIMESTAMP,
    PRIMARY KEY ("id_modulo")
);

CREATE TABLE erp."motivodeposito" (
    "id_motivo" TEXT NOT NULL,
    "id_sucursal" INTEGER,
    "nombre_motivo" VARCHAR(255),
    "valor_motivo" TEXT,
    "valorpendiente_motivo" TEXT,
    "asignado_motivo" TEXT,
    "fecha_motivo" TEXT,
    "codapertura_motivo" TEXT,
    "codcaja_motivo" TEXT,
    "coddetcobro_motivo" TEXT,
    "estado_motivo" TEXT,
    "codcobro_motivo" TEXT,
    "codcliente_motivo" TEXT,
    PRIMARY KEY ("id_motivo")
);

CREATE TABLE erp."movimientobanco" (
    "id_movimientobanco" SERIAL NOT NULL,
    "id_tipomovimientobanco" INTEGER,
    "id_banco" INTEGER,
    "fecha_movimientobanco" TIMESTAMP,
    "numerodoc_movimientobanco" VARCHAR(255),
    "valor_movimientobanco" NUMERIC(19,2),
    "referencia_movimientobanco" VARCHAR(255),
    "saldoactual_movimientobanco" NUMERIC(19,2),
    PRIMARY KEY ("id_movimientobanco")
);

CREATE TABLE erp."multidimension" (
    "id_multidimension" SERIAL NOT NULL,
    "tipo_multidimension" VARCHAR(255),
    "valor_multidimension" INTEGER,
    PRIMARY KEY ("id_multidimension")
);

CREATE TABLE erp."nivelestudio" (
    "id_nivelestudio" SERIAL NOT NULL,
    "nombre_nivelestudio" VARCHAR(255),
    PRIMARY KEY ("id_nivelestudio")
);

CREATE TABLE erp."origeningreso" (
    "id_origeningreso" SERIAL NOT NULL,
    "nombre_origeningreso" VARCHAR(255),
    "cod_origeningreso" VARCHAR(255),
    PRIMARY KEY ("id_origeningreso")
);

CREATE TABLE erp."pagina" (
    "id_pagina" SERIAL NOT NULL,
    "url_pagina" VARCHAR(255),
    "alias_pagina" VARCHAR(255),
    "seccion_pagina" VARCHAR(255),
    PRIMARY KEY ("id_pagina")
);

CREATE TABLE erp."paginarol" (
    "id_paginarol" SERIAL NOT NULL,
    "id_rol" INTEGER,
    "id_pagina" INTEGER,
    "estado_paginarol" BOOLEAN,
    PRIMARY KEY ("id_paginarol")
);

CREATE TABLE erp."pago" (
    "id_pago" SERIAL NOT NULL,
    "id_asiento" INTEGER,
    "id_formapagocobro" VARCHAR(255),
    "id_banco" INTEGER,
    "id_estadopago" INTEGER,
    "id_usr" INTEGER,
    "beneficiario_pago" VARCHAR(255),
    "idenbeneficiario_pago" VARCHAR(255),
    "numerodocumento_pago" VARCHAR(255),
    "monto_pago" NUMERIC(19,2),
    "valcruzado_pago" NUMERIC(19,2),
    "numcuenta_pago" VARCHAR(255),
    "tipocuenta_pago" VARCHAR(255),
    "concepto_pago" VARCHAR(255),
    "banco_pago" VARCHAR(255),
    "fecharegistro_pago" TIMESTAMP,
    "fechacontabilidad_pago" DATE,
    "fechaconfirmacion_pago" DATE,
    "cheque_pago" BOOLEAN,
    "codcuenta_pago" VARCHAR(255),
    "codcalendario_pago" VARCHAR(255),
    "escajachica_pago" BOOLEAN,
    "esmanual_pago" BOOLEAN,
    "tipo_pago" BOOLEAN,
    "recuperarcheque_pago" BOOLEAN,
    "anulado_pago" BOOLEAN,
    "id_sucursal" INTEGER,
    "fechavalidacion_pago" DATE,
    "fechaconcilia_pago" DATE,
    PRIMARY KEY ("id_pago")
);

CREATE TABLE erp."pagofacturaproveedor" (
    "id_pagofacproveedor" SERIAL NOT NULL,
    "id_pago" INTEGER,
    "id_cuenta" INTEGER,
    "id_usr" INTEGER,
    "id_facturaproveedor" INTEGER,
    "numcuota_pagofacproveedor" INTEGER,
    PRIMARY KEY ("id_pagofacproveedor")
);

CREATE TABLE erp."pagorol" (
    "id_pagoroles" SERIAL NOT NULL,
    "id_pago" INTEGER,
    "id_rolpago" INTEGER,
    PRIMARY KEY ("id_pagoroles")
);

CREATE TABLE erp."pais" (
    "id_pais" SERIAL NOT NULL,
    "codigopais" VARCHAR(50),
    "nombre_pais" VARCHAR(255),
    PRIMARY KEY ("id_pais")
);

CREATE TABLE erp."parametro" (
    "id_parametro" SERIAL NOT NULL,
    "nombre_parametro" VARCHAR(255),
    "valor_parametro" VARCHAR(255),
    PRIMARY KEY ("id_parametro")
);

CREATE TABLE erp."parentesco" (
    "id_parentesco" SERIAL NOT NULL,
    "nombre_parentesco" VARCHAR(255),
    "cargafamiliar_parentesco" BOOLEAN,
    PRIMARY KEY ("id_parentesco")
);

CREATE TABLE erp."parroquia" (
    "id_parroquia" SERIAL NOT NULL,
    "id_ciudad" INTEGER,
    "nombre_parroquia" VARCHAR(255),
    "cod_parroquia" VARCHAR(255),
    PRIMARY KEY ("id_parroquia")
);

CREATE TABLE erp."periodo" (
    "id_periodo" SERIAL NOT NULL,
    "fechacierre_periodo" DATE,
    "fechaanterior_periodo" DATE,
    "id_modulo" INTEGER,
    "id_usuario_creacion" INTEGER,
    "id_usuario_modificacion" INTEGER,
    "fecha_creacion" TIMESTAMP,
    "fecha_modificacion" TIMESTAMP,
    PRIMARY KEY ("id_periodo")
);

CREATE TABLE erp."persona" (
    "id_persona" SERIAL NOT NULL,
    "id_tipocontribuyente" VARCHAR(255) NOT NULL,
    "id_pais" INTEGER NOT NULL,
    "id_parroquia" INTEGER,
    "id_estadocivil" INTEGER NOT NULL,
    "id_genero" INTEGER NOT NULL,
    "id_identificacionfiscal" VARCHAR(50) NOT NULL,
    "identificacion_persona" VARCHAR(50) NOT NULL,
    "nombre_persona" VARCHAR(255) NOT NULL,
    "fechanacimiento_persona" DATE,
    "direccion_persona" VARCHAR(500),
    "celular_persona" VARCHAR(20),
    "telefono_persona" VARCHAR(20),
    "correo_persona" VARCHAR(100),
    "escliente_persona" BOOLEAN NOT NULL DEFAULT FALSE,
    "esempleado_persona" BOOLEAN NOT NULL DEFAULT FALSE,
    "esproveedor_persona" BOOLEAN NOT NULL DEFAULT FALSE,
    "fecharegistro_persona" TIMESTAMP,
    "barriodomicilio_persona" VARCHAR(255),
    "cantondomicilio_persona" VARCHAR(255),
    "parroquiadomicilio_persona" VARCHAR(255),
    "sectordomicilio_persona" VARCHAR(255),
    "id_usuario_creacion" INTEGER,
    "id_usuario_modificacion" INTEGER,
    "fecha_creacion" TIMESTAMP,
    "fecha_modificacion" TIMESTAMP,
    PRIMARY KEY ("id_persona"),
    FOREIGN KEY ("id_tipocontribuyente") REFERENCES erp."tipocontribuyente"("id_tipocontribuyente"),
    FOREIGN KEY ("id_pais") REFERENCES erp."pais"("id_pais"),
    FOREIGN KEY ("id_parroquia") REFERENCES erp."parroquia"("id_parroquia"),
    FOREIGN KEY ("id_estadocivil") REFERENCES erp."estadocivil"("id_estadocivil"),
    FOREIGN KEY ("id_genero") REFERENCES erp."genero"("id_genero"),
    FOREIGN KEY ("id_identificacionfiscal") REFERENCES erp."identificacionfiscal"("id_identificacionfiscal")
);

CREATE TABLE erp."posesiondomicilio" (
    "id_posesiondomicilio" SERIAL NOT NULL,
    "nombre_posesiondomicilio" VARCHAR(255),
    PRIMARY KEY ("id_posesiondomicilio")
);

CREATE TABLE erp."producto" (
    "id_producto" SERIAL NOT NULL,
    "id_rotacionproducto" INTEGER,
    "id_subgrupo" INTEGER,
    "id_tarifaiva" INTEGER,
    "id_unidadmedida" INTEGER,
    "cod_producto" VARCHAR(255),
    "nombrecorto_producto" VARCHAR(255),
    "nombrelargo_producto" VARCHAR(255),
    "costounitario_producto" NUMERIC(19,2),
    "pvpunitario_producto" NUMERIC(19,2),
    "marcaiva_producto" BOOLEAN,
    "caracteristica_producto" VARCHAR(255),
    "pesokg_producto" NUMERIC(19,2),
    "largo_producto" NUMERIC(19,2),
    "ancho_producto" NUMERIC(19,2),
    "espesor_producto" NUMERIC(19,2),
    "marca_producto" VARCHAR(255),
    "preciofactura_producto" NUMERIC(19,2),
    "esmateriaprima_producto" BOOLEAN,
    "esservicio_producto" BOOLEAN,
    "ficha_producto" VARCHAR(255),
    "fecharegistro_producto" TIMESTAMP,
    "fechamodificacion_producto" TIMESTAMP,
    "estado_producto" BOOLEAN,
    PRIMARY KEY ("id_producto")
);

CREATE TABLE erp."proveedor" (
    "id_proveedor" SERIAL NOT NULL,
    "id_tipoidfiscal" VARCHAR(255),
    "id_retencioniva" INTEGER,
    "id_persona" INTEGER,
    "id_bancocatalogo" INTEGER,
    "id_retencionfuente" INTEGER,
    "id_tipoprov" INTEGER,
    "id_tipocuentabancaria" INTEGER,
    "id_condicionpago" INTEGER,
    "id_formapagocobro" VARCHAR(255),
    "nombrecomercial_proveedor" VARCHAR(255),
    "direccion2_proveedor" VARCHAR(255),
    "correo2_proveedor" VARCHAR(255),
    "celular2_proveedor" VARCHAR(255),
    "telefono2_proveedor" VARCHAR(255),
    "sitioweb_proveedor" VARCHAR(255),
    "contacto_proveedor" VARCHAR(255),
    "cargocontacto_proveedor" VARCHAR(255),
    "telefonocontacto_proveedor" VARCHAR(255),
    "correocontacto_proveedor" VARCHAR(255),
    "celularcontacto_proveedor" VARCHAR(255),
    "credito_proveedor" BOOLEAN,
    "montocredito_proveedor" NUMERIC(19,2),
    "fechavencimientocredito_proveedor" DATE,
    "diascredito_proveedor" INTEGER,
    "montogarantia_proveedor" NUMERIC(19,2),
    "numcuentabancaria_proveedor" VARCHAR(255),
    "referenciapago_proveedor" VARCHAR(255),
    "saldoanticipo_proveedor" NUMERIC(19,2),
    "nombrecheque_proveedor" VARCHAR(255),
    "contribespecial_proveedor" VARCHAR(255),
    "estransporte_proveedor" BOOLEAN,
    "estado_proveedor" BOOLEAN,
    "numero_cuenta_pago" VARCHAR(255),
    "id_identificacionfiscal_pago" INTEGER,
    "cuentapasiva_proveedor" INTEGER,
    "cuentagasto_proveedor" INTEGER,
    "id_usuario_creacion" INTEGER,
    "id_usuario_modificacion" INTEGER,
    "fecha_creacion" TIMESTAMP,
    "fecha_modificacion" TIMESTAMP,
    PRIMARY KEY ("id_proveedor")
);

CREATE TABLE erp."provincia" (
    "id_provincia" VARCHAR(255) NOT NULL,
    "id_pais" INTEGER,
    "nombre_provincia" VARCHAR(255),
    "cod_region" INTEGER,
    PRIMARY KEY ("id_provincia")
);

CREATE TABLE erp."proyecto" (
    "id_proyecto" SERIAL NOT NULL,
    "nombre_proyecto" VARCHAR(255),
    "descripcion_proyecto" VARCHAR(255),
    "fechainicio_proyecto" DATE,
    "fechafin_proyecto" DATE,
    "estado_proyecto" BOOLEAN,
    PRIMARY KEY ("id_proyecto")
);

CREATE TABLE erp."region" (
    "id_region" SERIAL NOT NULL,
    "nombre_region" VARCHAR(255),
    PRIMARY KEY ("id_region")
);

CREATE TABLE erp."respuestas_documento" (
    "id_respuestas_documento" SERIAL NOT NULL,
    "codigo_errror_resp" VARCHAR(50),
    "codigo_respuesta_resp" VARCHAR(50),
    "fecha_resp" TIMESTAMP,
    "xml_resp" VARCHAR(255),
    "id_documento_electronico" INTEGER,
    PRIMARY KEY ("id_respuestas_documento")
);

CREATE TABLE erp."retencion" (
    "id" INTEGER NOT NULL,
    "id_tipo_retencion" INTEGER,
    "id_cuenta" INTEGER,
    "detalle" VARCHAR(255),
    "valor_porcentaje" NUMERIC(19,2),
    "porcentaje_descripcion" VARCHAR(255),
    "campo_extra" VARCHAR(255),
    "codigo_anexo" VARCHAR(255),
    "es_proveedor" BOOLEAN,
    "es_cliente" BOOLEAN,
    "estado_retencion" BOOLEAN,
    PRIMARY KEY ("id")
);

CREATE TABLE erp."rol" (
    "id_rol" SERIAL NOT NULL,
    "nombre_rol" VARCHAR(255),
    "descripcion_rol" VARCHAR(255),
    "horainicio_rol" VARCHAR(255),
    "horafin_rol" VARCHAR(255),
    "tiempo_rol" INTEGER,
    "id_usuario_creacion" INTEGER,
    "id_usuario_modificacion" INTEGER,
    "fecha_creacion" TIMESTAMP,
    "fecha_modificacion" TIMESTAMP,
    PRIMARY KEY ("id_rol")
);

CREATE TABLE erp."rolusuario" (
    "id_rolusuario" SERIAL NOT NULL,
    "id_usr" INTEGER,
    "id_rol" INTEGER,
    "estado_rolusuario" BOOLEAN,
    "id_usuario_creacion" INTEGER,
    "id_usuario_modificacion" INTEGER,
    "fecha_creacion" TIMESTAMP,
    "fecha_modificacion" TIMESTAMP,
    PRIMARY KEY ("id_rolusuario")
);

CREATE TABLE erp."rotacionproducto" (
    "id_rotacionproducto" SERIAL NOT NULL,
    "nombre_rotacionproducto" VARCHAR(255),
    PRIMARY KEY ("id_rotacionproducto")
);

CREATE TABLE erp."seg" (
    "id_seg" SERIAL NOT NULL,
    "tiemposesion_seg" VARCHAR(255),
    "horadesde_seg" VARCHAR(255),
    "horahasta_seg" VARCHAR(255),
    PRIMARY KEY ("id_seg")
);

CREATE TABLE erp."sesion" (
    "id_sesion" SERIAL NOT NULL,
    "id_usr" INTEGER NOT NULL,
    "token" VARCHAR(255) NOT NULL,
    "fecha_creacion" TIMESTAMPTZ NOT NULL,
    "fecha_expiracion" TIMESTAMPTZ NOT NULL,
    "estado" BOOLEAN NOT NULL DEFAULT TRUE,
    "duracion_minutos" INTEGER NOT NULL DEFAULT 0,
    PRIMARY KEY ("id_sesion", "id_usr")
);

CREATE TABLE erp."solicitud" (
    "id_solicitud" SERIAL NOT NULL,
    "id_usr" INTEGER,
    "id_estado" INTEGER,
    "asunto_solicitud" VARCHAR(255),
    "usuariorecibe_solicitud" INTEGER,
    "descripcion_solicitud" VARCHAR(255),
    "fecha_solicitud" TIMESTAMP,
    "fecharespuesta_solicitud" TIMESTAMP,
    PRIMARY KEY ("id_solicitud")
);

CREATE TABLE erp."sorteo" (
    "id_sorteo" SERIAL NOT NULL,
    "anulado_sorteo" BOOLEAN,
    "descripcion_sorteo" VARCHAR(255),
    "fechafin_sorteo" TIMESTAMP,
    "fechainicio_sorteo" TIMESTAMP,
    "montoparticipante_sorteo" NUMERIC(19,2),
    "nombre_sorteo" VARCHAR(255),
    "id_cupon_sorteo" INTEGER,
    PRIMARY KEY ("id_sorteo")
);

CREATE TABLE erp."subgrupo" (
    "id_subgrupo" SERIAL NOT NULL,
    "id_grupo" INTEGER,
    "nombre_subgrupo" VARCHAR(255),
    "nemonico_subgrupo" VARCHAR(255),
    PRIMARY KEY ("id_subgrupo")
);

CREATE TABLE erp."sucursal" (
    "id_sucursal" SERIAL NOT NULL,
    "id_ciudad" INTEGER,
    "id_categoriasucursal" VARCHAR(255),
    "id_region" INTEGER,
    "nombre_sucursal" VARCHAR(255),
    "nemonico_sucursal" VARCHAR(255),
    "direccion_sucursal" VARCHAR(500),
    "telefono_sucursal" VARCHAR(20),
    "telefono2_sucursal" VARCHAR(20),
    "celular_sucursal" VARCHAR(20),
    "correo_sucursal" VARCHAR(100),
    "correo2_sucursal" VARCHAR(100),
    "responsable_sucursal" VARCHAR(255),
    "esoutlet_sucursal" BOOLEAN,
    "estado_sucursal" BOOLEAN,
    "impresiontermica_sucursal" BOOLEAN,
    "codigo_sucursal" VARCHAR(255),
    PRIMARY KEY ("id_sucursal")
);

CREATE TABLE erp."sustentotributario" (
    "id_sustentotributario" VARCHAR(255) NOT NULL,
    "nombre_sustentotributario" VARCHAR(255),
    PRIMARY KEY ("id_sustentotributario")
);

CREATE TABLE erp."tarifaiva" (
    "id_tarifaiva" SERIAL NOT NULL,
    "porc_tarifaiva" NUMERIC(19,2),
    "cod_tarifaiva" VARCHAR(255),
    "descripcion_iva" VARCHAR(255),
    "id_cuenta_venta" INTEGER,
    "id_cuenta_compra" INTEGER,
    "estado_tarifaiva" BOOLEAN,
    "id_usuario_creacion" INTEGER,
    "id_usuario_modificacion" INTEGER,
    "fecha_creacion" TIMESTAMP,
    "fecha_modificacion" TIMESTAMP,
    PRIMARY KEY ("id_tarifaiva")
);

CREATE TABLE erp."tarjeta" (
    "id_tarjeta" SERIAL NOT NULL,
    "id_cuenta" INTEGER,
    "nombre_tarjeta" VARCHAR(255),
    "escredito_tarjeta" BOOLEAN,
    "estado_tarjeta" BOOLEAN,
    "id_usuario_creacion" INTEGER,
    "id_usuario_modificacion" INTEGER,
    "fecha_creacion" TIMESTAMP,
    "fecha_modificacion" TIMESTAMP,
    PRIMARY KEY ("id_tarjeta")
);

CREATE TABLE erp."tipocomprobante" (
    "id_tipocomprobante" SERIAL NOT NULL,
    "nombre_tipocomprobante" VARCHAR(255),
    "cod_tipocomprobante" VARCHAR(255),
    PRIMARY KEY ("id_tipocomprobante")
);

CREATE TABLE erp."tipocontrato" (
    "id_tipocontrato" SERIAL NOT NULL,
    "nombre_tipocontrato" VARCHAR(255),
    PRIMARY KEY ("id_tipocontrato")
);

CREATE TABLE erp."tipocuenta" (
    "id_tipocuenta" SERIAL NOT NULL,
    "nombre_tipocuenta" VARCHAR(255) NOT NULL,
    "descripcion_tipocuenta" VARCHAR(255),
    PRIMARY KEY ("id_tipocuenta")
);

CREATE TABLE erp."tipocuentabancaria" (
    "id_tipocuentabancaria" SERIAL NOT NULL,
    "nombre_tipocuentabancaria" VARCHAR(255),
    "nemonico_tipocuentabancaria" VARCHAR(255),
    PRIMARY KEY ("id_tipocuentabancaria")
);

CREATE TABLE erp."tipodoc" (
    "id_tipodoc" SERIAL NOT NULL,
    "cod_tipodoc" VARCHAR(255),
    "nombre_tipodoc" VARCHAR(255),
    PRIMARY KEY ("id_tipodoc")
);

CREATE TABLE erp."tipoidentificacionfiscal" (
    "id_tipoidfiscal" VARCHAR(255) NOT NULL,
    "tipotransaccion_tipoidfiscal" VARCHAR(255),
    "tipoidentificacion_tipoidfiscal" VARCHAR(50),
    PRIMARY KEY ("id_tipoidfiscal")
);

CREATE TABLE erp."tipolicenciaconducir" (
    "id_tipolicenciaconducir" SERIAL NOT NULL,
    "nombre_tipolicenciaconducir" VARCHAR(255),
    "descripcion_tipolicenciaconducir" VARCHAR(255),
    PRIMARY KEY ("id_tipolicenciaconducir")
);

CREATE TABLE erp."tipomovimiento" (
    "id_tipomovimiento" SERIAL NOT NULL,
    "nombre_tipomovimiento" VARCHAR(255),
    PRIMARY KEY ("id_tipomovimiento")
);

CREATE TABLE erp."tipomovimientobanco" (
    "id_tipomovimientobanco" SERIAL NOT NULL,
    "nombre_tipomovimientobanco" VARCHAR(255),
    "estado_tipomovimientobanco" BOOLEAN,
    PRIMARY KEY ("id_tipomovimientobanco")
);

CREATE TABLE erp."tipopago" (
    "id_tipopago" SERIAL NOT NULL,
    "cod_tipopago" VARCHAR(255),
    "nombre_tipopago" VARCHAR(255),
    PRIMARY KEY ("id_tipopago")
);

CREATE TABLE erp."tipoproveedor" (
    "id_tipoprov" SERIAL NOT NULL,
    "nombre_tipoprov" VARCHAR(255),
    PRIMARY KEY ("id_tipoprov")
);

CREATE TABLE erp."tiposangre" (
    "id_tiposangre" SERIAL NOT NULL,
    "nombre_tiposangre" VARCHAR(255),
    PRIMARY KEY ("id_tiposangre")
);

CREATE TABLE erp."tipovehiculo" (
    "id_tipovehiculo" SERIAL NOT NULL,
    "nombre_tipovehiculo" VARCHAR(255),
    "id_usuario_creacion" INTEGER,
    "id_usuario_modificacion" INTEGER,
    "fecha_creacion" TIMESTAMP,
    "fecha_modificacion" TIMESTAMP,
    PRIMARY KEY ("id_tipovehiculo")
);

CREATE TABLE erp."transporte" (
    "id_transporte" SERIAL NOT NULL,
    "id_tipovehiculo" INTEGER,
    "pesomin_transporte" NUMERIC(19,2),
    "pesomax_transporte" NUMERIC(19,2),
    "interno_transporte" BOOLEAN,
    "inactivo_transporte" BOOLEAN,
    "id_tipoidentificacionfiscal" VARCHAR(255),
    "id_usuario_creacion" INTEGER,
    "id_usuario_modificacion" INTEGER,
    "fecha_creacion" TIMESTAMP,
    "fecha_modificacion" TIMESTAMP,
    PRIMARY KEY ("id_transporte")
);

CREATE TABLE erp."unidadmedida" (
    "id_unidadmedida" SERIAL NOT NULL,
    "nombre_unidadmedida" VARCHAR(255),
    "tipo_unidadmedida" VARCHAR(255),
    PRIMARY KEY ("id_unidadmedida")
);

CREATE TABLE erp."usuario" (
    "id_usr" SERIAL NOT NULL,
    "id_sucursal" INTEGER NOT NULL,
    "id_persona" INTEGER NOT NULL,
    "nick_usr" VARCHAR(255),
    "pass_usr" VARCHAR(255),
    "fechaingreso_usr" TIMESTAMP,
    "fechainactivacion_usr" TIMESTAMP,
    "horainicio_usr" VARCHAR(255),
    "horafin_usr" VARCHAR(255),
    "tiempo_usr" INTEGER,
    "diasmaximo_usr" INTEGER,
    "id_caja" INTEGER,
    "coddesbloqueo_usr" VARCHAR(255),
    "estado_usr" BOOLEAN,
    "img_usr" VARCHAR(255),
    PRIMARY KEY ("id_usr"),
    FOREIGN KEY ("id_sucursal") REFERENCES erp."sucursal"("id_sucursal"),
    FOREIGN KEY ("id_persona") REFERENCES erp."persona"("id_persona"),
    FOREIGN KEY ("id_caja") REFERENCES erp."caja"("id_caja")
);

CREATE TABLE erp."vehiculoempresa" (
    "id_vehiculo" SERIAL NOT NULL,
    "id_tipovehiculo" INTEGER,
    "id_empleado" INTEGER,
    "placa_vehiculo" VARCHAR(255),
    "pesomax_vehiculo" NUMERIC(19,2),
    "pesomin_vehiculo" NUMERIC(19,2),
    PRIMARY KEY ("id_vehiculo")
);

