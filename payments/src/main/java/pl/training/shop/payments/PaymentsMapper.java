package pl.training.shop.payments;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
interface PaymentsMapper {

    @Mapping(source = "requestId", target = "id")
    PaymentRequestDomain toDomain(PaymentRequest paymentRequest);

    Payment toModel(PaymentDomain paymentDomain);

    PaymentDomain toDomain(Payment payment);

}
