package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PACKAGE;

@RequiredArgsConstructor(access = PACKAGE)
class GetPaymentService {

    private final PaymentsReaderAdapter paymentsReader;

    PaymentDomain getById(String id) {
        return paymentsReader.getById(id)
                .orElseThrow(PaymentNotFoundException::new);
    }

}
