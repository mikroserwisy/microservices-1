
package pl.training.shop.payments;

import lombok.Builder;
import lombok.Value;
import org.javamoney.moneta.FastMoney;

import java.time.Instant;
import java.util.Map;

@Builder
@Value
class PaymentDomain {

    String id;
    FastMoney value;
    Instant timestamp;
    PaymentStatusDomain status;
    Map<String, String> properties;

}
