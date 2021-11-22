package pl.training.shop.payments;

import lombok.Builder;
import lombok.Value;
import org.javamoney.moneta.FastMoney;

@Builder
@Value
class PaymentDomain {

    String id;
    Long requestId;
    FastMoney value;
    String status;

}
