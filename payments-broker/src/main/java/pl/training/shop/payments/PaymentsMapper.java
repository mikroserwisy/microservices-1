package pl.training.shop.payments;

import org.mapstruct.Mapper;
import pl.training.shop.commons.FastMoneyMapper;

@Mapper(componentModel = "spring", uses = FastMoneyMapper.class)
interface PaymentsMapper {

    PaymentDocument toDocument(PaymentDomain paymentDomain);

    PaymentDomain toDomain(PaymentDocument paymentDocument);

    PaymentDto toDto(PaymentDomain paymentDomain);

    PaymentDomain toDomain(PaymentDto paymentDto);

}
