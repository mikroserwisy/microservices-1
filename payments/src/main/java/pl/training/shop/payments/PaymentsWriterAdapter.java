package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PACKAGE;

@RequiredArgsConstructor(access = PACKAGE)
class PaymentsWriterAdapter {

    private final PaymentsMapper mapper;
    private final PaymentsWriter paymentsWriter;

    PaymentDomain save(PaymentDomain paymentDomain) {
       var payment = mapper.toModel(paymentDomain);
       return mapper.toDomain(paymentsWriter.save(payment));
    }

}
