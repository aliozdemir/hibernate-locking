package com.ozdemir.hibernatelocking.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class CustomLogAspect {

    @Around("@annotation(CustomLog)")
    public Object customLog(ProceedingJoinPoint joinPoint) throws Throwable {
        final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        final String endpoint = request.getServletPath();
        final MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        final String methodName = methodSignature.getName();
        CustomLog customLog = methodSignature.getMethod().getAnnotation(CustomLog.class);

        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;

        if(customLog.level().equals(LogLevel.DEBUG)){
            log.debug("Endpoint : {} Method : {} Execution Time : {}", endpoint, methodName, executionTime);
        }else if(customLog.level().equals(LogLevel.ERROR)){
            log.error("Endpoint : {} Method : {} Execution Time : {}", endpoint, methodName, executionTime);
        }else if(customLog.level().equals(LogLevel.INFO)){
            log.info("Endpoint : {} Method : {} Execution Time : {}", endpoint, methodName, executionTime);
        }else{
            log.warn("Endpoint : {} Method : {} Execution Time : {}", endpoint, methodName, executionTime);
        }
        return proceed;
    }
}
