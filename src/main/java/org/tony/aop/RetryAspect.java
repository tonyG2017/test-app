package org.tony.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Slf4j
@Aspect
@Component
public class RetryAspect {

    @Pointcut("@annotation(org.tony.aop.Retry)")
    public void retryPointcut() {

    }

    @Around("retryPointcut() && @annotation(retry)")
    @Transactional(rollbackFor = Exception.class)
    public Object tryAgain(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable {
        int count = 0;
        do {
            count++;
            try {
                log.info("Retry round: {}", count);
                return joinPoint.proceed();
            } catch (RetryException e) {
                if (count > retry.value()) {
                    log.error("Retry failed!");
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return false;
                }
            }
        } while (true);
    }
}