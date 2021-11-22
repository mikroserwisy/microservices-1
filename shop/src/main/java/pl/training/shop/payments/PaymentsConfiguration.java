package pl.training.shop.payments;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class PaymentsConfiguration {

    private static final PaymentsFactory PAYMENTS_FACTORY = new DefaultPaymentsFactory();

    @Bean
    ProcessPaymentUseCase processPaymentUseCase(TimeProvider timeProvider, PaymentsWriter paymentsWriter) {
        return PAYMENTS_FACTORY.create(timeProvider, paymentsWriter);
    }

    @Bean
    GetPaymentUseCase getPaymentUseCase(PaymentsReader paymentsReader) {
        return PAYMENTS_FACTORY.create(paymentsReader);
    }

}
