package pl.training.shop.commons.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import pl.training.shop.commons.Length;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Optional;

@Aspect
@Component
public class LengthValidator {

    @Before("execution(* *(@pl.training.shop.commons.Length (*)))")
    public void validate(JoinPoint joinPoint) throws NoSuchMethodException {
        var signature = (MethodSignature) joinPoint.getSignature();
        var methodName = signature.getMethod().getName();
        var parameterTypes = signature.getMethod().getParameterTypes();
        var annotations = joinPoint.getTarget().getClass().getMethod(methodName, parameterTypes).getParameterAnnotations();
        var arguments = joinPoint.getArgs();
        for (int index = 0; index < arguments.length; index++) {
            var annotation = getAnnotation(annotations[index]);
            if (annotation.isPresent()) {
                var value = (String) arguments[index];
                if (value.length() < annotation.get().length()) {
                    throw new IllegalArgumentException();
                }
            }
        }
    }

    private Optional<Length> getAnnotation(Annotation[] annotations) {
        return Arrays.stream(annotations)
                .filter(annotation -> annotation instanceof Length)
                .map(annotation -> (Length) annotation)
                .findFirst();
    }

}
