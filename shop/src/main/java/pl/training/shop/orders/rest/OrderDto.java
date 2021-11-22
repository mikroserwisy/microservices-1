package pl.training.shop.orders.rest;

import lombok.Value;

import java.util.Map;

@Value
class OrderDto {

    Long id;
    Map<Long, Integer> entries;

}
