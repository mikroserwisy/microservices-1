package pl.training.shop;

import org.mapstruct.factory.Mappers;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import pl.training.shop.commons.FastMoneyMapper;
import pl.training.shop.commons.rest.RestTemplateAuthorizationInterceptor;
import springfox.documentation.spring.web.plugins.Docket;

import static springfox.documentation.builders.RequestHandlerSelectors.withClassAnnotation;
import static springfox.documentation.spi.DocumentationType.OAS_30;

@EnableFeignClients
@EnableTransactionManagement
@EnableAspectJAutoProxy
@Configuration
class ShopConfiguration {

    @Bean
    Docket docket() {
        return new Docket(OAS_30)
                .select()
                .apis(withClassAnnotation(RestController.class))
                .build();
    }

    @Bean
    FastMoneyMapper fastMoneyMapper() {
        return Mappers.getMapper(FastMoneyMapper.class);
    }

    @LoadBalanced
    @Bean
    RestTemplate restTemplate(RestTemplateAuthorizationInterceptor authorizationInterceptor) {
        return new RestTemplateBuilder()
                .additionalInterceptors(authorizationInterceptor)
                .build();
    }

}
