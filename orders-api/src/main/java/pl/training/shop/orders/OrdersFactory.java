package pl.training.shop.orders;

public interface OrdersFactory {

    PlaceOrderUseCase create(PaymentsService paymentsService, ProductsProvider productsProvider);

}
