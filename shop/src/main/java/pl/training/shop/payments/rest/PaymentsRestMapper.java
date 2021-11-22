package pl.training.shop.payments.rest;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ValueMapping;
import pl.training.shop.commons.FastMoneyMapper;
import pl.training.shop.payments.Payment;
import pl.training.shop.payments.PaymentRequest;
import pl.training.shop.payments.PaymentStatus;

@Mapper(componentModel = "spring", uses = FastMoneyMapper.class)
interface PaymentsRestMapper {

    @Mapping(source = "additionalInfo", target = "properties")
    PaymentRequest toModel(PaymentRequestDto paymentRequestDto);

    @ValueMapping(source = "STARTED", target = "NOT_CONFIRMED")
    @ValueMapping(source = "FAILED", target = "NOT_CONFIRMED")
    @ValueMapping(source = "CANCELED", target = "NOT_CONFIRMED")
    PaymentStatusDto toDto(PaymentStatus paymentStatus);

    PaymentDto toDto(Payment payment);

}
