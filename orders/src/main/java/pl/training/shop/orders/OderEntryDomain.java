package pl.training.shop.orders;

import lombok.Builder;
import lombok.Value;
import org.javamoney.moneta.FastMoney;

@Builder
@Value
class OderEntryDomain {

    Long id;
    FastMoney price;
    int quantity;

}
