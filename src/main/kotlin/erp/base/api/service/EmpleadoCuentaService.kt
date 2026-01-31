package erp.base.api.service

import erp.base.api.enums.AreaGroupEnum
import erp.base.api.models.Departamento
import erp.base.api.models.EmpleadoCuenta
import erp.base.api.repository.cont.EmpleadoCuentaRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class EmpleadoCuentaService(
    private val conEmpleadoCuentaRepository: EmpleadoCuentaRepository,
    private val tbaDepartamentoService: DepartamentoService
) {

    fun findByIdEmployee(idEmpleadoCuenta: Int): Flux<EmpleadoCuenta> =
        conEmpleadoCuentaRepository.findByIdEmpleado(idEmpleadoCuenta)

    fun update(conEmpleadoCuenta: List<EmpleadoCuenta>, idDepartment: Int): Mono<EmpleadoCuenta> {
        val tbaDepartamentoMono = tbaDepartamentoService.getDepartmentById(idDepartment)
        return Mono.zip(tbaDepartamentoMono, tbaDepartamentoMono).flatMap { tuple ->
            val tbaDepartamento = tuple.t1
            val conEmpleadoCuenta1 = validateGroupArea(conEmpleadoCuenta[0], tbaDepartamento.idAreaGrupo)
            conEmpleadoCuentaRepository.save(conEmpleadoCuenta1)
        }
    }

    fun createEmployeeAccount(departmentId: Int, groupAreaId: Int?, employeeId: Int): Flux<EmpleadoCuenta> {
        val employeeAccountsList = mutableListOf<EmpleadoCuenta>()
        val employeeAccount = EmpleadoCuenta().apply {
            idEmpleado = employeeId
        }
        validateGroupArea(employeeAccount, groupAreaId)
        employeeAccountsList.add(employeeAccount)
        val accountSalariesCXP = EmpleadoCuenta().apply {
            idEmpleado = employeeId
            idCuenta = 279
        }
        employeeAccountsList.add(accountSalariesCXP)
        return conEmpleadoCuentaRepository.saveAll(employeeAccountsList)
    }

    fun validateGroupArea(employeeAccount: EmpleadoCuenta, getGroupAreaId: Int?): EmpleadoCuenta {
        employeeAccount.idCuenta = when {
            getGroupAreaId != null && getGroupAreaId == AreaGroupEnum.SALES.identification -> 573
            getGroupAreaId != null && getGroupAreaId == AreaGroupEnum.PRODUCTION.identification -> 425
            else -> 670
        }
        return employeeAccount
    }
}
