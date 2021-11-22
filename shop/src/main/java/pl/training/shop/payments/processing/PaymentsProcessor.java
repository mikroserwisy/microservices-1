package pl.training.shop.payments.processing;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import pl.training.shop.payments.Payment;

import javax.annotation.PostConstruct;

import static lombok.AccessLevel.PACKAGE;

@Aspect
@Component
@Log
@RequiredArgsConstructor(access = PACKAGE)
class PaymentsProcessor {

    private final PaymentsProcessingMapper mapper;

    @Value("${api.payments}")
    @Setter
    private String paymentsEndpoint;

    @PostConstruct
    void subscribe() {
        WebClient.builder().build()
                .get()
                .uri(paymentsEndpoint)
                .retrieve()
                .bodyToFlux(PaymentDto.class)
                .subscribe(paymentDto -> log.info("Update: " + paymentDto.toString()), throwable -> log.info(throwable.toString()));
    }

    @AfterReturning(value = "execution(* pl.training.shop.payments.ProcessPaymentUseCase.pro*(..))", returning = "payment")
    void onPayment(Payment payment) {
        WebClient.builder().build()
                .post()
                .uri(paymentsEndpoint)
                .bodyValue(mapper.toDto(payment))
                .retrieve()
                .bodyToMono(PaymentDto.class)
                .subscribe(paymentDto -> log.info(paymentDto.toString()), throwable -> log.info(throwable.toString()));
    }

}
