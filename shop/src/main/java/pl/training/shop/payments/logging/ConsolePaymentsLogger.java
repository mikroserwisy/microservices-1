package pl.training.shop.payments.logging;

import lombok.extern.java.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import pl.training.shop.payments.Payment;
import pl.training.shop.payments.PaymentRequest;

@Order(1)
@Aspect
@Component
@Log
class ConsolePaymentsLogger {

    @Pointcut("execution(* pl.training.shop.payments.ProcessPaymentUseCase.pro*(..))")
    //@Pointcut("bean(processPaymentUseCase)")
    void logPayments() {
    }

    @Before("logPayments() && args(paymentRequest)")
    void log(PaymentRequest paymentRequest) {
        log.info("New payment request: " + paymentRequest);
    }

    @AfterReturning(value = "logPayments()", returning = "payment")
    void log(Payment payment) {
        log.info("New payment: " + payment);
    }

    @AfterThrowing(value = "logPayments()", throwing = "exception")
    void log(JoinPoint joinPoint, RuntimeException exception) {
        log.info("Payment method " + joinPoint.getSignature() + " failed with exception " + exception);
    }

    @After("logPayments()")
    void log() {
        log.info("After payment");
    }

}
