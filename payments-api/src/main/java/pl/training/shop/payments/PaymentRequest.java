package pl.training.shop.payments;

import lombok.Value;
import org.javamoney.moneta.FastMoney;

import java.util.Map;

@Value
public class PaymentRequest {

    Long requestId;
    FastMoney value;
    Map<String, String> properties;

}
