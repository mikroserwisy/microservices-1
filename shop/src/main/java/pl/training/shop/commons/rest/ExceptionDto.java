package pl.training.shop.commons.rest;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class ExceptionDto {

    String description;
    LocalDateTime timestamp = LocalDateTime.now();

}
