package com.sicon.tba.aspects;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.AfterReturning;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class ClienteSaveAspect {

    @Before("execution(* com.sicon.tba.repository.TbaClienteRepository.save(..)) && args(entity)")
    public void beforeSave(Object entity) {

    }

    @AfterReturning(pointcut = "execution(* com.sicon.tba.repository.TbaClienteRepository.save(..))", returning = "result")
    public void afterSave(Object result) {
    }

}