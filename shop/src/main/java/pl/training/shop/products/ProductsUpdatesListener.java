package pl.training.shop.products;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@Log
class ProductsUpdatesListener implements Consumer<String> {

    @Override
    public void accept(String message) {
        log.info("New message: " + message);
    }

}
