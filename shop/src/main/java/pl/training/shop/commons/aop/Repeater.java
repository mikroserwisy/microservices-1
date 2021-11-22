package pl.training.shop.commons.aop;

import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import pl.training.shop.commons.Retry;

@Aspect
@Component
@Log
public class Repeater {

    @Around("@annotation(pl.training.shop.commons.Retry)")
    public Object tryExecute(ProceedingJoinPoint joinPoint) throws Throwable {
        var method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        var annotation = method.getAnnotation(Retry.class);
        var attempts = annotation.attempts();
        var currentAttempt = 0;
        Throwable throwable;
        do {
            currentAttempt++;
            log.info(joinPoint.getSignature() +" method execution attempt " +  currentAttempt);
            try {
                return joinPoint.proceed();
            } catch (Throwable exception) {
                throwable = exception;
            }
        } while (currentAttempt < attempts);
        throw throwable;
    }

}
