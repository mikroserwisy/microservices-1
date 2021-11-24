package pl.training.shop.commons.keycloak;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class RestTemplateTokenInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] body, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        getTokenHeader().ifPresent(tokenHeader -> httpRequest.getHeaders().set(HttpHeaders.AUTHORIZATION, tokenHeader));
        return clientHttpRequestExecution.execute(httpRequest, body);
    }

    @SuppressWarnings("unchecked")
    private Optional<String> getTokenHeader() {
        KeycloakAuthenticationToken keycloakAuthenticationToken;
        try {
            keycloakAuthenticationToken = (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
            var keycloakPrincipal = (KeycloakPrincipal<KeycloakSecurityContext>) keycloakAuthenticationToken.getPrincipal();
            var keycloakSecurityContext = keycloakPrincipal.getKeycloakSecurityContext();
            var token = keycloakSecurityContext.getToken();
            return Optional.of(String.format("%s %s", token.getType(), keycloakSecurityContext.getTokenString()));
        } catch (ClassCastException exception) {
            return Optional.empty();
        }
    }

}
