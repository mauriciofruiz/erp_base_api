package com.sicon.tba.service;

import com.sicon.tba.constants.ErrorMessage;
import com.sicon.tba.dto.ConCuentaDatosContablesDto;
import com.sicon.tba.dto.ConEmpleadoCuentaDto;
import com.sicon.tba.dto.RrhCargasFamiliaresDto;
import com.sicon.tba.dto.RrhContactoEmergenciaDto;
import com.sicon.tba.dto.RrhhContratosDto;
import com.sicon.tba.dto.TbaCuentaBancariaDto;
import com.sicon.tba.dto.EmpleadoPersonaDto;
import com.sicon.tba.dto.TbaEmpleadoDto;
import com.sicon.tba.dto.TbaEmpleadoFichaDto;
import com.sicon.tba.dto.TbaInformacionContractualDto;
import com.sicon.tba.dto.TbaPersonaDto;
import com.sicon.tba.enums.StateEnum;
import com.sicon.tba.enums.ContractModalityEnum;
import com.sicon.tba.exception.CustomException;
import com.sicon.tba.models.*;
import com.sicon.tba.models.rrh.RrhCargaFamiliar;
import com.sicon.tba.models.rrh.RrhContactoEmergencia;
import com.sicon.tba.models.rrh.RrhContratoLaboral;
import com.sicon.tba.models.rrh.RrhCuentaBancaria;
import com.sicon.tba.repository.TbaSucursalRepository;
import com.sicon.tba.repository.cont.ConCuentaRepository;
import com.sicon.tba.repository.TbaEmpleadoRepository;
import com.sicon.tba.repository.TbaPersonaRepository;
import com.sicon.tba.repository.rrh.RrhContratoLaboralRepository;
import com.sicon.tba.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TbaEmpleadoService {

    private final TbaEmpleadoRepository tbaEmpleadoRepository;
    private final TbaPersonaRepository tbaPersonaRepository;
    private final RrhContratoLaboralRepository rrhContratoLaboralRepository;
    private final ConCuentaRepository conCuentaRepository;
    private final TbaSucursalRepository tbaSucursalRepository;

    // Servicios
    private final TbaPersonaService tbaPersonaService;
    private final RrhCargaFamiliarService rrhCargaFamiliarService;
    private final RrhContactoEmergenciaService rrhContactoEmergenciaService;
    private final RrhContractoLaboralService rrhContractoLaboralService;
    private final TbaCuentaBancariaService tbaCuentaBancariaService;
    private final ConEmpleadoCuentaService conEmpleadoCuentaService;
    private final TbaDepartamentoService serviceDepartamento;
    private final TbaUsuarioService tbaUsuarioService;
    private final ConMultidimensionService conMultidimensionService;
    // Parametros
    private LocalDateTime currentDate = LocalDateTime.now();
    Flux<RrhCargaFamiliar> familyBurdenFlux = Flux.empty();
    Flux<RrhContactoEmergencia> emergenyContactFlux = Flux.empty();
    Flux<RrhContratoLaboral> workContractFlux = Flux.empty();
    Flux<RrhCuentaBancaria> bankAccountFlux = Flux.empty();
    Mono<ConEmpleadoCuenta> employeeAcountMono = Mono.empty();
    Mono<TbaUsuario> userMono = Mono.empty();

    // Variables

    private Boolean existsEmployeeNickName = false;

    /**
     * Método Para crear al empleado y a la persona
     *
     * @param tbaEmpleadoDto TbaEmpleadoDto
     * @return Objeto con los datos del empleado para la ficha
     */


    public Mono<Object> createEmployeePerson(TbaEmpleadoDto tbaEmpleadoDto, TbaInformacionContractualDto informacionContractualDto) {
        Mono<Boolean> employeeExistsByIdentification = tbaPersonaRepository.findByIdentificacionPersona(tbaEmpleadoDto.getIdentificacionPersona()).hasElement();
        return employeeExistsByIdentification.publishOn(Schedulers.boundedElastic()).flatMap(existsEmployeePerson -> {
            if (existsEmployeePerson) {
                Mono<TbaPersona> personMono = tbaPersonaRepository.findByIdentificacionPersona(tbaEmpleadoDto.getIdentificacionPersona());
                return personMono.flatMap(tbaPersona -> {
                    Mono<Boolean> existsEmployee = tbaEmpleadoRepository.existsByIdPersona(tbaPersona.getIdPersona());
                    return existsEmployee.publishOn(Schedulers.boundedElastic()).flatMap(exists -> {
                        if (exists) {
                            return Mono.error(new CustomException(HttpStatus.CONFLICT, ErrorMessage.EMPLOYEE_FOUND));
                        } else {
                            return fillEmployeeData(tbaPersona, tbaEmpleadoDto, informacionContractualDto);
                        }
                    });
                });
            } else {
                return createPerson(tbaEmpleadoDto).flatMap(person -> fillEmployeeData(person, tbaEmpleadoDto, informacionContractualDto));
            }
        });
    }


    public Mono<Object> fillEmployeeData(TbaPersona person, TbaEmpleadoDto tbaEmpleadoDto, TbaInformacionContractualDto informacionContractualDto) {
        return Mono.defer(() -> {
            Integer personId = person.getIdPersona();

            return createEmployee(tbaEmpleadoDto, personId, informacionContractualDto)
                    .publishOn(Schedulers.boundedElastic())
                    .flatMap(employee -> {
                        Integer employeeId = employee.getIdEmpleado();

                        if (tbaEmpleadoDto.getRrhCargasFamiliaresDtos() != null && !tbaEmpleadoDto.getRrhCargasFamiliaresDtos().isEmpty()) {
                            familyBurdenFlux = rrhCargaFamiliarService.createEmployeeFamilyBurden(tbaEmpleadoDto.getRrhCargasFamiliaresDtos(), employeeId);
                        }

                        if (tbaEmpleadoDto.getRrhContactoEmergenciaDtos() != null && !tbaEmpleadoDto.getRrhContactoEmergenciaDtos().isEmpty()) {
                            emergenyContactFlux = rrhContactoEmergenciaService.createEmployeeEmergencyContact(tbaEmpleadoDto.getRrhContactoEmergenciaDtos(), employeeId);
                        }

                        if (informacionContractualDto.getRrhhContratosDtos() != null && !informacionContractualDto.getRrhhContratosDtos().isEmpty()) {
                            workContractFlux = rrhContractoLaboralService.createWorkContract(informacionContractualDto.getRrhhContratosDtos(), employeeId, currentDate);
                        }

                        if (tbaEmpleadoDto.getTbaCuentaBancariaDtos() != null && !tbaEmpleadoDto.getTbaCuentaBancariaDtos().isEmpty()) {
                            bankAccountFlux = tbaCuentaBancariaService.createBankAccountEmployee(tbaEmpleadoDto.getTbaCuentaBancariaDtos(), employeeId);
                        }

                        Flux<ConEmpleadoCuenta> employeeAccountFlux = serviceDepartamento.getDepartmentById(informacionContractualDto.getIdDepartamento())
                                .flatMapMany(department -> conEmpleadoCuentaService.createEmployeeAccount(department.getIdDepartamento(), department.getIdAreaGrupo(), employeeId));
                        Mono<Boolean> existsPersonMono = tbaUsuarioService.validateByIdPersona(person.getIdPersona());
                        Mono<ConMultidimension> multidimensionMono = conMultidimensionService.createMultidimensionEmployee(employeeId);
                        return Mono.zip(
                                Mono.just(employee),
                                familyBurdenFlux.collectList(),
                                emergenyContactFlux.collectList(),
                                workContractFlux.collectList(),
                                bankAccountFlux.collectList(),
                                employeeAccountFlux.collectList(),
                                multidimensionMono,
                                existsPersonMono
                        ).publishOn(Schedulers.boundedElastic()).flatMap(tuple -> {
                            String nickName;
                            boolean existsPerson = tuple.getT8();
                            if (!existsPerson) {
                                int i = 0;
                                do {
                                    nickName = tbaUsuarioService.createNickName(person.getNombrePersona(), existsEmployeeNickName, i);
                                    existsEmployeeNickName = tbaUsuarioService.validateNickName(nickName).block();
                                    i++;
                                } while (existsEmployeeNickName);
                                try {
                                    userMono = tbaUsuarioService.createUser(employee, nickName, person.getIdPersona(), informacionContractualDto.getIdCargo());
                                } catch (Exception e) {
                                    return Mono.error(new CustomException(HttpStatus.BAD_REQUEST, e.getMessage()));
                                }
                            }
                            return Mono.zip(Mono.just(person), Mono.just(employee), userMono);
                        });
                    });
        });
    }


    /**
     * Método Para obtener los datos para la ficha Empleado
     *
     * @param completeName   Nombre Completo del Empleado
     * @param identification Identificación del Emplado
     * @return Objeto con los datos del empleado para la ficha
     */
    public Flux<TbaEmpleadoFichaDto> getEmployee(String completeName, String identification) {
        Flux<TbaEmpleadoFichaDto> tbaEmpleadoDtoMono = tbaEmpleadoRepository.findEmpleadoByParams(completeName, identification);
        return tbaEmpleadoDtoMono.switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.EMPLOYEE_NOT_FOUND)));
    }

    /**
     * Método Para obtener todos los Datos de la Ficha del Empleado
     *
     * @param id id del empleadoD
     * @return Objeto con los datos del empleado para la ficha
     */

    public Mono<TbaEmpleadoDto> getEmployeeRecord(Integer id) {
        Mono<TbaEmpleadoDto> tbaEmpleadoServiceMono = tbaEmpleadoRepository.getFichaEmpleado(id).log();

        Mono<List<RrhCargasFamiliaresDto>> familyBurdensDtoMono = tbaEmpleadoRepository.getCargasFamiliares(id).collectList().log().onErrorResume(throwable -> {
            return Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.EMPLOYEE_NOT_FOUND));
        });

        Mono<List<RrhContactoEmergenciaDto>> emergenyContactDtoMono = tbaEmpleadoRepository.getEmergencyContact(id).collectList().log().onErrorResume(throwable -> {
            return Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.EMPLOYEE_NOT_FOUND));
        });

        Mono<List<TbaCuentaBancariaDto>> bankAccountDtoMono = tbaEmpleadoRepository.getBancaryCount(id).collectList().log().onErrorResume(throwable -> {
            return Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.EMPLOYEE_NOT_FOUND));
        });

        return Mono.zip(familyBurdensDtoMono, emergenyContactDtoMono, tbaEmpleadoServiceMono, bankAccountDtoMono).flatMap(tuple -> {
            List<RrhCargasFamiliaresDto> familyBurdensDtoList = tuple.getT1();
            List<RrhContactoEmergenciaDto> emergencyContactDtoList = tuple.getT2();
            TbaEmpleadoDto employeeDto = tuple.getT3();
            List<TbaCuentaBancariaDto> tbaCuentaBancariaDtoList = tuple.getT4();
            for (RrhCargasFamiliaresDto familyBurdenDto : familyBurdensDtoList) {
                try {
                    familyBurdenDto.setEdad(Utils.calcularEdad(familyBurdenDto.getFechanacimientoCargafml()));
                } catch (ParseException e) {
                    return Mono.error(new CustomException(HttpStatus.BAD_REQUEST, ErrorMessage.ERROR_DATE));
                }
            }

            return Mono.just(TbaEmpleadoDto.builder().idPersona(
                            employeeDto.getIdPersona())
                    .idEmpleado(employeeDto.getIdEmpleado())
                    .identificacionPersona(employeeDto.getIdentificacionPersona())
                    .idEstadocivil(employeeDto.getIdEstadocivil())
                    .idCargo(employeeDto.getIdCargo())
                    .idSucursal(employeeDto.getIdSucursal())
                    .nombrePersona(employeeDto.getNombrePersona())
                    .fechanacimientoPersona(employeeDto.getFechanacimientoPersona())
                    .idTiposangre(employeeDto.getIdTiposangre())
                    .idParroquia(employeeDto.getIdParroquia())
                    .nombreTiposangre(employeeDto.getNombreTiposangre())
                    .nombreNivelestudio(employeeDto.getNombreNivelestudio())
                    .nombreCiudad(employeeDto.getNombreCiudad())
                    .nombreProvincia(employeeDto.getNombreProvincia())
                    .nombreParroquia(employeeDto.getNombreParroquia())
                    .nombreEstadocivil(employeeDto.getNombreEstadocivil())
                    .idGenero(employeeDto.getIdGenero())
                    .nombreGenero(employeeDto.getNombreGenero())
                    .idNivelestudio(employeeDto.getIdNivelestudio())
                    .tituloEmpleado(employeeDto.getTituloEmpleado())
                    .direccionPersona(employeeDto.getDireccionPersona())
                    .sectordomicilioPersona(employeeDto.getSectordomicilioPersona())
                    .correoPersona(employeeDto.getCorreoPersona())
                    .telefonoEmpleado(employeeDto.getTelefonoEmpleado())
                    .celularPersona(employeeDto.getCelularPersona())
                    .nombreTipolicenciaconducir(employeeDto.getNombreTipolicenciaconducir())
                    .descripcionTipolicenciaconducir(employeeDto.getDescripcionTipolicenciaconducir())
                    .idTipolicenciaconducir(employeeDto.getIdTipolicenciaconducir())
                    .fechacaducalicenciaEmpleado(employeeDto.getFechacaducalicenciaEmpleado())
                    .idDiscapacidad(employeeDto.getIdDiscapacidad())
                    .esdiscapacitadoEmpleado(employeeDto.getEsdiscapacitadoEmpleado())
                    .nombreDiscapacidad(employeeDto.getNombreDiscapacidad())
                    .porcdiscapacidadEmpleado(employeeDto.getPorcdiscapacidadEmpleado())
                    .rrhContactoEmergenciaDtos(emergencyContactDtoList)
                    .rrhCargasFamiliaresDtos(familyBurdensDtoList)
                    .tbaCuentaBancariaDtos(tbaCuentaBancariaDtoList)
                    .pathCedulaEmpleado(employeeDto.getPathCedulaEmpleado())
                    .pathLicenciaEmpleado(employeeDto.getPathLicenciaEmpleado())
                    .pathCvEmpleado(employeeDto.getPathCvEmpleado())
                    .imgUsr(employeeDto.getImgUsr())
                    .build());
        }).switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.EMPLOYEE_NOT_FOUND))).onErrorResume(throwable -> {
            return Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.EMPLOYEE_NOT_FOUND));
        });
    }

    /**
     * Método Para actualizar Ficha del Empleado
     *
     * @param employeeId Id del Empleado
     * @return Objeto con los datos del empleado para la ficha
     */

    public Mono<Object> updateEmployeeRecord(Integer employeeId, TbaEmpleadoDto tbaEmpleadoDto) {
        familyBurdenFlux = Flux.empty();
        emergenyContactFlux = Flux.empty();
        bankAccountFlux = Flux.empty();
        Mono<Boolean> existsEmployeeById = tbaEmpleadoRepository.existsById(employeeId);

        Mono<TbaEmpleado> employeeMono = tbaEmpleadoRepository.findById(employeeId);
        Mono<TbaPersona> personMono = tbaPersonaRepository.findById(tbaEmpleadoDto.getIdPersona());


        return existsEmployeeById.flatMap(exists -> {

            if (exists) {
                return Mono.zip(employeeMono, personMono).flatMap(tuple -> {
                    TbaEmpleado employee = tuple.getT1();
                    TbaPersona person = tuple.getT2();

                    person.setNombrePersona(tbaEmpleadoDto.getNombrePersona() != null ? tbaEmpleadoDto.getNombrePersona() : person.getNombrePersona());
                    person.setFechaNacimientoPersona(tbaEmpleadoDto.getFechanacimientoPersona() != null ? tbaEmpleadoDto.getFechanacimientoPersona() : person.getFechaNacimientoPersona());
                    person.setIdGenero(tbaEmpleadoDto.getIdGenero() != null ? tbaEmpleadoDto.getIdGenero() : person.getIdGenero());
                    person.setIdEstadoCivil(tbaEmpleadoDto.getIdEstadocivil() != null ? tbaEmpleadoDto.getIdEstadocivil() : person.getIdEstadoCivil());
                    person.setIdParroquia(tbaEmpleadoDto.getIdParroquia() != null ? tbaEmpleadoDto.getIdParroquia() : person.getIdParroquia());
                    person.setDireccionPersona(tbaEmpleadoDto.getDireccionPersona() != null ? tbaEmpleadoDto.getDireccionPersona() : person.getDireccionPersona());
                    person.setSectorDomicilioPersona(tbaEmpleadoDto.getSectordomicilioPersona() != null ? tbaEmpleadoDto.getSectordomicilioPersona() : person.getSectorDomicilioPersona());
                    person.setCorreoPersona(tbaEmpleadoDto.getCorreoPersona() != null ? tbaEmpleadoDto.getCorreoPersona() : person.getCorreoPersona());
                    person.setCelularPersona(tbaEmpleadoDto.getCelularPersona() != null ? tbaEmpleadoDto.getCelularPersona() : person.getCelularPersona());

                    employee.setIdTipoSangre(tbaEmpleadoDto.getIdTiposangre() != null ? tbaEmpleadoDto.getIdTiposangre() : employee.getIdTipoSangre());
                    employee.setIdSucursal(tbaEmpleadoDto.getIdSucursal() != null ? tbaEmpleadoDto.getIdSucursal() : employee.getIdSucursal());
                    employee.setIdCargo(tbaEmpleadoDto.getIdCargo() != null ? tbaEmpleadoDto.getIdCargo() : employee.getIdCargo());
                    employee.setTelefonoEmpleado(tbaEmpleadoDto.getTelefonoEmpleado() != null ? tbaEmpleadoDto.getTelefonoEmpleado() : employee.getCelularEmpleado());
                    employee.setCelularEmpleado(tbaEmpleadoDto.getCelularEmpleado() != null ? tbaEmpleadoDto.getCelularEmpleado() : employee.getCelularEmpleado());
                    employee.setIdNivelEstudio(tbaEmpleadoDto.getIdNivelestudio() != null ? tbaEmpleadoDto.getIdNivelestudio() : employee.getIdNivelEstudio());
                    employee.setTituloEmpleado(tbaEmpleadoDto.getTituloEmpleado() != null ? tbaEmpleadoDto.getTituloEmpleado() : employee.getTituloEmpleado());
                    employee.setIdTipoLicenciaConducir(tbaEmpleadoDto.getIdTipolicenciaconducir() != null ? tbaEmpleadoDto.getIdTipolicenciaconducir() : employee.getIdTipoLicenciaConducir());
                    employee.setFechaCaducaLicenciaEmpleado(tbaEmpleadoDto.getFechacaducalicenciaEmpleado() != null ? tbaEmpleadoDto.getFechacaducalicenciaEmpleado() : employee.getFechaCaducaLicenciaEmpleado());
                    employee.setIdDiscapacidad(tbaEmpleadoDto.getIdDiscapacidad() != null ? tbaEmpleadoDto.getIdDiscapacidad() : employee.getIdDiscapacidad());
                    employee.setEsDiscapacitadoEmpleado(tbaEmpleadoDto.getEsdiscapacitadoEmpleado() != null ? tbaEmpleadoDto.getEsdiscapacitadoEmpleado() : employee.getEsDiscapacitadoEmpleado());
                    employee.setPorcentajeDiscapacidadEmpleado(tbaEmpleadoDto.getPorcdiscapacidadEmpleado() != null ? tbaEmpleadoDto.getPorcdiscapacidadEmpleado() : employee.getPorcentajeDiscapacidadEmpleado());

                    /* Lista de Cargas Familiares*/
                    if (tbaEmpleadoDto.getRrhCargasFamiliaresDtos() != null && !tbaEmpleadoDto.getRrhCargasFamiliaresDtos().isEmpty()) {
                        List<RrhCargasFamiliaresDto> rrhCargasFamiliaresDtos = tbaEmpleadoDto.getRrhCargasFamiliaresDtos();
                        familyBurdenFlux = rrhCargaFamiliarService.createEmployeeFamilyBurden(rrhCargasFamiliaresDtos, employeeId);
                    }

                    /* Lista de Cuentas Bancarias */
                    if (tbaEmpleadoDto.getTbaCuentaBancariaDtos() != null && !tbaEmpleadoDto.getTbaCuentaBancariaDtos().isEmpty()) {
                        List<TbaCuentaBancariaDto> bankAccountsDtoList = tbaEmpleadoDto.getTbaCuentaBancariaDtos();
                        bankAccountFlux = tbaCuentaBancariaService.createBankAccountEmployee(bankAccountsDtoList, employeeId);
                    }

                    /* Lista de Contactos de Emergencia*/
                    if (tbaEmpleadoDto.getRrhContactoEmergenciaDtos() != null && !tbaEmpleadoDto.getRrhContactoEmergenciaDtos().isEmpty()) {
                        List<RrhContactoEmergenciaDto> emergencyContactsDtoList = tbaEmpleadoDto.getRrhContactoEmergenciaDtos();
                        emergenyContactFlux = rrhContactoEmergenciaService.createEmployeeEmergencyContact(emergencyContactsDtoList, employeeId);
                    }
                    Mono<TbaEmpleado> saveEmployee = tbaEmpleadoRepository.save(employee);
                    Mono<TbaPersona> savePersone = tbaPersonaRepository.save(person);
                    return Mono.zip(familyBurdenFlux.collectList(), saveEmployee, savePersone, bankAccountFlux.collectList(), emergenyContactFlux.collectList());

                });

            } else {
                return Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.EMPLOYEE_NOT_FOUND + employeeId));
            }
        });

    }

    public Mono<TbaInformacionContractualDto> getContractualInformation(Integer employeeId) {
        Mono<TbaInformacionContractualDto> tbaInformacionContractualDtoMono = tbaEmpleadoRepository.getContractualInformation(employeeId);

        Mono<List<RrhhContratosDto>> contractEmployeeList = tbaEmpleadoRepository.getContractEmployee(employeeId).collectList().onErrorResume(throwable -> {
            return Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.EMPLOYEE_NOT_FOUND));
        });

        return Mono.zip(tbaInformacionContractualDtoMono, contractEmployeeList).flatMap(tuple -> {
            TbaInformacionContractualDto tbaInformacionContractualDto = tuple.getT1();
            List<RrhhContratosDto> contractsDtoList = tuple.getT2();

            return Mono.just(TbaInformacionContractualDto.builder().fechaingresoempresaEmpleado(
                            tbaInformacionContractualDto.getFechaingresoempresaEmpleado())
                    .idSucursal(tbaInformacionContractualDto.getIdSucursal())
                    .nombreSucursal(tbaInformacionContractualDto.getNombreSucursal())
                    .salarioEmpleado(tbaInformacionContractualDto.getSalarioEmpleado())
                    .idDepartamento(tbaInformacionContractualDto.getIdDepartamento())
                    .nombreDepartamento(tbaInformacionContractualDto.getNombreDepartamento())
                    .idCargo(tbaInformacionContractualDto.getIdCargo())
                    .nombreCargo(tbaInformacionContractualDto.getNombreCargo())
                    .provisionvacacionesEmpleado(tbaInformacionContractualDto.getProvisionvacacionesEmpleado())
                    .valorquincenaEmpleado(tbaInformacionContractualDto.getValorquincenaEmpleado())
                    .quincenaEmpleado(tbaInformacionContractualDto.getQuincenaEmpleado())
                    .iesspersonalEmpleado(tbaInformacionContractualDto.getIesspersonalEmpleado())
                    .iesspatronalEmpleado(tbaInformacionContractualDto.getIesspatronalEmpleado())
                    .fracumuladosEmpleado(tbaInformacionContractualDto.getFracumuladosEmpleado())
                    .decimo3acumuladoEmpleado(tbaInformacionContractualDto.getDecimo3acumuladoEmpleado())
                    .decimo4acumuladoEmpleado(tbaInformacionContractualDto.getDecimo4acumuladoEmpleado())
                    .impuestorentaEmpleado(tbaInformacionContractualDto.getImpuestorentaEmpleado())
                    .fechasalidaEmpleado(tbaInformacionContractualDto.getFechasalidaEmpleado())
                    .motivosalidaEmpleado(tbaInformacionContractualDto.getMotivosalidaEmpleado())
                    .fechaingresoempresaEmpleado(tbaInformacionContractualDto.getFechaingresoempresaEmpleado())
                    .fechareingresoEmpleado(tbaInformacionContractualDto.getFechareingresoEmpleado())
                    .motivoreingresoEmpleado(tbaInformacionContractualDto.getMotivoreingresoEmpleado())
                    .rrhhContratosDtos(contractsDtoList)
                    .build());
        }).switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.EMPLOYEE_NOT_FOUND))).onErrorResume(throwable -> {
            return Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.EMPLOYEE_NOT_FOUND));
        });
    }


    public Mono<Object> updateContractualInformation(Integer employeeId, TbaInformacionContractualDto tbaInformacionContractualDto) {
        Mono<Boolean> isEmployeeExistsById = tbaEmpleadoRepository.existsById(employeeId);

        Mono<TbaEmpleado> employeeMono = tbaEmpleadoRepository.findById(employeeId);
        Flux<ConEmpleadoCuenta> employeeAccountFlux = conEmpleadoCuentaService.findByIdEmployee(employeeId);

        Mono<List<RrhContratoLaboral>> workContractListMono = rrhContratoLaboralRepository.findByIdEmpleado(employeeId).collectList();
        Mono<TbaDepartamento> departmentMono = serviceDepartamento.getDepartmentById(tbaInformacionContractualDto.getIdDepartamento());
        return isEmployeeExistsById.flatMap(exist -> {

            if (exist) {
                return Mono.zip(employeeMono, departmentMono, workContractListMono, employeeAccountFlux.collectList()).publishOn(Schedulers.boundedElastic()).flatMap(tuple -> {
                    TbaEmpleado employee = tuple.getT1();

                    if (tbaInformacionContractualDto.getIdCargo() != employee.getIdCargo()) {
                        TbaDepartamento department = tuple.getT2();
                        ConEmpleadoCuentaDto employeeAccount = new ConEmpleadoCuentaDto();
                        employeeAccount.setIdEmpleadoCuenta(tuple.getT4().get(0).getIdEmpleadoCuenta());
                        employeeAccount.setIdEmpleado(employeeId);
                        employeeAccount.setIdCuenta(tuple.getT4().get(0).getIdCuenta());
                        employeeAcountMono = conEmpleadoCuentaService.update(tuple.getT4(), department.getIdDepartamento());
                    }

                    employee.setIdSucursal(tbaInformacionContractualDto.getIdSucursal() != null ? tbaInformacionContractualDto.getIdSucursal() : employee.getIdSucursal());
                    employee.setSalarioEmpleado(tbaInformacionContractualDto.getSalarioEmpleado() != null ? tbaInformacionContractualDto.getSalarioEmpleado() : employee.getSalarioEmpleado());
                    employee.setIdCargo(tbaInformacionContractualDto.getIdCargo() != null ? tbaInformacionContractualDto.getIdCargo() : employee.getIdEmpleado());
                    employee.setQuincenaEmpleado(tbaInformacionContractualDto.getQuincenaEmpleado() != null ? tbaInformacionContractualDto.getQuincenaEmpleado() : employee.getQuincenaEmpleado());
                    employee.setIessPersonalEmpleado(tbaInformacionContractualDto.getIesspersonalEmpleado() != null ? tbaInformacionContractualDto.getIesspersonalEmpleado() : employee.getIessPersonalEmpleado());
                    employee.setIessPatronalEmpleado(tbaInformacionContractualDto.getIesspatronalEmpleado() != null ? tbaInformacionContractualDto.getIesspatronalEmpleado() : employee.getIessPatronalEmpleado());
                    employee.setDecimoTerceroAcumuladoEmpleado(tbaInformacionContractualDto.getDecimo3acumuladoEmpleado() != null ? tbaInformacionContractualDto.getDecimo3acumuladoEmpleado() : employee.getDecimoTerceroAcumuladoEmpleado());
                    employee.setDecimoCuartoAcumuladoEmpleado(tbaInformacionContractualDto.getDecimo4acumuladoEmpleado() != null ? tbaInformacionContractualDto.getDecimo4acumuladoEmpleado() : employee.getDecimoCuartoAcumuladoEmpleado());
                    employee.setFraccionalesAcumuladosEmpleado(tbaInformacionContractualDto.getFracumuladosEmpleado() != null ? tbaInformacionContractualDto.getFracumuladosEmpleado() : employee.getFraccionalesAcumuladosEmpleado());
                    employee.setValorQuincenaEmpleado(tbaInformacionContractualDto.getValorquincenaEmpleado() != null ? tbaInformacionContractualDto.getValorquincenaEmpleado() : employee.getValorQuincenaEmpleado());
                    employee.setImpuestoRentaEmpleado(tbaInformacionContractualDto.getImpuestorentaEmpleado() != null ? tbaInformacionContractualDto.getImpuestorentaEmpleado() : employee.getImpuestoRentaEmpleado());
                    if (tbaInformacionContractualDto.getFechasalidaEmpleado() != null) {
                        employee.setProvisionVacacionesEmpleado(Boolean.TRUE);
                    } else {
                        employee.setProvisionVacacionesEmpleado(tbaInformacionContractualDto.getProvisionvacacionesEmpleado() != null ? tbaInformacionContractualDto.getProvisionvacacionesEmpleado() : employee.getProvisionVacacionesEmpleado());
                    }
                    if (tbaInformacionContractualDto.getRrhhContratosDtos() != null && !tbaInformacionContractualDto.getRrhhContratosDtos().isEmpty()) {
                        List<RrhhContratosDto> contractsDtoList = tbaInformacionContractualDto.getRrhhContratosDtos();
                        workContractFlux = rrhContractoLaboralService.updateWorkContract(contractsDtoList, employeeId);
                    }
                    Mono<TbaEmpleado> saveEmployeContract = tbaEmpleadoRepository.save(employee);
                    return Mono.zip(workContractFlux.collectList(), saveEmployeContract, employeeAcountMono);
                });
            } else {
                return Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.EMPLOYEE_NOT_FOUND + employeeId));
            }
        });
    }


    public Flux<ConCuentaDatosContablesDto> getAccountingDataEmployee(Integer id) {
        return conCuentaRepository.getDatosContables(id);
    }

    /* Métodos para crear por partes al empleado*/

    public Mono<TbaEmpleado> createEmployee(TbaEmpleadoDto tbaEmpleadoDto, Integer personId, TbaInformacionContractualDto tbaInformacionContractualDto) {
        return tbaEmpleadoRepository.save(
                TbaEmpleado.builder()
                        .idPersona(personId)
                        .idTipoSangre(tbaEmpleadoDto.getIdTiposangre())
                        .idTipoLicenciaConducir(tbaEmpleadoDto.getIdTipolicenciaconducir())
                        .idCargo(tbaInformacionContractualDto.getIdCargo())
                        .idSucursal(tbaInformacionContractualDto.getIdSucursal())
                        .esDiscapacitadoEmpleado(tbaEmpleadoDto.getEsdiscapacitadoEmpleado())
                        .idDiscapacidad(tbaEmpleadoDto.getIdDiscapacidad())
                        .fechaCaducaLicenciaEmpleado(tbaEmpleadoDto.getFechacaducalicenciaEmpleado())
                        .fechaIngresoEmpresaEmpleado(LocalDate.from(currentDate))
                        .idNivelEstudio(tbaEmpleadoDto.getIdNivelestudio())
                        .porcentajeDiscapacidadEmpleado(tbaEmpleadoDto.getEsdiscapacitadoEmpleado() != Boolean.FALSE ? tbaEmpleadoDto.getPorcdiscapacidadEmpleado() : BigDecimal.valueOf(0))
                        .iessPersonalEmpleado(Boolean.FALSE)
                        .iessPatronalEmpleado(Boolean.FALSE)
                        .provisionVacacionesEmpleado(Boolean.FALSE)
                        .decimoTerceroAcumuladoEmpleado(Boolean.TRUE)
                        .decimoCuartoAcumuladoEmpleado(Boolean.TRUE)
                        .quincenaEmpleado(tbaInformacionContractualDto.getQuincenaEmpleado())
                        .salarioEmpleado(tbaInformacionContractualDto.getSalarioEmpleado())
                        .horaOrdinariaEmpleado(Utils.calcularCostoHora(tbaInformacionContractualDto.getSalarioEmpleado(), tbaInformacionContractualDto.getRrhhContratosDtos().get(0).equals(ContractModalityEnum.INTERNSHIP) ? BigDecimal.valueOf(6) : BigDecimal.valueOf(8)))
                        .tituloEmpleado(tbaEmpleadoDto.getTituloEmpleado())
                        .impuestoRentaAnualEmpleado(BigDecimal.ZERO)
                        .impuestoRentaEmpleado(BigDecimal.ZERO)
                        .impuestoRentaRebajaEmpleado(BigDecimal.ZERO)
                        .build()
        );

    }

    public Mono<TbaPersona> createPerson(TbaEmpleadoDto tbaEmpleadoDto) {
        TbaPersonaDto tbaPersonaDto = new TbaPersonaDto();

        // Preguntar el tipo de contribuyente que mismo pex
        tbaPersonaDto.setIdTipoContribuyente("02");
        tbaPersonaDto.setIdParroquia(tbaEmpleadoDto.getIdParroquia());
        // Mismo queda con 593 obligatorìo
        tbaPersonaDto.setIdPais(593);
        tbaPersonaDto.setIdEstadoCivil(tbaEmpleadoDto.getIdEstadocivil());
        tbaPersonaDto.setIdGenero(tbaEmpleadoDto.getIdGenero());
        tbaPersonaDto.setIdIdentificacionFiscal(2);
        tbaPersonaDto.setIdentificacionPersona(tbaEmpleadoDto.getIdentificacionPersona());
        tbaPersonaDto.setNombrePersona(tbaEmpleadoDto.getNombrePersona());
        tbaPersonaDto.setFechaNacimientoPersona(tbaEmpleadoDto.getFechanacimientoPersona());
        tbaPersonaDto.setDireccionPersona(tbaEmpleadoDto.getDireccionPersona());
        tbaPersonaDto.setCelularPersona(tbaEmpleadoDto.getCelularPersona());
        tbaPersonaDto.setTelefonoPersona(tbaEmpleadoDto.getTelefonoEmpleado());
        tbaPersonaDto.setCorreoPersona(tbaEmpleadoDto.getCorreoPersona());
        tbaPersonaDto.setEsClientePersona(Boolean.FALSE);
        tbaPersonaDto.setEsEmpleadoPersona(Boolean.TRUE);
        tbaPersonaDto.setEsProveedorPersona(Boolean.FALSE);
        tbaPersonaDto.setFechaRegistroPersona(currentDate);
        return tbaPersonaService.createPerson(tbaPersonaDto);
    }

    public Mono<List<EmpleadoPersonaDto>> getEmployeeActive() {
        Flux<EmpleadoPersonaDto> tbaEmpleadoActivosDtoFlux = tbaEmpleadoRepository.getAllByEstadoEmpleado(StateEnum.ACTIVATED.getIdentification());
        Mono<List<EmpleadoPersonaDto>> activeEmployeesDtoListMono = tbaEmpleadoActivosDtoFlux.collectList();
        return activeEmployeesDtoListMono.flatMap(activeEmployeesDatoList -> {

            activeEmployeesDatoList.forEach(activeEmployeeDto -> {
                activeEmployeeDto.setMetodoPago(Boolean.TRUE.equals(activeEmployeeDto.getEstadoCuentabancaria()) ? "TRANSFERENCIA" : "CHEQUE");
            });
            return Mono.just(activeEmployeesDatoList);
        });

    }

    public Flux<TbaEmpleadoFichaDto> getEmployeeActiveByParam(String paramSearch) {
        String identification = null;
        String completeName = null;
        try {
            if (Utils.validateNumbers(paramSearch)) {
                identification = paramSearch;
            } else {
                completeName = paramSearch;
            }
        } catch (Exception e) {
            try {
                throw new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.NULL_POINTER);
            } catch (CustomException ex) {
                throw new RuntimeException(ex);
            }
        }
        Flux<TbaEmpleadoFichaDto> tbaEmpleadoDtoMono = tbaEmpleadoRepository.findEmpleadoByParams(completeName, identification)
                .filter(TbaEmpleadoFichaDto::getEstado);
        return tbaEmpleadoDtoMono.switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.EMPLOYEE_NOT_FOUND)));
    }

    public Mono<EmpleadoPersonaDto> getEmpleadoPersonaById(Integer id) {
        return tbaEmpleadoRepository.findById(id).flatMap(this::buildEmpleadoPersonaDto);
    }

    public Mono<EmpleadoPersonaDto> buildEmpleadoPersonaDto(TbaEmpleado empleado) {
        Mono<TbaPersona> personaMono = tbaPersonaRepository.findById(empleado.getIdPersona());
        Mono<Sucursal> sucursalMono = tbaSucursalRepository.findById(empleado.getIdSucursal());
        return Mono.zip(personaMono, sucursalMono)
                .map(tuple -> {
                    TbaPersona persona = tuple.getT1();
                    Sucursal sucursal = tuple.getT2();
                    return EmpleadoPersonaDto.builder()
                            .idEmpleado(empleado.getIdEmpleado())
                            .idPersona(empleado.getIdPersona())
                            .idSucursal(empleado.getIdSucursal())
                            .nombreSucursal(sucursal.getNombreSucursal())
                            .nombrePersona(persona.getNombrePersona())
                            .identificacionPersona(persona.getIdentificacionPersona())
                            .quincenaEmpleado(empleado.getQuincenaEmpleado())
                            .valorquincenaEmpleado(empleado.getValorQuincenaEmpleado())
                            .estadoEmpleado(empleado.getEstadoEmpleado())
                            .build();
                });
    }

}
