package pl.training.shop.commons.keycloak;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CustomDecisionVoter implements AccessDecisionVoter<FilterInvocation> {

    private final Map<String, List<GrantedAuthority>> rules = Map.of(
            "/**", List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))
    );

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, FilterInvocation filterInvocation, Collection<ConfigAttribute> collection) {
        var url = filterInvocation.getRequestUrl().replaceAll("\\?.*", "");
        var authorities = authentication.getAuthorities();
        var antMatcher = new AntPathMatcher();
        var key = rules.keySet().stream()
                .filter(pattern -> antMatcher.match(pattern, url))
                .findFirst();
        if (key.isPresent()) {
            var roles = rules.get(key.get());
            if (roles.isEmpty()) {
                return ACCESS_GRANTED;
            }
            return roles.stream().anyMatch(authorities::contains) ? ACCESS_GRANTED : ACCESS_DENIED;
        }
        return ACCESS_DENIED;
    }

}
