package com.javahub.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentLinkedQueue;

@Aspect
@Component
public class ExecutionTimeLoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(ExecutionTimeLoggingAspect.class);
    private final ConcurrentLinkedQueue<String> auditTrail = new ConcurrentLinkedQueue<>();

    @Around("@annotation(com.javahub.aop.annotation.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        String method = joinPoint.getSignature().toShortString();

        try {
            Object result = joinPoint.proceed();
            long duration = System.currentTimeMillis() - start;
            String entry = "[SUCCESS] " + method + " executed in " + duration + "ms";
            log.info(entry);
            auditTrail.add(entry);
            return result;
        } catch (Throwable ex) {
            long duration = System.currentTimeMillis() - start;
            String entry = "[FAILED] " + method + " failed after " + duration + "ms with " + ex.getMessage();
            log.error(entry);
            auditTrail.add(entry);
            throw ex;
        }
    }

    public ConcurrentLinkedQueue<String> getAuditTrail() {
        return auditTrail;
    }
}
