package pl.training.shop.orders.rest;

import org.mapstruct.Mapper;
import pl.training.shop.orders.Order;

@Mapper(componentModel = "spring")
interface OrdersRestMapper {

    Order toModel(OrderDto orderDto);

}
