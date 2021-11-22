package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static lombok.AccessLevel.PACKAGE;

@RequiredArgsConstructor(access = PACKAGE)
class PaymentsReaderAdapter {

    private final PaymentsMapper mapper;
    private final PaymentsReader paymentsReader;

    Optional<PaymentDomain> getById(String id) {
        return paymentsReader.getById(id)
                .map(mapper::toDomain);
    }

}
