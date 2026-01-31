package erp.base.api.service

import erp.base.api.constants.ErrorMessage
import erp.base.api.dto.CargasFamiliaresDto
import erp.base.api.dto.ContactoEmergenciaDto
import erp.base.api.dto.CuentaBancariaDto
import erp.base.api.dto.CuentaDatosContablesDto
import erp.base.api.dto.EmpleadoDto
import erp.base.api.dto.EmpleadoFichaDto
import erp.base.api.dto.EmpleadoPersonaDto
import erp.base.api.dto.InformacionContractualDto
import erp.base.api.dto.PersonaDto
import erp.base.api.dto.RrhhContratosDto
import erp.base.api.enums.ContractModalityEnum
import erp.base.api.enums.StateEnum
import erp.base.api.exception.CustomException
import erp.base.api.models.Departamento
import erp.base.api.models.Empleado
import erp.base.api.models.EmpleadoCuenta
import erp.base.api.models.Persona
import erp.base.api.models.Sucursal
import erp.base.api.models.Usuario
import erp.base.api.models.rrh.CargaFamiliar
import erp.base.api.models.rrh.ContactoEmergencia
import erp.base.api.models.rrh.ContratoLaboral
import erp.base.api.models.rrh.CuentaBancaria
import erp.base.api.repository.cont.CuentaRepository
import erp.base.api.repository.EmpleadoRepository
import erp.base.api.repository.PersonaRepository
import erp.base.api.repository.rrh.ContratoLaboralRepository
import erp.base.api.repository.SucursalRepository
import erp.base.api.utils.Utils
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class EmpleadoService(
    private val tbaEmpleadoRepository: EmpleadoRepository,
    private val tbaPersonaRepository: PersonaRepository,
    private val rrhContratoLaboralRepository: ContratoLaboralRepository,
    private val conCuentaRepository: CuentaRepository,
    private val tbaSucursalRepository: SucursalRepository,
    private val tbaPersonaService: PersonaService,
    private val rrhCargaFamiliarService: CargaFamiliarService,
    private val rrhContactoEmergenciaService: ContactoEmergenciaService,
    private val rrhContractoLaboralService: ContractoLaboralService,
    private val tbaCuentaBancariaService: CuentaBancariaService,
    private val conEmpleadoCuentaService: EmpleadoCuentaService,
    private val serviceDepartamento: DepartamentoService,
    private val tbaUsuarioService: UsuarioService,
    private val conMultidimensionService: MultidimensionService
) {
    private val currentDate = LocalDateTime.now()

    fun createEmployeePerson(
        tbaEmpleadoDto: EmpleadoDto,
        informacionContractualDto: InformacionContractualDto
    ): Mono<Any> {
        val employeeExistsByIdentification = tbaPersonaRepository.findByIdentificacionPersona(tbaEmpleadoDto.identificacionPersona!!).hasElement()
        return employeeExistsByIdentification.publishOn(Schedulers.boundedElastic()).flatMap { existsEmployeePerson ->
            if (existsEmployeePerson) {
                val personMono = tbaPersonaRepository.findByIdentificacionPersona(tbaEmpleadoDto.identificacionPersona!!)
                personMono.flatMap { tbaPersona ->
                    val existsEmployee = tbaEmpleadoRepository.existsByIdPersona(tbaPersona.idPersona!!)
                    existsEmployee.publishOn(Schedulers.boundedElastic()).flatMap { exists ->
                        if (exists) {
                            Mono.error<Any>(CustomException(HttpStatus.CONFLICT, ErrorMessage.EMPLOYEE_FOUND))
                        } else {
                            fillEmployeeData(tbaPersona, tbaEmpleadoDto, informacionContractualDto)
                        }
                    }
                }
            } else {
                createPerson(tbaEmpleadoDto).flatMap { fillEmployeeData(it, tbaEmpleadoDto, informacionContractualDto) }
            }
        }
    }

    fun fillEmployeeData(
        person: Persona,
        tbaEmpleadoDto: EmpleadoDto,
        informacionContractualDto: InformacionContractualDto
    ): Mono<Any> {
        val personId = person.idPersona!!
        return Mono.defer {
            createEmployee(tbaEmpleadoDto, personId, informacionContractualDto)
            .publishOn(Schedulers.boundedElastic())
            .flatMap { employee ->
                val employeeId = employee.idEmpleado!!
                var familyBurdenFlux: Flux<CargaFamiliar> = Flux.empty()
                var emergenyContactFlux: Flux<ContactoEmergencia> = Flux.empty()
                var workContractFlux: Flux<ContratoLaboral> = Flux.empty()
                var bankAccountFlux: Flux<CuentaBancaria> = Flux.empty()
                if (!tbaEmpleadoDto.rrhCargasFamiliaresDtos.isNullOrEmpty()) {
                    familyBurdenFlux = rrhCargaFamiliarService.createEmployeeFamilyBurden(tbaEmpleadoDto.rrhCargasFamiliaresDtos!!, employeeId)
                }
                if (!tbaEmpleadoDto.rrhContactoEmergenciaDtos.isNullOrEmpty()) {
                    emergenyContactFlux = rrhContactoEmergenciaService.createEmployeeEmergencyContact(tbaEmpleadoDto.rrhContactoEmergenciaDtos!!, employeeId)
                }
                if (!informacionContractualDto.rrhhContratosDtos.isNullOrEmpty()) {
                    workContractFlux = rrhContractoLaboralService.createWorkContract(informacionContractualDto.rrhhContratosDtos!!, employeeId, currentDate)
                }
                if (!tbaEmpleadoDto.tbaCuentaBancariaDtos.isNullOrEmpty()) {
                    bankAccountFlux = tbaCuentaBancariaService.createBankAccountEmployee(tbaEmpleadoDto.tbaCuentaBancariaDtos!!, employeeId)
                }
                val employeeAccountFlux = serviceDepartamento.getDepartmentById(informacionContractualDto.idDepartamento!!)
                    .flatMapMany { conEmpleadoCuentaService.createEmployeeAccount(it.idDepartamento!!, it.idAreaGrupo!!, employeeId) }
                val existsPersonMono = tbaUsuarioService.validateByIdPersona(person.idPersona!!)
                val multidimensionMono = conMultidimensionService.createMultidimensionEmployee(employeeId)
                Mono.zip(
                    Mono.just(employee),
                    familyBurdenFlux.collectList(),
                    emergenyContactFlux.collectList(),
                    workContractFlux.collectList(),
                    bankAccountFlux.collectList(),
                    employeeAccountFlux.collectList(),
                    multidimensionMono,
                    existsPersonMono
                ).publishOn(Schedulers.boundedElastic()).flatMap { tuple ->
                    var userMono: Mono<Usuario> = Mono.empty()
                    val existsPerson = tuple.t8
                    if (!existsPerson) {
                        var i = 0
                        var existsEmployeeNickName = true
                        var nickName = ""
                        do {
                            nickName = tbaUsuarioService.createNickName(person.nombrePersona!!, existsEmployeeNickName, i)
                            existsEmployeeNickName = tbaUsuarioService.validateNickName(nickName).block()!!
                            i++
                        } while (existsEmployeeNickName)
                        try {
                            userMono = tbaUsuarioService.createUser(employee, nickName, person.idPersona!!, informacionContractualDto.idCargo!!)
                        } catch (e: Exception) {
                            return@flatMap Mono.error<Any>(CustomException(HttpStatus.BAD_REQUEST, e.message!!))
                        }
                    }
                    Mono.zip(Mono.just(person), Mono.just(employee), userMono)
                }
            }
        }
    }

    fun getEmployee(completeName: String?, identification: String?): Flux<EmpleadoFichaDto> {
        val tbaEmpleadoDtoMono = tbaEmpleadoRepository.findEmpleadoByParams(completeName, identification)
        return tbaEmpleadoDtoMono.switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.EMPLOYEE_NOT_FOUND)))
    }

    fun getEmployeeRecord(id: Int): Mono<EmpleadoDto> {
        val tbaEmpleadoServiceMono = tbaEmpleadoRepository.getFichaEmpleado(id)
        val familyBurdensDtoMono = tbaEmpleadoRepository.getCargasFamiliares(id).collectList()
            .onErrorResume { Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.EMPLOYEE_NOT_FOUND)) }
        val emergenyContactDtoMono = tbaEmpleadoRepository.getEmergencyContact(id).collectList()
            .onErrorResume { Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.EMPLOYEE_NOT_FOUND)) }
        val bankAccountDtoMono = tbaEmpleadoRepository.getBancaryCount(id).collectList()
            .onErrorResume { Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.EMPLOYEE_NOT_FOUND)) }
        return Mono.zip(familyBurdensDtoMono, emergenyContactDtoMono, tbaEmpleadoServiceMono, bankAccountDtoMono).flatMap { tuple ->
            val familyBurdensDtoList = tuple.t1
            val emergencyContactDtoList = tuple.t2
            val employeeDto = tuple.t3
            val tbaCuentaBancariaDtoList = tuple.t4
            for (familyBurdenDto in familyBurdensDtoList) {
                try {
                    familyBurdenDto.edad = Utils.calcularEdad(familyBurdenDto.fechanacimientoCargafml!!)
                } catch (e: Exception) {
                    return@flatMap Mono.error<EmpleadoDto>(CustomException(HttpStatus.BAD_REQUEST, ErrorMessage.ERROR_DATE))
                }
            }
            val dto = EmpleadoDto().apply {
                idPersona = employeeDto.idPersona
                idEmpleado = employeeDto.idEmpleado
                identificacionPersona = employeeDto.identificacionPersona
                idEstadocivil = employeeDto.idEstadocivil
                idCargo = employeeDto.idCargo
                idSucursal = employeeDto.idSucursal
                nombrePersona = employeeDto.nombrePersona
                fechanacimientoPersona = employeeDto.fechanacimientoPersona
                idTiposangre = employeeDto.idTiposangre
                idParroquia = employeeDto.idParroquia
                nombreTiposangre = employeeDto.nombreTiposangre
                nombreNivelestudio = employeeDto.nombreNivelestudio
                nombreCiudad = employeeDto.nombreCiudad
                nombreProvincia = employeeDto.nombreProvincia
                nombreParroquia = employeeDto.nombreParroquia
                nombreEstadocivil = employeeDto.nombreEstadocivil
                idGenero = employeeDto.idGenero
                nombreGenero = employeeDto.nombreGenero
                idNivelestudio = employeeDto.idNivelestudio
                tituloEmpleado = employeeDto.tituloEmpleado
                direccionPersona = employeeDto.direccionPersona
                sectordomicilioPersona = employeeDto.sectordomicilioPersona
                correoPersona = employeeDto.correoPersona
                telefonoEmpleado = employeeDto.telefonoEmpleado
                celularPersona = employeeDto.celularPersona
                nombreTipolicenciaconducir = employeeDto.nombreTipolicenciaconducir
                descripcionTipolicenciaconducir = employeeDto.descripcionTipolicenciaconducir
                idTipolicenciaconducir = employeeDto.idTipolicenciaconducir
                fechacaducalicenciaEmpleado = employeeDto.fechacaducalicenciaEmpleado
                idDiscapacidad = employeeDto.idDiscapacidad
                esdiscapacitadoEmpleado = employeeDto.esdiscapacitadoEmpleado
                nombreDiscapacidad = employeeDto.nombreDiscapacidad
                porcdiscapacidadEmpleado = employeeDto.porcdiscapacidadEmpleado
                rrhContactoEmergenciaDtos = emergencyContactDtoList
                rrhCargasFamiliaresDtos = familyBurdensDtoList
                tbaCuentaBancariaDtos = tbaCuentaBancariaDtoList
                pathCedulaEmpleado = employeeDto.pathCedulaEmpleado
                pathLicenciaEmpleado = employeeDto.pathLicenciaEmpleado
                pathCvEmpleado = employeeDto.pathCvEmpleado
                imgUsr = employeeDto.imgUsr
            }
            Mono.just(dto)
        }.switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.EMPLOYEE_NOT_FOUND)))
            .onErrorResume { Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.EMPLOYEE_NOT_FOUND)) }
    }

    fun updateEmployeeRecord(employeeId: Int, tbaEmpleadoDto: EmpleadoDto): Mono<Any> {
        var familyBurdenFlux: Flux<CargaFamiliar> = Flux.empty()
        var emergenyContactFlux: Flux<ContactoEmergencia> = Flux.empty()
        var bankAccountFlux: Flux<CuentaBancaria> = Flux.empty()
        val existsEmployeeById = tbaEmpleadoRepository.existsById(employeeId)
        val employeeMono = tbaEmpleadoRepository.findById(employeeId)
        val personMono = tbaPersonaRepository.findById(tbaEmpleadoDto.idPersona!!)
        return existsEmployeeById.flatMap { exists ->
            if (exists) {
                Mono.zip(employeeMono, personMono).flatMap { tuple ->
                    val employee = tuple.t1
                    val person = tuple.t2
                    person.nombrePersona = tbaEmpleadoDto.nombrePersona ?: person.nombrePersona
                    person.fechaNacimientoPersona = tbaEmpleadoDto.fechanacimientoPersona ?: person.fechaNacimientoPersona
                    person.idGenero = tbaEmpleadoDto.idGenero ?: person.idGenero
                    person.idEstadoCivil = tbaEmpleadoDto.idEstadocivil ?: person.idEstadoCivil
                    person.idParroquia = tbaEmpleadoDto.idParroquia ?: person.idParroquia
                    person.direccionPersona = tbaEmpleadoDto.direccionPersona ?: person.direccionPersona
                    person.sectorDomicilioPersona = tbaEmpleadoDto.sectordomicilioPersona ?: person.sectorDomicilioPersona
                    person.correoPersona = tbaEmpleadoDto.correoPersona ?: person.correoPersona
                    person.celularPersona = tbaEmpleadoDto.celularPersona ?: person.celularPersona
                    employee.idTipoSangre = tbaEmpleadoDto.idTiposangre ?: employee.idTipoSangre
                    employee.idSucursal = tbaEmpleadoDto.idSucursal ?: employee.idSucursal
                    employee.idCargo = tbaEmpleadoDto.idCargo ?: employee.idCargo
                    employee.telefonoEmpleado = tbaEmpleadoDto.telefonoEmpleado ?: employee.celularEmpleado
                    employee.celularEmpleado = tbaEmpleadoDto.celularEmpleado ?: employee.celularEmpleado
                    employee.idNivelEstudio = tbaEmpleadoDto.idNivelestudio ?: employee.idNivelEstudio
                    employee.tituloEmpleado = tbaEmpleadoDto.tituloEmpleado ?: employee.tituloEmpleado
                    employee.idTipoLicenciaConducir = tbaEmpleadoDto.idTipolicenciaconducir ?: employee.idTipoLicenciaConducir
                    employee.fechaCaducaLicenciaEmpleado = tbaEmpleadoDto.fechacaducalicenciaEmpleado ?: employee.fechaCaducaLicenciaEmpleado
                    employee.idDiscapacidad = tbaEmpleadoDto.idDiscapacidad ?: employee.idDiscapacidad
                    employee.esDiscapacitadoEmpleado = tbaEmpleadoDto.esdiscapacitadoEmpleado ?: employee.esDiscapacitadoEmpleado
                    employee.porcentajeDiscapacidadEmpleado = tbaEmpleadoDto.porcdiscapacidadEmpleado ?: employee.porcentajeDiscapacidadEmpleado
                    if (!tbaEmpleadoDto.rrhCargasFamiliaresDtos.isNullOrEmpty()) {
                        familyBurdenFlux = rrhCargaFamiliarService.createEmployeeFamilyBurden(tbaEmpleadoDto.rrhCargasFamiliaresDtos!!, employeeId)
                    }
                    if (!tbaEmpleadoDto.tbaCuentaBancariaDtos.isNullOrEmpty()) {
                        bankAccountFlux = tbaCuentaBancariaService.createBankAccountEmployee(tbaEmpleadoDto.tbaCuentaBancariaDtos!!, employeeId)
                    }
                    if (!tbaEmpleadoDto.rrhContactoEmergenciaDtos.isNullOrEmpty()) {
                        emergenyContactFlux = rrhContactoEmergenciaService.createEmployeeEmergencyContact(tbaEmpleadoDto.rrhContactoEmergenciaDtos!!, employeeId)
                    }
                    val saveEmployee = tbaEmpleadoRepository.save(employee)
                    val savePersone = tbaPersonaRepository.save(person)
                    Mono.zip(familyBurdenFlux.collectList(), saveEmployee, savePersone, bankAccountFlux.collectList(), emergenyContactFlux.collectList())
                }
            } else {
                Mono.error<Any>(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.EMPLOYEE_NOT_FOUND + employeeId))
            }
        }
    }

    fun getContractualInformation(employeeId: Int): Mono<InformacionContractualDto> {
        val tbaInformacionContractualDtoMono = tbaEmpleadoRepository.getContractualInformation(employeeId)
        val contractEmployeeList = tbaEmpleadoRepository.getContractEmployee(employeeId).collectList()
            .onErrorResume { Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.EMPLOYEE_NOT_FOUND)) }
        return Mono.zip(tbaInformacionContractualDtoMono, contractEmployeeList).flatMap { tuple ->
            val tbaInformacionContractualDto = tuple.t1
            val contractsDtoList = tuple.t2
            val dto = InformacionContractualDto().apply {
                fechaingresoempresaEmpleado = tbaInformacionContractualDto.fechaingresoempresaEmpleado
                idSucursal = tbaInformacionContractualDto.idSucursal
                nombreSucursal = tbaInformacionContractualDto.nombreSucursal
                salarioEmpleado = tbaInformacionContractualDto.salarioEmpleado
                idDepartamento = tbaInformacionContractualDto.idDepartamento
                nombreDepartamento = tbaInformacionContractualDto.nombreDepartamento
                idCargo = tbaInformacionContractualDto.idCargo
                nombreCargo = tbaInformacionContractualDto.nombreCargo
                provisionvacacionesEmpleado = tbaInformacionContractualDto.provisionvacacionesEmpleado
                valorquincenaEmpleado = tbaInformacionContractualDto.valorquincenaEmpleado
                quincenaEmpleado = tbaInformacionContractualDto.quincenaEmpleado
                iesspersonalEmpleado = tbaInformacionContractualDto.iesspersonalEmpleado
                iesspatronalEmpleado = tbaInformacionContractualDto.iesspatronalEmpleado
                fracumuladosEmpleado = tbaInformacionContractualDto.fracumuladosEmpleado
                decimo3acumuladoEmpleado = tbaInformacionContractualDto.decimo3acumuladoEmpleado
                decimo4acumuladoEmpleado = tbaInformacionContractualDto.decimo4acumuladoEmpleado
                impuestorentaEmpleado = tbaInformacionContractualDto.impuestorentaEmpleado
                fechasalidaEmpleado = tbaInformacionContractualDto.fechasalidaEmpleado
                motivosalidaEmpleado = tbaInformacionContractualDto.motivosalidaEmpleado
                fechareingresoEmpleado = tbaInformacionContractualDto.fechareingresoEmpleado
                motivoreingresoEmpleado = tbaInformacionContractualDto.motivoreingresoEmpleado
                rrhhContratosDtos = contractsDtoList
            }
            Mono.just(dto)
        }.switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.EMPLOYEE_NOT_FOUND)))
            .onErrorResume { Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.EMPLOYEE_NOT_FOUND)) }
    }

    fun updateContractualInformation(employeeId: Int, tbaInformacionContractualDto: InformacionContractualDto): Mono<Any> {
        val isEmployeeExistsById = tbaEmpleadoRepository.existsById(employeeId)
        val employeeMono = tbaEmpleadoRepository.findById(employeeId)
        val employeeAccountFlux = conEmpleadoCuentaService.findByIdEmployee(employeeId)
        val workContractListMono = rrhContratoLaboralRepository.findByIdEmpleado(employeeId).collectList()
        val departmentMono = serviceDepartamento.getDepartmentById(tbaInformacionContractualDto.idDepartamento!!)
        return isEmployeeExistsById.flatMap { exist ->
            if (exist) {
                Mono.zip(employeeMono, departmentMono, workContractListMono, employeeAccountFlux.collectList())
                    .publishOn(Schedulers.boundedElastic()).flatMap { tuple ->
                        val employee = tuple.t1
                        var employeeAcountMono: Mono<EmpleadoCuenta?> = Mono.just(null)
                        if (tbaInformacionContractualDto.idCargo != employee.idCargo) {
                            val department = tuple.t2
                            employeeAcountMono = conEmpleadoCuentaService.update(tuple.t4, department.idDepartamento!!).map { it }
                        }
                        employee.idSucursal = tbaInformacionContractualDto.idSucursal ?: employee.idSucursal
                        employee.salarioEmpleado = tbaInformacionContractualDto.salarioEmpleado ?: employee.salarioEmpleado
                        employee.idCargo = tbaInformacionContractualDto.idCargo ?: employee.idEmpleado
                        employee.quincenaEmpleado = tbaInformacionContractualDto.quincenaEmpleado ?: employee.quincenaEmpleado
                        employee.iessPersonalEmpleado = tbaInformacionContractualDto.iesspersonalEmpleado ?: employee.iessPersonalEmpleado
                        employee.iessPatronalEmpleado = tbaInformacionContractualDto.iesspatronalEmpleado ?: employee.iessPatronalEmpleado
                        employee.decimoTerceroAcumuladoEmpleado = tbaInformacionContractualDto.decimo3acumuladoEmpleado ?: employee.decimoTerceroAcumuladoEmpleado
                        employee.decimoCuartoAcumuladoEmpleado = tbaInformacionContractualDto.decimo4acumuladoEmpleado ?: employee.decimoCuartoAcumuladoEmpleado
                        employee.fraccionalesAcumuladosEmpleado = tbaInformacionContractualDto.fracumuladosEmpleado ?: employee.fraccionalesAcumuladosEmpleado
                        employee.valorQuincenaEmpleado = tbaInformacionContractualDto.valorquincenaEmpleado ?: employee.valorQuincenaEmpleado
                        employee.impuestoRentaEmpleado = tbaInformacionContractualDto.impuestorentaEmpleado ?: employee.impuestoRentaEmpleado
                        if (tbaInformacionContractualDto.fechasalidaEmpleado != null) {
                            employee.provisionVacacionesEmpleado = true
                        } else {
                            employee.provisionVacacionesEmpleado = tbaInformacionContractualDto.provisionvacacionesEmpleado ?: employee.provisionVacacionesEmpleado
                        }
                        var workContractFlux: Flux<ContratoLaboral> = Flux.empty()
                        if (!tbaInformacionContractualDto.rrhhContratosDtos.isNullOrEmpty()) {
                            workContractFlux = rrhContractoLaboralService.updateWorkContract(tbaInformacionContractualDto.rrhhContratosDtos!!, employeeId)
                        }
                        val saveEmployeContract = tbaEmpleadoRepository.save(employee)
                        Mono.zip(workContractFlux.collectList(), saveEmployeContract, employeeAcountMono)
                    }
            } else {
                Mono.error<Any>(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.EMPLOYEE_NOT_FOUND + employeeId))
            }
        }
    }

    fun getAccountingDataEmployee(id: Int): Flux<CuentaDatosContablesDto> =
        conCuentaRepository.getDatosContables(id)

    fun createEmployee(
        tbaEmpleadoDto: EmpleadoDto,
        personId: Int,
        tbaInformacionContractualDto: InformacionContractualDto
    ): Mono<Empleado> {
        val firstContract = tbaInformacionContractualDto.rrhhContratosDtos?.firstOrNull()
        val isInternship = firstContract?.nombreModalidadcontrato == ContractModalityEnum.INTERNSHIP.nameModality
        val costoHora = Utils.calcularCostoHora(
            tbaInformacionContractualDto.salarioEmpleado!!,
            if (isInternship) BigDecimal.valueOf(6) else BigDecimal.valueOf(8)
        )
        return tbaEmpleadoRepository.save(
            Empleado(
                idEmpleado = null,
                idPosesionDomicilio = null,
                idCodigoSectorial = null,
                idTipoSangre = tbaEmpleadoDto.idTiposangre,
                idTipoLicenciaConducir = tbaEmpleadoDto.idTipolicenciaconducir,
                idCargo = tbaInformacionContractualDto.idCargo,
                idSucursal = tbaInformacionContractualDto.idSucursal,
                idPersona = personId,
                idDiscapacidad = tbaEmpleadoDto.idDiscapacidad,
                numeroHijosEmpleado = null,
                celularEmpleado = null,
                telefonoEmpleado = null,
                correoEmpleado = null,
                fechaCaducaLicenciaEmpleado = tbaEmpleadoDto.fechacaducalicenciaEmpleado,
                fechaIngresoEmpresaEmpleado = LocalDate.from(currentDate),
                fechaReingresoEmpleado = null,
                salarioEmpleado = tbaInformacionContractualDto.salarioEmpleado,
                horaOrdinariaEmpleado = costoHora,
                esDiscapacitadoEmpleado = tbaEmpleadoDto.esdiscapacitadoEmpleado,
                porcentajeDiscapacidadEmpleado = if (tbaEmpleadoDto.esdiscapacitadoEmpleado != false) tbaEmpleadoDto.porcdiscapacidadEmpleado else BigDecimal.ZERO,
                decimoTerceroAcumuladoEmpleado = true,
                decimoCuartoAcumuladoEmpleado = true,
                fraccionalesAcumuladosEmpleado = null,
                provisionVacacionesEmpleado = false,
                quincenaEmpleado = tbaInformacionContractualDto.quincenaEmpleado,
                valorQuincenaEmpleado = null,
                correoEmpresaEmpleado = null,
                usuarioSkypeEmpleado = null,
                telefonoEmpresaEmpleado = null,
                extensionEmpleado = null,
                celularEmpresaEmpleado = null,
                usuarioSistemaEmpleado = null,
                fechaSalidaEmpleado = null,
                motivoSalidaEmpleado = null,
                estadoEmpleado = true,
                tituloEmpleado = tbaEmpleadoDto.tituloEmpleado,
                idNivelEstudio = tbaEmpleadoDto.idNivelestudio,
                iessPatronalEmpleado = false,
                iessPersonalEmpleado = false,
                motivoReingresoEmpleado = null,
                impuestoRentaAnualEmpleado = BigDecimal.ZERO,
                impuestoRentaEmpleado = BigDecimal.ZERO,
                impuestoRentaRebajaEmpleado = BigDecimal.ZERO,
                pathCedulaEmpleado = null,
                pathLicenciaEmpleado = null,
                pathCvEmpleado = null
            )
        )
    }

    fun createPerson(tbaEmpleadoDto: EmpleadoDto): Mono<Persona> {
        val tbaPersonaDto = PersonaDto().apply {
            idTipoContribuyente = "02"
            idParroquia = tbaEmpleadoDto.idParroquia
            idPais = 593
            idEstadoCivil = tbaEmpleadoDto.idEstadocivil
            idGenero = tbaEmpleadoDto.idGenero
            idIdentificacionFiscal = 2
            identificacionPersona = tbaEmpleadoDto.identificacionPersona
            nombrePersona = tbaEmpleadoDto.nombrePersona
            fechaNacimientoPersona = tbaEmpleadoDto.fechanacimientoPersona
            direccionPersona = tbaEmpleadoDto.direccionPersona
            celularPersona = tbaEmpleadoDto.celularPersona
            telefonoPersona = tbaEmpleadoDto.telefonoEmpleado
            correoPersona = tbaEmpleadoDto.correoPersona
            esClientePersona = false
            esEmpleadoPersona = true
            esProveedorPersona = false
            fechaRegistroPersona = currentDate
        }
        return tbaPersonaService.createPerson(tbaPersonaDto)
    }

    fun getEmployeeActive(): Mono<List<EmpleadoPersonaDto>> {
        val tbaEmpleadoActivosDtoFlux = tbaEmpleadoRepository.getAllByEstadoEmpleado(StateEnum.ACTIVATED.identification)
        return tbaEmpleadoActivosDtoFlux.collectList().flatMap { activeEmployeesDatoList: List<EmpleadoPersonaDto> ->
            activeEmployeesDatoList.forEach { activeEmployeeDto ->
                activeEmployeeDto.metodoPago = if (activeEmployeeDto.estadoCuentabancaria == true) "TRANSFERENCIA" else "CHEQUE"
            }
            Mono.just(activeEmployeesDatoList)
        }
    }

    fun getEmployeeActiveByParam(paramSearch: String): Flux<EmpleadoFichaDto> {
        val (identification, completeName) = try {
            val id = if (Utils.validateNumbers(paramSearch)) paramSearch else null
            val name = if (Utils.validateNumbers(paramSearch)) null else paramSearch
            Pair(id, name)
        } catch (e: Exception) {
            throw CustomException(HttpStatus.NOT_FOUND, ErrorMessage.NULL_POINTER)
        }
        return tbaEmpleadoRepository.findEmpleadoByParams(completeName, identification)
            .filter { dto: EmpleadoFichaDto -> dto.estado == true }
    }

    fun getEmpleadoPersonaById(id: Int): Mono<EmpleadoPersonaDto> =
        tbaEmpleadoRepository.findById(id).flatMap { empleado: Empleado -> buildEmpleadoPersonaDto(empleado) }

    fun buildEmpleadoPersonaDto(empleado: Empleado): Mono<EmpleadoPersonaDto> {
        val personaMono = tbaPersonaRepository.findById(empleado.idPersona!!)
        val sucursalMono = tbaSucursalRepository.findById(empleado.idSucursal!!)
        return Mono.zip(personaMono, sucursalMono).map { tuple: reactor.util.function.Tuple2<Persona, Sucursal> ->
            val persona = tuple.t1
            val sucursal = tuple.t2
            EmpleadoPersonaDto().apply {
                idEmpleado = empleado.idEmpleado
                idPersona = empleado.idPersona
                idSucursal = empleado.idSucursal
                nombreSucursal = sucursal.nombreSucursal
                nombrePersona = persona.nombrePersona
                identificacionPersona = persona.identificacionPersona
                quincenaEmpleado = empleado.quincenaEmpleado
                valorquincenaEmpleado = empleado.valorQuincenaEmpleado
                estadoEmpleado = empleado.estadoEmpleado
            }
        }
    }
}
