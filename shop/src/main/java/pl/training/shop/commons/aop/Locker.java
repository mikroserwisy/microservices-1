package pl.training.shop.commons.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import pl.training.shop.commons.Lock;
import pl.training.shop.commons.Lock.LockType;

import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Aspect
@Component
@RequiredArgsConstructor
public class Locker {

    private static  final String DEFAULT_LOCK_NAME = "default";

    private final ReadWriteLock defaultLock = new ReentrantReadWriteLock();
    private final Map<String, ReadWriteLock> locks;

    @Around("@annotation(pl.training.shop.commons.Lock)")
    public Object lock(ProceedingJoinPoint joinPoint) throws Throwable {
        var method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        var annotation = method.getAnnotation(Lock.class);
        var lockName = annotation.value();
        var lock = lockName.equals(DEFAULT_LOCK_NAME) ? defaultLock : locks.get(lockName);
        var targetLock = annotation.type().equals(LockType.READ) ? lock.readLock() : lock.writeLock();
        targetLock.lock();
        try {
            return joinPoint.proceed();
        } finally {
            targetLock.unlock();
        }
    }

}
