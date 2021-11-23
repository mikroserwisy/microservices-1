package pl.training.shop.commons;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

@Component
@Endpoint(id = "shop-health-endpoint")
public class ShopHealthEndpoint {

    @ReadOperation
    public Map<String, String> getInfo() {
       return Map.of("status", "ok", "timestamp", LocalDateTime.now().toString());
    }

}
