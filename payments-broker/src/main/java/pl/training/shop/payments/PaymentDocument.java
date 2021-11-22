package pl.training.shop.payments;

import lombok.Data;
import org.javamoney.moneta.FastMoney;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
class PaymentDocument {

    @Id
    private String id;
    private Long requestId;
    private FastMoney value;
    private String status;

}
