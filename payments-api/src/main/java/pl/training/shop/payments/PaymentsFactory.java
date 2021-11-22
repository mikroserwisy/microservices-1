package pl.training.shop.payments;

public interface PaymentsFactory {

    ProcessPaymentUseCase create(TimeProvider timeProvider, PaymentsWriter paymentsWriter);

    GetPaymentUseCase create(PaymentsReader paymentsReader);

}
