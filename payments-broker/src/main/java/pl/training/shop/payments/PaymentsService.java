package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.time.Duration;

import static lombok.AccessLevel.PACKAGE;

@Service
@RequiredArgsConstructor(access = PACKAGE)
class PaymentsService {

    private static final String CONFIRMED_STATUS = "CONFIRMED";

    private final PaymentsRepository paymentsRepository;
    private final PaymentsMapper mapper;
    private final Sinks.Many<PaymentDomain> payments = Sinks.many().replay().all(10);

    Flux<PaymentDomain> getPaymentsStream() {
        return payments.asFlux();
    }

    Mono<PaymentDomain> process(Mono<PaymentDomain> paymentStream) {
        return paymentStream.map(this::process)
                .map(mapper::toDocument)
                .flatMap(paymentsRepository::save)
                .map(mapper::toDomain)
                .flatMap(payment -> Mono.just(payment).delayElement(Duration.ofSeconds(5)))
                .doOnNext(payments::tryEmitNext);
    }

    private PaymentDomain process(PaymentDomain paymentDomain) {
        return PaymentDomain.builder()
                .id(paymentDomain.getId())
                .requestId(paymentDomain.getRequestId())
                .value(paymentDomain.getValue())
                .status(CONFIRMED_STATUS)
                .build();
    }


}
