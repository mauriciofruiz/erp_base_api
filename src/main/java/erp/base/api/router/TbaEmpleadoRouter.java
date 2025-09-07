package erp.base.api.router;

import erp.base.api.constants.PathConstants;
import erp.base.api.handler.TbaEmpleadoHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class TbaEmpleadoRouter {

    @Bean
    public RouterFunction<ServerResponse> employeeRouter(TbaEmpleadoHandler handler) {
        return RouterFunctions.route()
                .GET(PathConstants.EMPLOYEE, handler::getFichaEmpleado)
                .POST(PathConstants.EMPLOYEE, handler::createEmployee)
                // todo: refactor path --> .GET(PathConstants.EMPLOYEE+ PathConstants.ID_PARAM, handler::getCompleteEmployee)
                .PUT(PathConstants.EMPLOYEE + PathConstants.UPDATE + PathConstants.ID_PARAM, handler::updateInformation)
                .GET(PathConstants.EMPLOYEE + PathConstants.EMPLOYEE_INFORMATION + PathConstants.ID_PARAM, handler::getContractualInformation)
                .PUT(PathConstants.EMPLOYEE + PathConstants.EMPLOYEE_INFORMATION + PathConstants.ID_PARAM, handler::updateContractualInformation)
                .GET(PathConstants.EMPLOYEE + PathConstants.ACCOUNTING_DATA_EMPLOYEE + PathConstants.ID_PARAM, handler::getAccountingDataEmployee)
                .GET(PathConstants.EMPLOYEE + PathConstants.ACTIVE, handler::getAllEmployeeActive)
                .GET(PathConstants.EMPLOYEE_PERSON, handler::getEmpleadoPersonaById)
                .build();
    }
}
