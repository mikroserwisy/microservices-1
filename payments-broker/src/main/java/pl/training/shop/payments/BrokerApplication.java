package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log
@RequiredArgsConstructor
public class BrokerApplication implements ApplicationRunner {

    private final PaymentsService paymentsService;

    public static void main(String[] args) {
        SpringApplication.run(BrokerApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        paymentsService.getPaymentsStream()
                .subscribe(payment -> log.info(payment.toString()), throwable -> log.info(throwable.toString()));
    }

}
