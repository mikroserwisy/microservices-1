package pl.training.shop.orders;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OrdersConfiguration {

    private static final OrdersFactory ORDERS_FACTORY = new DefaultOrdersFactory();

    @Bean
    PlaceOrderUseCase placeOrderUseCase(PaymentsService paymentsService, ProductsProvider productsProvider) {
        return ORDERS_FACTORY.create(paymentsService, productsProvider);
    }

}
