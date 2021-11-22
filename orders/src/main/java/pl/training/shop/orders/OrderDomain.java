package pl.training.shop.orders;

import lombok.Value;
import org.javamoney.moneta.FastMoney;
import pl.training.shop.commons.LocalMoney;

import java.util.List;

@Value
class OrderDomain {

    Long id;
    List<OderEntryDomain> entries;


    FastMoney getTotalValue() {
        return entries.stream()
                .map(entry -> entry.getPrice().multiply(entry.getQuantity()))
                .reduce(LocalMoney.zero(), FastMoney::add);
    }

}
