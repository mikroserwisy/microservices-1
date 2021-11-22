package pl.training.shop.orders;

import lombok.Value;

import java.util.Map;

@Value
public class Order {

    Long id;
    Map<Long, Integer> entries;

}
