package pl.training.shop.payments;

public interface ProcessPaymentUseCase {

    Payment process(PaymentRequest paymentRequest);

}
