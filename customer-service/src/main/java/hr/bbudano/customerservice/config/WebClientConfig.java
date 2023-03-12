package hr.bbudano.customerservice.config;

import hr.bbudano.customerservice.client.OrdersClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {

    @Value("${spring-graphql-demo.order-service.base-url}")
    private String orderServiceBaseUrl;

    @Bean
    public OrdersClient ordersClient() {
        var webClient = WebClient
                .builder()
                .baseUrl(orderServiceBaseUrl)
                .build();

        var httpServiceProxyFactory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(webClient))
                .build();

        return httpServiceProxyFactory.createClient(OrdersClient.class);
    }

}
