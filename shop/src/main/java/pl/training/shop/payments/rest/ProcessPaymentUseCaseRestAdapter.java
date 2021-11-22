package pl.training.shop.payments.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.training.shop.commons.ExtendedValidation;
import pl.training.shop.commons.Lock;
import pl.training.shop.commons.Retry;
import pl.training.shop.payments.ProcessPaymentUseCase;

import static lombok.AccessLevel.PACKAGE;
import static pl.training.shop.commons.Lock.LockType.WRITE;
import static pl.training.shop.commons.rest.UriBuilder.requestUriWithId;

@RequestMapping("payments")
@RestController
@RequiredArgsConstructor(access = PACKAGE)
class ProcessPaymentUseCaseRestAdapter {

    private final PaymentsRestMapper mapper;
    private final ProcessPaymentUseCase processPaymentUseCase;

    @Lock(type = WRITE)
    @Retry(attempts = 2)
    @PostMapping
    ResponseEntity<PaymentDto> process(/*@Valid*/ @Validated(ExtendedValidation.class) @RequestBody PaymentRequestDto paymentRequestDto/*, BindingResult bindingResult*/) {
        /*if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }*/
        var paymentRequest = mapper.toModel(paymentRequestDto);
        var payment = processPaymentUseCase.process(paymentRequest);
        var paymentDto = mapper.toDto(payment);
        var locationUri = requestUriWithId(paymentDto.getId());
        return ResponseEntity.created(locationUri).body(paymentDto);
    }

}
