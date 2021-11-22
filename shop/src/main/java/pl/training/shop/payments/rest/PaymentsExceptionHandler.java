package pl.training.shop.payments.rest;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.training.shop.commons.rest.ExceptionDto;
import pl.training.shop.commons.rest.GlobalExceptionHandler;
import pl.training.shop.payments.PaymentNotFoundException;

import java.util.Locale;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice(basePackages = "pl.training.shop.payments.rest")
class PaymentsExceptionHandler extends GlobalExceptionHandler {

    public PaymentsExceptionHandler(MessageSource messageSource) {
        super(messageSource);
    }

    @ExceptionHandler(PaymentNotFoundException.class)
    ResponseEntity<ExceptionDto> onPaymentNotFoundException(PaymentNotFoundException exception, Locale locale) {
        return createResponse(exception, NOT_FOUND, locale);
    }

}
