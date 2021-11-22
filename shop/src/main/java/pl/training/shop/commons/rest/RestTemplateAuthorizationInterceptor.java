package pl.training.shop.commons.rest;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
public class RestTemplateAuthorizationInterceptor implements ClientHttpRequestInterceptor {

    @Value("${api.token}")
    @Setter
    private String token;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().set(AUTHORIZATION, "Bearer " + token);
        return execution.execute(request, body);
    }

}
