package pl.training.shop.commons.aop;

import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Aspect
@Component
@Log
public class Profiler {

    @Around("@annotation(pl.training.shop.commons.Profile)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        var startTime = System.nanoTime();
        var result = joinPoint.proceed();
        var totalTime = System.nanoTime() - startTime;
        log.info(joinPoint.getSignature() + " executed in " + totalTime + " ns");
        return result;
    }

}
