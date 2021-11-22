package pl.training.shop.orders;

import org.javamoney.moneta.FastMoney;

import java.util.Map;
import java.util.Optional;

public interface PaymentsService {

    Optional<Payment> pay(Long requestId, FastMoney value, Map<String, String> properties);

}
