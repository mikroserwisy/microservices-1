package pl.training.shop.payments.persistence;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.javamoney.moneta.FastMoney;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@NamedEntityGraph(name = PaymentEntity.SELECT_WITH_PROPERTIES, attributeNodes = @NamedAttributeNode("properties"))
@NamedQuery(name = PaymentEntity.SELECT_WITH_VALUE_GREATER_THEN, query = "select p from PaymentEntity p where p.value >= :value")
@Table(name = "PAYMENTS")
@Entity
@EqualsAndHashCode(of = "id")
@Data
class PaymentEntity {

    public static final String SELECT_WITH_PROPERTIES = "paymentsWithProperties";
    public static final String SELECT_WITH_VALUE_GREATER_THEN = "paymentsWithValueGreaterThen";

    @Id
    private String id;
    private FastMoney value;
    private Instant timestamp;
    private String status;
    @JoinColumn
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PropertyEntity> properties;

}
