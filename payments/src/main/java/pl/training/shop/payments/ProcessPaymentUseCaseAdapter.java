package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import pl.training.shop.commons.Atomic;
import pl.training.shop.commons.Profile;

import static lombok.AccessLevel.PACKAGE;

@RequiredArgsConstructor(access = PACKAGE)
class ProcessPaymentUseCaseAdapter implements ProcessPaymentUseCase {

    private final PaymentsMapper mapper;
    private final ProcessPaymentService processPaymentService;

    @Atomic
    @Profile
    @Override
    public Payment process(PaymentRequest paymentRequest) {
        var paymentRequestDomain = mapper.toDomain(paymentRequest);
        var paymentDomain = processPaymentService.process(paymentRequestDomain);
        return mapper.toModel(paymentDomain);
    }

}
