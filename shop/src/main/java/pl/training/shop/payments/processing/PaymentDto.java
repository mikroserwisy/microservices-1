package pl.training.shop.payments.processing;

import lombok.Data;
import lombok.Value;

import java.time.Instant;

@Data
class PaymentDto {

    private String requestId;
    private String value;

}
