#!/usr/bin/env python3
"""
Generate Flyway V1 DDL for schema 'erp' from information_schema columns JSON.
Usage: python3 generate_erp_ddl.py <columns.json>
Output: V1__initial_schema_erp.sql (to stdout)
"""
import json
import sys
from collections import defaultdict

# Primary key column per table (from information_schema.table_constraints)
PK = {
    "acceso_documento": "id_acceso_documento",
    "adquiriente": "id_adquiriente",
    "anticipotransporte": "id_anticipotransporte",
    "aperturacaja": "id_aperturacaja",
    "area": "id_area",
    "areagrupo": "id_areagrupo",
    "asiento": "id_asiento",
    "banco": "id_banco",
    "bancocatalogo": "id_bancocatalogo",
    "bitacora": "id_bitacora",
    "bloqueo": "id_bloqueo",
    "caja": "id_caja",
    "calendariopago": "id_calendariopago",
    "cargafamiliar": "id_cargafml",
    "cargo": "id_cargo",
    "casosverificacion": "id_casosverificacion",
    "categoriacliente": "id_categoriacliente",
    "categoriasucursal": "id_categoriasucursal",
    "ciudad": "id_ciudad",
    "cliente": "id_cliente",
    "cobro": "id_cobro",
    "codigosectorial": "id_codigosectorial",
    "condicionpago": "id_condicionpago",
    "contactoemergencia": "id_contactoemergencia",
    "contratolaboral": "id_contrato",
    "creditocliente": "id_creditocliente",
    "cuenta": "id_cuenta",
    "cuentabancaria": "id_cuentabancaria",
    "cupon": "id_cupon",
    "departamento": "id_departamento",
    "deposito": "id_deposito",
    "desbloqueo": "id_desbloqueo",
    "detcobro": "id_detcobro",
    "detdeposito": "id_detdeposito",
    "detfactura": "id_detfactura",
    "discapacidad": "id_discapacidad",
    "documento": "doc_id_documento",
    "documentocasosverificacion": "id_documentocasosverificacion",
    "documentos_electronicos": "id_documento_electronico",
    "documentoverificacion": "id_documentoverificacion",
    "empleado": "id_empleado",
    "empleadocuenta": "id_empleadocuenta",
    "empresa": "emp_id",
    "errores": "id_errores",
    "estado": "id_estado",
    "estadoasiento": "id_estadoasiento",
    "estadocivil": "id_estadocivil",
    "estadodeposito": "id_estadodeposito",
    "estadoentrega": "id_estadoentrega",
    "estadofactura": "id_estadofactura",
    "estadopago": "id_estadopago",
    "estadosri": "id_estadosri",
    "excepciones_cliente_descuento": "id_excepcion",
    "factura": "id_factura",
    "facturaproveedor": "id_facturaproveedor",
    "formapagocobro": "id_formapagocobro",
    "genero": "id_genero",
    "grupo": "id_grupo",
    "identificacionfiscal": "id_identificacionfiscal",
    "imgusr": "id_imgusr",
    "impuesto_documento": "id_impuesto",
    "indicefinanciero": "id_indicefinanciero",
    "listapreciosprov": "id_listaprecios",
    "matricula": "id_matricula",
    "menu": "id_menu",
    "menu_usuario": "id_menu_usuario",
    "modificacioncreditocliente": "id_modificacioncreditocliente",
    "modulo": "id_modulo",
    "motivodeposito": "id_motivo",
    "movimientobanco": "id_movimientobanco",
    "multidimension": "id_multidimension",
    "nivelestudio": "id_nivelestudio",
    "origeningreso": "id_origeningreso",
    "pagina": "id_pagina",
    "paginarol": "id_paginarol",
    "pago": "id_pago",
    "pagofacturaproveedor": "id_pagofacproveedor",
    "pagorol": "id_pagoroles",
    "pais": "id_pais",
    "parametro": "id_parametro",
    "parentesco": "id_parentesco",
    "parroquia": "id_parroquia",
    "periodo": "id_periodo",
    "persona": "id_persona",
    "posesiondomicilio": "id_posesiondomicilio",
    "producto": "id_producto",
    "proveedor": "id_proveedor",
    "provincia": "id_provincia",
    "proyecto": "id_proyecto",
    "region": "id_region",
    "respuestas_documento": "id_respuestas_documento",
    "retencion": "id",
    "rol": "id_rol",
    "rolusuario": "id_rolusuario",
    "rotacionproducto": "id_rotacionproducto",
    "seg": "id_seg",
    "sesion": "id_sesion",
    "solicitud": "id_solicitud",
    "sorteo": "id_sorteo",
    "subgrupo": "id_subgrupo",
    "sucursal": "id_sucursal",
    "sustentotributario": "id_sustentotributario",
    "tarifaiva": "id_tarifaiva",
    "tarjeta": "id_tarjeta",
    "tipocomprobante": "id_tipocomprobante",
    "tipocontrato": "id_tipocontrato",
    "tipocontribuyente": "id_tipocontribuyente",
    "tipocuenta": "id_tipocuenta",
    "tipocuentabancaria": "id_tipocuentabancaria",
    "tipodoc": "id_tipodoc",
    "tipoidentificacionfiscal": "id_tipoidfiscal",
    "tipolicenciaconducir": "id_tipolicenciaconducir",
    "tipomovimiento": "id_tipomovimiento",
    "tipomovimientobanco": "id_tipomovimientobanco",
    "tipopago": "id_tipopago",
    "tipoproveedor": "id_tipoprov",
    "tiposangre": "id_tiposangre",
    "tipovehiculo": "id_tipovehiculo",
    "transporte": "id_transporte",
    "unidadmedida": "id_unidadmedida",
    "usuario": "id_usr",
    "vehiculoempresa": "id_vehiculo",
}


def sql_type(row):
    dt = row["data_type"]
    nullable = row["is_nullable"] == "YES"
    default = row.get("column_default")
    length = row.get("character_maximum_length")
    prec = row.get("numeric_precision")
    scale = row.get("numeric_scale")

    if dt == "integer":
        t = "INTEGER"
    elif dt == "bigint":
        t = "BIGINT"
    elif dt == "smallint":
        t = "SMALLINT"
    elif dt == "character varying":
        t = f"VARCHAR({length})" if length else "VARCHAR(255)"
    elif dt == "character":
        t = f"CHAR({length})" if length else "CHAR(1)"
    elif dt == "text":
        t = "TEXT"
    elif dt == "boolean":
        t = "BOOLEAN"
    elif dt == "timestamp without time zone":
        t = "TIMESTAMP"
    elif dt == "timestamp with time zone":
        t = "TIMESTAMPTZ"
    elif dt == "date":
        t = "DATE"
    elif dt == "time without time zone":
        t = "TIME"
    elif dt == "numeric":
        if prec and scale is not None:
            t = f"NUMERIC({prec},{scale})"
        elif prec:
            t = f"NUMERIC({prec})"
        else:
            t = "NUMERIC(19,2)"
    elif dt == "real":
        t = "REAL"
    elif dt == "double precision":
        t = "DOUBLE PRECISION"
    else:
        t = dt.upper()

    nn = " NOT NULL" if not nullable else ""
    def_clause = ""
    if default and "nextval" not in str(default).lower():
        def_clause = f" DEFAULT {default}"
    elif default and "nextval" in str(default).lower():
        def_clause = f" DEFAULT {default}"

    return f"{t}{nn}{def_clause}"


def main():
    if len(sys.argv) < 2:
        print("Usage: generate_erp_ddl.py <columns.json>", file=sys.stderr)
        sys.exit(1)
    with open(sys.argv[1]) as f:
        rows = json.load(f)
    by_table = defaultdict(list)
    for r in rows:
        by_table[r["table_name"]].append(r)
    for t in by_table:
        by_table[t].sort(key=lambda x: x["ordinal_position"])

    print("-- Initial DDL: erp schema (erp_base_db.erp.table_name)")
    print("-- Generated from current public schema structure")
    print("")

    for table in sorted(by_table.keys()):
        cols = by_table[table]
        pk_col = PK.get(table)
        parts = []
        for c in cols:
            col_name = c["column_name"]
            typ = sql_type(c)
            parts.append(f'    "{col_name}" {typ}')
        if pk_col:
            parts.append(f'    PRIMARY KEY ("{pk_col}")')
        body = ",\n".join(parts)
        print(f'CREATE TABLE erp."{table}" (')
        print(body)
        print(");")
        print("")


if __name__ == "__main__":
    main()
