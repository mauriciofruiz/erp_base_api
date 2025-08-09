package com.sicon.tba.repository;

import com.sicon.tba.dto.RrhCargasFamiliaresDto;
import com.sicon.tba.dto.RrhContactoEmergenciaDto;
import com.sicon.tba.dto.RrhhContratosDto;
import com.sicon.tba.dto.TbaCuentaBancariaDto;
import com.sicon.tba.dto.EmpleadoPersonaDto;
import com.sicon.tba.dto.TbaEmpleadoDto;
import com.sicon.tba.dto.TbaEmpleadoFichaDto;
import com.sicon.tba.dto.TbaInformacionContractualDto;
import com.sicon.tba.models.TbaEmpleado;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface TbaEmpleadoRepository extends ReactiveCrudRepository<TbaEmpleado, Integer> {


    @Query("SELECT te.ID_PERSONA,\n" +
            "       te.ID_EMPLEADO,\n" +
            "       tp.IDENTIFICACION_PERSONA,\n" +
            "       tp.NOMBRE_PERSONA,\n" +
            "       tp.CELULAR_PERSONA,\n" +
            "       tp.CORREO_PERSONA,\n" +
            "       tu.IMG_USR,\n" +
            "       tc.NOMBRE_CARGO,\n" +
            "       ts.NOMBRE_SUCURSAL,\n" +
            "       te.SALARIO_EMPLEADO,\n" +
            "       te.FECHAINGRESOEMPRESA_EMPLEADO,\n" +
            "       te.FECHAREINGRESO_EMPLEADO,\n" +
            "       te.estado_empleado as estado\n" +
            "FROM TBA_EMPLEADO te\n" +
            "         LEFT JOIN TBA_USUARIO tu ON tu.ID_PERSONA = te.ID_PERSONA\n" +
            "         JOIN TBA_PERSONA tp ON te.ID_PERSONA = tp.ID_PERSONA\n" +
            "         JOIN TBA_CARGO tc ON tu.cargo_usr = tc.ID_CARGO\n" +
            "         JOIN TBA_SUCURSAL ts ON te.ID_SUCURSAL = ts.ID_SUCURSAL\n" +
            "WHERE tp.ESEMPLEADO_PERSONA\n" +
            "  AND (:nombreEmpleado is null or UPPER(tp.NOMBRE_PERSONA) LIKE UPPER(CONCAT('%', :nombreEmpleado, '%')))\n" +
            "  AND (:identificacion is null or tp.IDENTIFICACION_PERSONA LIKE CONCAT('%', :identificacion, '%'))")
    Flux<TbaEmpleadoFichaDto> findEmpleadoByParams(String nombreEmpleado, String identificacion);


    @Query(" SELECT  TP.ID_PERSONA, TE.ID_EMPLEADO, TE.ID_CARGO, TE.ID_SUCURSAL ,TP.IDENTIFICACION_PERSONA, TP.NOMBRE_PERSONA, TP.FECHANACIMIENTO_PERSONA," +
            " R.ID_TIPOSANGRE ,R.NOMBRE_TIPOSANGRE , RN.ID_NIVELESTUDIO ,RN.NOMBRE_NIVELESTUDIO," +
            " TC.NOMBRE_CIUDAD, TP.ID_PARROQUIA, TPO.NOMBRE_PROVINCIA,  ES.ID_ESTADOCIVIL ,TPA.NOMBRE_PARROQUIA,ES.NOMBRE_ESTADOCIVIL, TG.ID_GENERO ,TG.NOMBRE_GENERO, TE.TITULO_EMPLEADO, TP.DIRECCION_PERSONA," +
            " TP.SECTORDOMICILIO_PERSONA, TP.CORREO_PERSONA, TP.CELULAR_PERSONA, TE.TELEFONO_EMPLEADO, RT.NOMBRE_TIPOLICENCIACONDUCIR," +
            " RT.DESCRIPCION_TIPOLICENCIACONDUCIR,TE.ID_TIPOLICENCIACONDUCIR ,TE.FECHACADUCALICENCIA_EMPLEADO,TE.ESDISCAPACITADO_EMPLEADO, TE.ID_DISCAPACIDAD, RD.NOMBRE_DISCAPACIDAD," +
            " TE.PORCDISCAPACIDAD_EMPLEADO, TE.PATHCEDULA_EMPLEADO, TE.PATHLICENCIA_EMPLEADO, TE.PATHCV_EMPLEADO, TU.IMG_USR" +
            " FROM TBA_PARROQUIA TPA" +
            " JOIN TBA_CIUDAD TC on TPA.ID_CIUDAD = TC.ID_CIUDAD" +
            " JOIN TBA_PROVINCIA TPO on TC.ID_PROVINCIA = TPO.ID_PROVINCIA and TC.ID_PROVINCIA = TPO.ID_PROVINCIA" +
            " RIGHT JOIN TBA_PERSONA TP on TPA.ID_PARROQUIA = TP.ID_PARROQUIA" +
            " JOIN TBA_ESTADOCIVIL ES ON TP.ID_ESTADOCIVIL = ES.ID_ESTADOCIVIL" +
            " JOIN TBA_GENERO TG ON TP.ID_GENERO =  TG.ID_GENERO" +
            " LEFT JOIN TBA_EMPLEADO TE on TP.ID_PERSONA = TE.ID_PERSONA " +
            " LEFT JOIN TBA_USUARIO TU on TP.ID_PERSONA = TU.ID_PERSONA" +
            " LEFT JOIN RRH_TIPOLICENCIACONDUCIR RT on TE.ID_TIPOLICENCIACONDUCIR = RT.ID_TIPOLICENCIACONDUCIR" +
            " LEFT JOIN RRH_DISCAPACIDAD RD on TE.ID_DISCAPACIDAD = RD.ID_DISCAPACIDAD" +
            " LEFT JOIN RRH_NIVELESTUDIO RN on TE.ID_NIVELESTUDIO = RN.ID_NIVELESTUDIO" +
            " LEFT JOIN RRH_TIPOSANGRE R on TE.ID_TIPOSANGRE = R.ID_TIPOSANGRE" +
            " WHERE TE.ID_EMPLEADO =:idEmpleado")
    Mono<TbaEmpleadoDto> getFichaEmpleado(Integer idEmpleado);

    @Query(" SELECT RC.ID_CUENTABANCARIA ,TB.ID_BANCOCATALOGO,RC.ID_TIPOCUENTABANCARIA," +
            " TB.NOMBRE_BANCOCATALOGO,TT.NOMBRE_TIPOCUENTABANCARIA , RC.NUMERO_CUENTABANCARIA, RC.ESTADO_CUENTABANCARIA " +
            " FROM RRH_CUENTABANCARIA RC" +
            " JOIN TBA_EMPLEADO TE on RC.ID_EMPLEADO = TE.ID_EMPLEADO " +
            " JOIN TBA_BANCOCATALOGO TB on RC.ID_BANCOCATALOGO = TB.ID_BANCOCATALOGO" +
            " JOIN TBA_TIPOCUENTABANCARIA TT on RC.ID_TIPOCUENTABANCARIA = TT.ID_TIPOCUENTABANCARIA" +
            " WHERE TE.ID_EMPLEADO =:idEmpleado")
    Flux<TbaCuentaBancariaDto> getBancaryCount(Integer idEmpleado);

    @Query("SELECT  RC.ID_CONTACTOEMERGENCIA, RC.ID_PARENTESCO ,RP.NOMBRE_PARENTESCO, RC.NOMBRE_CONTACTOEMERGENCIA,RC.CELULAR_CONTACTOEMERGENCIA, RC.TELEFONO_CONTACTOEMERGENCIA" +
            " FROM RRH_CONTACTOEMERGENCIA RC" +
            " RIGHT JOIN RRH_PARENTESCO RP on RP.ID_PARENTESCO = RC.ID_PARENTESCO" +
            " WHERE RC.ID_EMPLEADO =:idEmpleado")
    Flux<RrhContactoEmergenciaDto> getEmergencyContact(Integer idEmpleado);

    @Query(" SELECT R.ID_CARGAFML,R.ID_PARENTESCO ,RP.NOMBRE_PARENTESCO,  R.CEDULA_CARGAFML,  R.NOMBRE_CARGAFML, R.FECHANACIMIENTO_CARGAFML, R.ACTIVA_CARGAFML," +
            " R.CAPACIDADESPECIAL_CARGAFML " +
            " FROM RRH_CARGAFAMILIAR R" +
            " JOIN RRH_PARENTESCO RP on RP.ID_PARENTESCO = R.ID_PARENTESCO" +
            " WHERE R.ID_EMPLEADO =:idEmpleado")
    Flux<RrhCargasFamiliaresDto> getCargasFamiliares(Integer idEmpleado);


    @Query(" SELECT TE.FECHAINGRESOEMPRESA_EMPLEADO,TE.FECHAREINGRESO_EMPLEADO , S.ID_SUCURSAL, S.NOMBRE_SUCURSAL,  TE.SALARIO_EMPLEADO, TC.ID_DEPARTAMENTO ,TD.NOMBRE_DEPARTAMENTO," +
            " TC.ID_CARGO ,TC.NOMBRE_CARGO, TE.PROVISIONVACACIONES_EMPLEADO, TE.QUINCENA_EMPLEADO, TE.VALORQUINCENA_EMPLEADO, TE.IESSPERSONAL_EMPLEADO," +
            " TE.IESSPATRONAL_EMPLEADO, TE.DECIMO3ACUMULADO_EMPLEADO,TE.DECIMO4ACUMULADO_EMPLEADO, TE.FRACUMULADOS_EMPLEADO, TE.IMPUESTORENTA_EMPLEADO," +
            " TE.FECHASALIDA_EMPLEADO, TE.MOTIVOSALIDA_EMPLEADO, TE.FECHAREINGRESO_EMPLEADO, TE.MOTIVOREINGRESO_EMPLEADO"+
            " FROM TBA_SUCURSAL S" +
            " RIGHT JOIN TBA_EMPLEADO TE on S.ID_SUCURSAL = TE.ID_SUCURSAL" +
            " JOIN TBA_CARGO TC on TE.ID_CARGO = TC.ID_CARGO" +
            " JOIN TBA_DEPARTAMENTO TD on TC.ID_DEPARTAMENTO = TD.ID_DEPARTAMENTO" +
            " WHERE TE.ID_EMPLEADO =:idEmpleado")
    Mono<TbaInformacionContractualDto> getContractualInformation(Integer idEmpleado);


    @Query(" SELECT RC.ID_CONTRATO ,RT.ID_TIPOCONTRATO , RT.NOMBRE_TIPOCONTRATO, RM.ID_MODALIDADCONTRATO ,RM.NOMBRE_MODALIDADCONTRATO, RC.FECHAINICIO_CONTRATO, RC.FECHAFIN_CONTRATO, RC.ESTADO_CONTRATO," +
            " RC.ARCHIVO_CONTRATO , RC.NROHORAS_CONTRATO" +
            " FROM RRH_MODALIDADCONTRATO RM" +
            " RIGHT JOIN RRH_CONTRATOLABORAL RC ON  RM.ID_MODALIDADCONTRATO = RC.ID_MODALIDADCONTRATO" +
            " RIGHT JOIN RRH_TIPOCONTRATO RT on RT.ID_TIPOCONTRATO = RC.ID_TIPOCONTRATO" +
            " WHERE RC.ID_EMPLEADO =:idEmpleado")
    Flux<RrhhContratosDto> getContractEmployee(Integer idEmpleado);
    Mono<Boolean> existsByIdPersona(Integer idPersona);

    @Query(" SELECT TP.ID_PERSONA, TE.ID_EMPLEADO, TS.ID_SUCURSAL ,TP.IDENTIFICACION_PERSONA, TP.NOMBRE_PERSONA," +
            " TE.QUINCENA_EMPLEADO,TE.VALORQUINCENA_EMPLEADO, TS.NOMBRE_SUCURSAL, RC.ESTADO_CUENTABANCARIA, TE.ESTADO_EMPLEADO" +
            " FROM TBA_EMPLEADO TE" +
            "         LEFT JOIN TBA_PERSONA TP on TE.ID_PERSONA = TP.ID_PERSONA" +
            "         LEFT JOIN TBA_SUCURSAL TS on TE.ID_SUCURSAL = TS.ID_SUCURSAL" +
            "         LEFT JOIN RRH_CUENTABANCARIA RC on TE.ID_EMPLEADO = RC.ID_EMPLEADO" +
            " WHERE ESTADO_EMPLEADO =:estadoEmpleado")
    Flux<EmpleadoPersonaDto> getAllByEstadoEmpleado(Integer estadoEmpleado);
}

