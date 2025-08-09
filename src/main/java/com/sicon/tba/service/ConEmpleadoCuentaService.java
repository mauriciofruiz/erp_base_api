package com.sicon.tba.service;

import com.sicon.tba.enums.AreaGroupEnum;
import com.sicon.tba.models.ConEmpleadoCuenta;
import com.sicon.tba.models.TbaDepartamento;
import com.sicon.tba.repository.cont.ConEmpleadoCuentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConEmpleadoCuentaService {

    private final ConEmpleadoCuentaRepository conEmpleadoCuentaRepository;

    //Service
    private final TbaDepartamentoService tbaDepartamentoService;

    public Flux<ConEmpleadoCuenta> findByIdEmployee(Integer idEmpleadoCuenta) {
        return conEmpleadoCuentaRepository.findByIdEmpleado(idEmpleadoCuenta);

    }

    public Mono<ConEmpleadoCuenta> update(List<ConEmpleadoCuenta> conEmpleadoCuenta, Integer idDepartment) {
        Mono < TbaDepartamento > tbaDepartamentoMono = tbaDepartamentoService.getDepartmentById(idDepartment);
        return Mono.zip(tbaDepartamentoMono,tbaDepartamentoMono ).flatMap( tuple -> {
            TbaDepartamento tbaDepartamento = tuple.getT1();
            ConEmpleadoCuenta conEmpleadoCuenta1 = validateGroupArea(conEmpleadoCuenta.get(0), tbaDepartamento.getIdAreaGrupo());
            return conEmpleadoCuentaRepository.save(conEmpleadoCuenta1);
        });
    }

    public Flux<ConEmpleadoCuenta> createEmployeeAccount(Integer departmentId, Integer groupAreaId, Integer employeeId) {

        List<ConEmpleadoCuenta> employeeAccountsList = new ArrayList<>();
        ConEmpleadoCuenta employeeAccount = new ConEmpleadoCuenta();
        employeeAccount.setIdEmpleado(employeeId);
        employeeAccount = validateGroupArea(employeeAccount, groupAreaId);
        employeeAccountsList.add(employeeAccount);
        // Carga de SalariosCXP
        ConEmpleadoCuenta accountSalariesCXP = new ConEmpleadoCuenta();
        accountSalariesCXP.setIdEmpleado(employeeId);
        accountSalariesCXP.setIdCuenta(279);
        employeeAccountsList.add(accountSalariesCXP);

        return conEmpleadoCuentaRepository.saveAll(employeeAccountsList);
    }

    public ConEmpleadoCuenta validateGroupArea(ConEmpleadoCuenta employeeAccount, Integer getGroupAreaId) {
        if (getGroupAreaId != null && getGroupAreaId.equals(AreaGroupEnum.SALES.getIdentification())) {
            employeeAccount.setIdCuenta(573);
        } else if (getGroupAreaId != null && getGroupAreaId.equals(AreaGroupEnum.PRODUCTION.getIdentification())) {
            employeeAccount.setIdCuenta(425);
        } else {
            employeeAccount.setIdCuenta(670);
        }
        return employeeAccount;
    }
}
