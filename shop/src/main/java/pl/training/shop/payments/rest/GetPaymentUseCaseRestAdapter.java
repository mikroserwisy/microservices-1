package pl.training.shop.payments.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.training.shop.commons.Length;
import pl.training.shop.payments.GetPaymentUseCase;

import static lombok.AccessLevel.PACKAGE;

@RequestMapping("payments")
@RestController
@RequiredArgsConstructor(access = PACKAGE)
class GetPaymentUseCaseRestAdapter {

    private final PaymentsRestMapper mapper;
    private final GetPaymentUseCase getPaymentUseCase;

    @GetMapping("{id}")
    public ResponseEntity<PaymentDto> getById(@Length(length = 36) @PathVariable String id) {
        var payment = getPaymentUseCase.getById(id);
        var paymentDto = mapper.toDto(payment);
        return ResponseEntity.ok(paymentDto);
    }

}
