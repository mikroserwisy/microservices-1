package pl.training.shop.payments;

import java.util.UUID;

class UUIDPaymentIdGenerator implements PaymentIdGenerator {

    @Override
    public String getNext() {
        return UUID.randomUUID().toString();
    }

}
