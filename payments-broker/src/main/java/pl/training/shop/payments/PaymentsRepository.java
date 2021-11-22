package pl.training.shop.payments;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

interface PaymentsRepository extends ReactiveMongoRepository<PaymentDocument, String> {
}
