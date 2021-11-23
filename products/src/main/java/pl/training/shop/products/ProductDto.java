package pl.training.shop.products;

import lombok.Data;

import java.math.BigDecimal;

@Data
class ProductDto {

    private Long id;
    private String name;
    private BigDecimal price;

}
