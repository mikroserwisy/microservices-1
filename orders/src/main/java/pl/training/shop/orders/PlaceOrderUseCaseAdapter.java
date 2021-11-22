package pl.training.shop.orders;

import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PACKAGE;

@RequiredArgsConstructor(access = PACKAGE)
class PlaceOrderUseCaseAdapter implements PlaceOrderUseCase {

    private final OrdersMapper mapper;
    private final PlaceOrderService placeOrderService;

    @Override
    public void place(Order order) {
        var orderDomain = mapper.toDomain(order);
        placeOrderService.place(orderDomain);
    }

}
