package pl.training.shop.commons.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Log
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private static final String KEY_VALUE_SEPARATOR = ": ";
    private static final String DELIMITER = ", ";
    
    private final MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ExceptionDto> onException(Exception exception, Locale locale) {
        log.warning(exception.getClass().getSimpleName());
        return createResponse(exception, INTERNAL_SERVER_ERROR, locale);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ExceptionDto> onMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        var description = getValidationErrors(exception);
        return ResponseEntity.badRequest().body(new ExceptionDto(description));
    }

    private String getValidationErrors(MethodArgumentNotValidException exception) {
        return exception.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + KEY_VALUE_SEPARATOR + error.getDefaultMessage())
                .collect(Collectors.joining(DELIMITER));
    }

    protected ResponseEntity<ExceptionDto> createResponse(Exception exception, HttpStatus status, Locale locale) {
        var key = exception.getClass().getSimpleName();
        String description;
        try {
            description = messageSource.getMessage(key, new Object[] {}, locale);
        } catch (NoSuchMessageException ex) {
            description = key;
        }
        return ResponseEntity.status(status).body(new ExceptionDto(description));
    }

}
