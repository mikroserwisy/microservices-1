package pl.training.shop.orders;

import lombok.Setter;
import org.javamoney.moneta.FastMoney;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Mapper
abstract class OrdersMapper {

    @Setter
    private ProductsProvider productsProvider;

    OrderDomain toDomain(Order order) {
        return new OrderDomain(order.getId(), toDomain(order.getEntries()));
    }

    private List<OderEntryDomain> toDomain(Map<Long, Integer> entries) {
        return entries.entrySet().stream()
                .map(entry -> OderEntryDomain.builder()
                        .id(entry.getKey())
                        .quantity(entry.getValue())
                        .price(getProductPrice(entry.getKey()))
                        .build())
                .collect(toList());
    }

    private FastMoney getProductPrice(Long productId) {
        return productsProvider.getProduct(productId).getPrice();
    }

}
