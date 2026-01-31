package erp.base.api.aspects

import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.stereotype.Component

@Aspect
@Component
class ClienteSaveAspect {

    @Before("execution(* erp.base.api.repository.ClienteRepository.save(..)) && args(entity)")
    fun beforeSave(entity: Any) {
    }

    @AfterReturning(
        pointcut = "execution(* erp.base.api.repository.ClienteRepository.save(..))",
        returning = "result",
    )
    fun afterSave(result: Any) {
    }
}
