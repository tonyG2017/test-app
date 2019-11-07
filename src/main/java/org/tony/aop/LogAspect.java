package org.tony.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {
    @Pointcut("execution(* org.tony.service.UserService.addUser (..))")
    public void pc1(){}

    @Before(value="pc1()")
    public void before(JoinPoint joinPoint){
        String name= joinPoint.getSignature().getName();
        System.out.println("********AOP**********: "+name+" starts execution");
    }

    @After(value="pc1()")
    public void after(JoinPoint joinPoint){
        String name= joinPoint.getSignature().getName();
        System.out.println("********AOP**********: "+name+" ends method execution");
    }

    @AfterReturning(value="pc1()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result){
        String name= joinPoint.getSignature().getName();
        System.out.println("********AOP**********: "+name+" returns "+result);
    }

    @AfterThrowing(value="pc1()",throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e){
        String name= joinPoint.getSignature().getName();
        System.out.println("********AOP**********: "+name+" throws "+e.getMessage());
    }

    @Around("pc1()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        String name= proceedingJoinPoint.getSignature().getName();
        System.out.println("********AOP**********: "+name+" around before proceed");
        Object object= proceedingJoinPoint.proceed();
        System.out.println("********AOP**********: "+name+" around proceed returns"+ object);
        System.out.println("********AOP**********: "+name+" around after proceed");
        return object;
    }
}
