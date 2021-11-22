package pl.training.shop.payments;

import java.util.Optional;

public interface PaymentsReader {

    Optional<Payment> getById(String id);

}
