package pl.training.shop.payments;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Value
class PaymentDto {

    String id;
    Long requestId;
    String value;
    @JsonProperty(access = READ_ONLY)
    String status;

}
