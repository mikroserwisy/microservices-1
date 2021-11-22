package pl.training.shop.payments.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.training.shop.payments.Payment;
import pl.training.shop.payments.PaymentsReader;
import pl.training.shop.payments.PaymentsWriter;

import java.util.Optional;

@Component
@RequiredArgsConstructor
class PaymentsReaderWriterAdapter implements PaymentsReader, PaymentsWriter {

    private final PaymentsPersistenceMapper mapper;
    private final JpaPaymentsRepository paymentsRepository;

    @Override
    public Optional<Payment> getById(String id) {
        return paymentsRepository.findById(id)
                .map(mapper::toModel);
    }

    @Override
    public Payment save(Payment payment) {
        var entity = mapper.toEntity(payment);
        return mapper.toModel(paymentsRepository.save(entity));
    }

}
