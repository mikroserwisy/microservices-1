package pl.training.shop.payments.persistence;

import org.mapstruct.Mapper;
import pl.training.shop.payments.Payment;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Mapper(componentModel = "spring")
interface PaymentsPersistenceMapper {

    PaymentEntity toEntity(Payment payment);

    Payment toModel(PaymentEntity paymentEntity);

    default List<PropertyEntity> toEntity(Map<String, String> properties) {
        return properties.entrySet().stream()
                .map(entry -> new PropertyEntity(entry.getKey(), entry.getValue()))
                .collect(toList());
    }

    default Map<String, String> toModel(List<PropertyEntity> properties) {
        return properties.stream()
                .collect(toMap(PropertyEntity::getKey, PropertyEntity::getValue));
    }

}
