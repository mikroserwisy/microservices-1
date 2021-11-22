package pl.training.shop.orders;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import static java.util.Collections.emptyMap;

@RequiredArgsConstructor
@Log
class PlaceOrderService {

    private final PaymentsService paymentsService;

    void place(OrderDomain order) {
        var paymentValue = order.getTotalValue();
        // load payment properties and user id
        var payment = paymentsService.pay(1L, paymentValue, emptyMap());
        log.info("Order: " +  order);
        log.info("Payment: " +  payment);
    }

}
