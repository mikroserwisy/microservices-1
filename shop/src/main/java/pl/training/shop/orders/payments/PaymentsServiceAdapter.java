package pl.training.shop.orders.payments;

import lombok.RequiredArgsConstructor;
import org.javamoney.moneta.FastMoney;
import org.springframework.stereotype.Component;
import pl.training.shop.orders.Payment;
import pl.training.shop.orders.PaymentsService;
import pl.training.shop.payments.PaymentRequest;
import pl.training.shop.payments.ProcessPaymentUseCase;

import java.util.Map;
import java.util.Optional;

import static lombok.AccessLevel.PACKAGE;

@Component
@RequiredArgsConstructor(access = PACKAGE)
class PaymentsServiceAdapter implements PaymentsService {

    private final ProcessPaymentUseCase processPaymentUseCase;

    @Override
    public Optional<Payment> pay(Long requestId, FastMoney value, Map<String, String> properties) {
        var paymentRequest = new PaymentRequest(requestId, value, properties);
        var payment = processPaymentUseCase.process(paymentRequest);
        return Optional.of(new Payment(payment.getId(), payment.getStatus().name()));
    }

}
