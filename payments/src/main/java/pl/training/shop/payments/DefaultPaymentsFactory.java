package pl.training.shop.payments;

import org.mapstruct.factory.Mappers;

public class DefaultPaymentsFactory implements PaymentsFactory {

    private static final PaymentIdGenerator PAYMENT_ID_GENERATOR = new UUIDPaymentIdGenerator();
    private static final PaymentsMapper PAYMENTS_MAPPER = Mappers.getMapper(PaymentsMapper.class);

    @Override
    public ProcessPaymentUseCase create(TimeProvider timeProvider, PaymentsWriter paymentsWriter) {
        var writerAdapter = new PaymentsWriterAdapter(PAYMENTS_MAPPER, paymentsWriter);
        var processPaymentService = new ProcessPaymentService(PAYMENT_ID_GENERATOR, timeProvider, writerAdapter);
        return new ProcessPaymentUseCaseAdapter(PAYMENTS_MAPPER, processPaymentService);
    }

    @Override
    public GetPaymentUseCase create(PaymentsReader paymentsReader) {
        var readerAdapter = new PaymentsReaderAdapter(PAYMENTS_MAPPER, paymentsReader);
        var getPaymentService = new GetPaymentService(readerAdapter);
        return new GetPaymentUseCaseAdapter(PAYMENTS_MAPPER, getPaymentService);
    }

}
