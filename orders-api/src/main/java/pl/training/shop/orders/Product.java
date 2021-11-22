package pl.training.shop.orders;

import lombok.Value;
import org.javamoney.moneta.FastMoney;

@Value
public class Product {

    Long id;
    String name;
    FastMoney price;

}
