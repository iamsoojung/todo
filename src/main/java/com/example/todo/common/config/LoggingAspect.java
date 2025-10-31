package com.example.todo.common.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Around("execution(* com.example.todo.controller..*(..))")
    public Object logController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        long start = System.currentTimeMillis();
        log.info("[START] {} args={}", proceedingJoinPoint.getSignature(), Arrays.toString(proceedingJoinPoint.getArgs()));

        // method info
        Method method = getMethod(proceedingJoinPoint);
        log.info("======= method name = {} =======", method.getName());

        // parameter
        Object[] args = proceedingJoinPoint.getArgs();
        if (args != null && args.length > 0) {
            log.info("======= method args = {} =======", Arrays.toString(args));
        } else {
            args = null;
        }

        // method proceed()
        Object returnObj = args != null ? proceedingJoinPoint.proceed(args) : proceedingJoinPoint.proceed();

        // return
        if (returnObj != null) {
            log.info("return type = {}", returnObj.getClass().getSimpleName());
            log.info("return value = {}", returnObj);
        } else {
            log.info("return value = null");
        }

        long end = System.currentTimeMillis();
        log.info("[END] {} took={}ms", proceedingJoinPoint.getSignature(), (end - start));

        return returnObj;
    }

    private Method getMethod(ProceedingJoinPoint proceedingJoinPoint) {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        return methodSignature.getMethod();
    }
}
