package pl.training.shop.commons.keycloak;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FeignTokenInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        getTokenHeader().ifPresent(tokenHeader -> requestTemplate.header(HttpHeaders.AUTHORIZATION, tokenHeader));
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
