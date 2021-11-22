package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static lombok.AccessLevel.PACKAGE;

//@Primary
//@Transactional
//@Service
@RequiredArgsConstructor(access = PACKAGE)
public class PaymentsFacade implements GetPaymentUseCase, ProcessPaymentUseCase {

    private final GetPaymentUseCase getPaymentUseCase;
    private final ProcessPaymentUseCase processPaymentUseCase;

    @Override
    public Payment getById(String id) {
        return getPaymentUseCase.getById(id);
    }

    @Override
    public Payment process(PaymentRequest paymentRequest) {
        return processPaymentUseCase.process(paymentRequest);
    }

}
