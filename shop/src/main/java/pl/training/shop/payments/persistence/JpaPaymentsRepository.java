package pl.training.shop.payments.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface JpaPaymentsRepository extends JpaRepository<PaymentEntity, String> {
}
