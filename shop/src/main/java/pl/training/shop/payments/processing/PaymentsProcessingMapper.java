package pl.training.shop.payments.processing;

import org.mapstruct.Mapper;
import pl.training.shop.commons.FastMoneyMapper;
import pl.training.shop.payments.Payment;

@Mapper(componentModel = "spring", uses = FastMoneyMapper.class)
interface PaymentsProcessingMapper {

    PaymentDto toDto(Payment payment);

}
