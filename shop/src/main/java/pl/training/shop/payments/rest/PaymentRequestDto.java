package pl.training.shop.payments.rest;

import lombok.Value;
import pl.training.shop.commons.BaseValidation;
import pl.training.shop.commons.ExtendedValidation;
import pl.training.shop.commons.money.Money;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Map;

@Value
class PaymentRequestDto {

    @Min(value = 1, groups = ExtendedValidation.class)
    Long requestId;
    @Pattern(regexp = "\\d+ PLN", groups = ExtendedValidation.class)
    @Money(groups = BaseValidation.class)
    String value;
    @NotEmpty(groups = {BaseValidation.class, ExtendedValidation.class})
    Map<String, String> additionalInfo;

}
