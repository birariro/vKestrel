package com.birariro.vkestrel.config.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.util.StopWatch;

@Aspect
@Slf4j
public class LogAspect {

    @Around("@annotation(com.birariro.vkestrel.annotation.AopExecutionTime)")
    public Object methodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable{

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object process = joinPoint.proceed();

        stopWatch.stop();
        log.info(stopWatch.prettyPrint());

        return process;
    }
}
