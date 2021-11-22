package pl.training.shop.payments.rest;

import lombok.Value;

import java.time.Instant;

@Value
class PaymentDto {

    String id;
    String value;
    Instant timestamp;
    PaymentStatusDto status;

}
