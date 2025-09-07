package erp.base.api.aspects;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.AfterReturning;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class ClienteSaveAspect {

    @Before("execution(* erp.base.api.repository.TbaClienteRepository.save(..)) && args(entity)")
    public void beforeSave(Object entity) {

    }

    @AfterReturning(pointcut = "execution(* erp.base.api.repository.TbaClienteRepository.save(..))", returning = "result")
    public void afterSave(Object result) {
    }

}
