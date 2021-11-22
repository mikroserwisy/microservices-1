package pl.training.shop.payments;

import lombok.Value;
import org.javamoney.moneta.FastMoney;

import java.time.Instant;
import java.util.Map;

@Value
public class Payment {

    String id;
    FastMoney value;
    Instant timestamp;
    PaymentStatus status;
    Map<String, String> properties;

}
