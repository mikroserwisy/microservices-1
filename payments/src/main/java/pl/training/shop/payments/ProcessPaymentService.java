package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PACKAGE;

@RequiredArgsConstructor(access = PACKAGE)
class ProcessPaymentService {

    private final PaymentIdGenerator paymentIdGenerator;
    private final TimeProvider timeProvider;
    private final PaymentsWriterAdapter paymentsWriter;

    PaymentDomain process(PaymentRequestDomain paymentRequest) {
        var payment = createPayment(paymentRequest);
        paymentsWriter.save(payment);
        return payment;
    }

    private PaymentDomain createPayment(PaymentRequestDomain paymentRequest) {
        return PaymentDomain.builder()
                .id(paymentIdGenerator.getNext())
                .value(paymentRequest.getValue())
                .timestamp(timeProvider.getTimestamp())
                .properties(paymentRequest.getProperties())
                .status(PaymentStatusDomain.STARTED)
                .build();
    }

}
