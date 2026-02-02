package erp.base.api.repository

import erp.base.api.dto.CargasFamiliaresDto
import erp.base.api.dto.ContactoEmergenciaDto
import erp.base.api.dto.CuentaBancariaDto
import erp.base.api.dto.EmpleadoDto
import erp.base.api.dto.EmpleadoFichaDto
import erp.base.api.dto.EmpleadoPersonaDto
import erp.base.api.dto.InformacionContractualDto
import erp.base.api.dto.RrhhContratosDto
import erp.base.api.models.Empleado
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface EmpleadoRepository : ReactiveCrudRepository<Empleado, Int> {

    @Query(
        "SELECT te.ID_PERSONA,\n" +
            "       te.ID_EMPLEADO,\n" +
            "       tp.IDENTIFICACION_PERSONA,\n" +
            "       tp.NOMBRE_PERSONA,\n" +
            "       tp.CELULAR_PERSONA,\n" +
            "       tp.CORREO_PERSONA,\n" +
            "       tu.IMG_USR,\n" +
            "       'Cargo no asignado',\n" +
            "       ts.NOMBRE_SUCURSAL,\n" +
            "       te.SALARIO_EMPLEADO,\n" +
            "       te.FECHAINGRESOEMPRESA_EMPLEADO,\n" +
            "       te.FECHAREINGRESO_EMPLEADO,\n" +
            "       te.estado_empleado as estado\n" +
            "FROM EMPLEADO te\n" +
            "         LEFT JOIN USUARIO tu ON tu.ID_PERSONA = te.ID_PERSONA\n" +
            "         JOIN PERSONA tp ON te.ID_PERSONA = tp.ID_PERSONA\n" +
            "         JOIN SUCURSAL ts ON te.ID_SUCURSAL = ts.ID_SUCURSAL\n" +
            "WHERE tp.ESEMPLEADO_PERSONA\n" +
            "  AND (:nombreEmpleado is null or UPPER(tp.NOMBRE_PERSONA) LIKE UPPER(CONCAT('%', :nombreEmpleado, '%')))\n" +
            "  AND (:identificacion is null or tp.IDENTIFICACION_PERSONA LIKE CONCAT('%', :identificacion, '%'))"
    )
    fun findEmpleadoByParams(nombreEmpleado: String?, identificacion: String?): Flux<EmpleadoFichaDto>

    @Query(
        " SELECT  TP.ID_PERSONA, TE.ID_EMPLEADO, TE.ID_CARGO, TE.ID_SUCURSAL ,TP.IDENTIFICACION_PERSONA, TP.NOMBRE_PERSONA, TP.FECHANACIMIENTO_PERSONA," +
            " R.ID_TIPOSANGRE ,R.NOMBRE_TIPOSANGRE , RN.ID_NIVELESTUDIO ,RN.NOMBRE_NIVELESTUDIO," +
            " TC.NOMBRE_CIUDAD, TP.ID_PARROQUIA, TPO.NOMBRE_PROVINCIA,  ES.ID_ESTADOCIVIL ,TPA.NOMBRE_PARROQUIA,ES.NOMBRE_ESTADOCIVIL, TG.ID_GENERO ,TG.NOMBRE_GENERO, TE.TITULO_EMPLEADO, TP.DIRECCION_PERSONA," +
            " TP.SECTORDOMICILIO_PERSONA, TP.CORREO_PERSONA, TP.CELULAR_PERSONA, TE.TELEFONO_EMPLEADO, RT.NOMBRE_TIPOLICENCIACONDUCIR," +
            " RT.DESCRIPCION_TIPOLICENCIACONDUCIR,TE.ID_TIPOLICENCIACONDUCIR ,TE.FECHACADUCALICENCIA_EMPLEADO,TE.ESDISCAPACITADO_EMPLEADO, TE.ID_DISCAPACIDAD, RD.NOMBRE_DISCAPACIDAD," +
            " TE.PORCDISCAPACIDAD_EMPLEADO, TE.PATHCEDULA_EMPLEADO, TE.PATHLICENCIA_EMPLEADO, TE.PATHCV_EMPLEADO, TU.IMG_USR" +
            " FROM PARROQUIA TPA" +
            " JOIN CIUDAD TC on TPA.ID_CIUDAD = TC.ID_CIUDAD" +
            " JOIN PROVINCIA TPO on TC.ID_PROVINCIA = TPO.ID_PROVINCIA and TC.ID_PROVINCIA = TPO.ID_PROVINCIA" +
            " RIGHT JOIN PERSONA TP on TPA.ID_PARROQUIA = TP.ID_PARROQUIA" +
            " JOIN ESTADOCIVIL ES ON TP.ID_ESTADOCIVIL = ES.ID_ESTADOCIVIL" +
            " JOIN GENERO TG ON TP.ID_GENERO =  TG.ID_GENERO" +
            " LEFT JOIN EMPLEADO TE on TP.ID_PERSONA = TE.ID_PERSONA " +
            " LEFT JOIN USUARIO TU on TP.ID_PERSONA = TU.ID_PERSONA" +
            " LEFT JOIN TIPOLICENCIACONDUCIR RT on TE.ID_TIPOLICENCIACONDUCIR = RT.ID_TIPOLICENCIACONDUCIR" +
            " LEFT JOIN DISCAPACIDAD RD on TE.ID_DISCAPACIDAD = RD.ID_DISCAPACIDAD" +
            " LEFT JOIN NIVELESTUDIO RN on TE.ID_NIVELESTUDIO = RN.ID_NIVELESTUDIO" +
            " LEFT JOIN TIPOSANGRE R on TE.ID_TIPOSANGRE = R.ID_TIPOSANGRE" +
            " WHERE TE.ID_EMPLEADO =:idEmpleado"
    )
    fun getFichaEmpleado(idEmpleado: Int): Mono<EmpleadoDto>

    @Query(
        " SELECT RC.ID_CUENTABANCARIA ,TB.ID_BANCOCATALOGO,RC.ID_TIPOCUENTABANCARIA," +
            " TB.NOMBRE_BANCOCATALOGO,TT.NOMBRE_TIPOCUENTABANCARIA , RC.NUMERO_CUENTABANCARIA, RC.ESTADO_CUENTABANCARIA " +
            " FROM CUENTABANCARIA RC" +
            " JOIN EMPLEADO TE on RC.ID_EMPLEADO = TE.ID_EMPLEADO " +
            " JOIN BANCOCATALOGO TB on RC.ID_BANCOCATALOGO = TB.ID_BANCOCATALOGO" +
            " JOIN TIPOCUENTABANCARIA TT on RC.ID_TIPOCUENTABANCARIA = TT.ID_TIPOCUENTABANCARIA" +
            " WHERE TE.ID_EMPLEADO =:idEmpleado"
    )
    fun getBancaryCount(idEmpleado: Int): Flux<CuentaBancariaDto>

    @Query(
        "SELECT  RC.ID_CONTACTOEMERGENCIA, RC.ID_PARENTESCO ,RP.NOMBRE_PARENTESCO, RC.NOMBRE_CONTACTOEMERGENCIA,RC.CELULAR_CONTACTOEMERGENCIA, RC.TELEFONO_CONTACTOEMERGENCIA" +
            " FROM CONTACTOEMERGENCIA RC" +
            " RIGHT JOIN PARENTESCO RP on RP.ID_PARENTESCO = RC.ID_PARENTESCO" +
            " WHERE RC.ID_EMPLEADO =:idEmpleado"
    )
    fun getEmergencyContact(idEmpleado: Int): Flux<ContactoEmergenciaDto>

    @Query(
        " SELECT R.ID_CARGAFML,R.ID_PARENTESCO ,RP.NOMBRE_PARENTESCO,  R.CEDULA_CARGAFML,  R.NOMBRE_CARGAFML, R.FECHANACIMIENTO_CARGAFML, R.ACTIVA_CARGAFML," +
            " R.CAPACIDADESPECIAL_CARGAFML " +
            " FROM CARGAFAMILIAR R" +
            " JOIN PARENTESCO RP on RP.ID_PARENTESCO = R.ID_PARENTESCO" +
            " WHERE R.ID_EMPLEADO =:idEmpleado"
    )
    fun getCargasFamiliares(idEmpleado: Int): Flux<CargasFamiliaresDto>

    @Query(
        " SELECT TE.FECHAINGRESOEMPRESA_EMPLEADO,TE.FECHAREINGRESO_EMPLEADO , S.ID_SUCURSAL, S.NOMBRE_SUCURSAL,  TE.SALARIO_EMPLEADO, TC.ID_DEPARTAMENTO ,TD.NOMBRE_DEPARTAMENTO," +
            " TC.ID_CARGO ,TC.NOMBRE_CARGO, TE.PROVISIONVACACIONES_EMPLEADO, TE.QUINCENA_EMPLEADO, TE.VALORQUINCENA_EMPLEADO, TE.IESSPERSONAL_EMPLEADO," +
            " TE.IESSPATRONAL_EMPLEADO, TE.DECIMO3ACUMULADO_EMPLEADO,TE.DECIMO4ACUMULADO_EMPLEADO, TE.FRACUMULADOS_EMPLEADO, TE.IMPUESTORENTA_EMPLEADO," +
            " TE.FECHASALIDA_EMPLEADO, TE.MOTIVOSALIDA_EMPLEADO, TE.FECHAREINGRESO_EMPLEADO, TE.MOTIVOREINGRESO_EMPLEADO" +
            " FROM SUCURSAL S" +
            " RIGHT JOIN EMPLEADO TE on S.ID_SUCURSAL = TE.ID_SUCURSAL" +
            " JOIN CARGO TC on TE.ID_CARGO = TC.ID_CARGO" +
            " JOIN DEPARTAMENTO TD on TC.ID_DEPARTAMENTO = TD.ID_DEPARTAMENTO" +
            " WHERE TE.ID_EMPLEADO =:idEmpleado"
    )
    fun getContractualInformation(idEmpleado: Int): Mono<InformacionContractualDto>

    @Query(
        " SELECT RC.ID_CONTRATO ,RT.ID_TIPOCONTRATO , RT.NOMBRE_TIPOCONTRATO, RM.ID_MODALIDADCONTRATO ,RM.NOMBRE_MODALIDADCONTRATO, RC.FECHAINICIO_CONTRATO, RC.FECHAFIN_CONTRATO, RC.ESTADO_CONTRATO," +
            " RC.ARCHIVO_CONTRATO , RC.NROHORAS_CONTRATO" +
            " FROM MODALIDADCONTRATO RM" +
            " RIGHT JOIN CONTRATOLABORAL RC ON  RM.ID_MODALIDADCONTRATO = RC.ID_MODALIDADCONTRATO" +
            " RIGHT JOIN TIPOCONTRATO RT on RT.ID_TIPOCONTRATO = RC.ID_TIPOCONTRATO" +
            " WHERE RC.ID_EMPLEADO =:idEmpleado"
    )
    fun getContractEmployee(idEmpleado: Int): Flux<RrhhContratosDto>

    fun existsByIdPersona(idPersona: Int): Mono<Boolean>

    @Query(
        " SELECT TP.ID_PERSONA, TE.ID_EMPLEADO, TS.ID_SUCURSAL ,TP.IDENTIFICACION_PERSONA, TP.NOMBRE_PERSONA," +
            " TE.QUINCENA_EMPLEADO,TE.VALORQUINCENA_EMPLEADO, TS.NOMBRE_SUCURSAL, RC.ESTADO_CUENTABANCARIA, TE.ESTADO_EMPLEADO" +
            " FROM EMPLEADO TE" +
            "         LEFT JOIN PERSONA TP on TE.ID_PERSONA = TP.ID_PERSONA" +
            "         LEFT JOIN SUCURSAL TS on TE.ID_SUCURSAL = TS.ID_SUCURSAL" +
            "         LEFT JOIN CUENTABANCARIA RC on TE.ID_EMPLEADO = RC.ID_EMPLEADO" +
            " WHERE ESTADO_EMPLEADO =:estadoEmpleado"
    )
    fun getAllByEstadoEmpleado(estadoEmpleado: Int): Flux<EmpleadoPersonaDto>
}
