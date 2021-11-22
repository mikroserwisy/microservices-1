package pl.training.shop.payments;

import java.time.Instant;

public interface TimeProvider {

    Instant getTimestamp();

}
