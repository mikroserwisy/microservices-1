package pl.training.shop.orders;

public class ServiceUnavailableException extends RuntimeException{

    public ServiceUnavailableException(Throwable cause) {
        super(cause);
    }

}
