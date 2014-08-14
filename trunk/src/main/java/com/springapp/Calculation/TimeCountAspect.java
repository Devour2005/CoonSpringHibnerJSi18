package com.springapp.Calculation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeCountAspect {

    @Around("execution(* com.springapp.Calculation.CalculatorController.calculMethod(..))")
    public Object timeCounterClass(ProceedingJoinPoint joinpoint) {
        Object result = null;
        try {
            System.out.println("Preparing to calculate");
            long start = System.currentTimeMillis(); // Before method invoke

            result = joinpoint.proceed(); // Method invoke

            long end = System.currentTimeMillis(); // After method invoke
            System.out.println("Calculation took " + (end - start)
                    + " milliseconds.");
        } catch (Throwable t) {

            System.out.println("Nothing happend!");
        }
        return result;
    }
}
