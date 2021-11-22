package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static lombok.AccessLevel.PACKAGE;

@Component
@RequiredArgsConstructor(access = PACKAGE)
class PaymentsHandler {

    private final PaymentsMapper mapper;
    private final PaymentsService paymentsService;

    Mono<ServerResponse> getPayments(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_NDJSON)
                .body(paymentsService.getPaymentsStream().map(mapper::toDto), PaymentDto.class);
    }

    Mono<ServerResponse> process(ServerRequest serverRequest) {
        var paymentStream = serverRequest.bodyToMono(PaymentDto.class).map(mapper::toDomain);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(paymentsService.process(paymentStream).map(mapper::toDto), PaymentDto.class);
    }

}
