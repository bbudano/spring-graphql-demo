package hr.bbudano.customerservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final WebClient webClient;

    @Value("${spring-graphql-demo.order-service.base-url:order_service_base_url_not_set}")
    private String orderServiceBaseUrl;

    public CustomerController(CustomerRepository customerRepository, WebClient webClient) {
        this.customerRepository = customerRepository;
        this.webClient = webClient;
    }

    @MutationMapping
    Mono<Customer> addCustomer(@Argument String name) {
        return this.customerRepository.save(new Customer(null, name));
    }

    @QueryMapping
    Flux<Customer> customers() {
        return this.customerRepository.findAll();
    }

    @QueryMapping
    Flux<Customer> customersByName(@Argument String name) {
        return this.customerRepository.findAllByName(name);
    }

    @QueryMapping
    Mono<Customer> customerById(@Argument Integer id) {
        return this.customerRepository.findById(id);
    }

    @SchemaMapping(typeName = "Customer")
    Flux<Order> orders(Customer customer) {
        return webClient
                .get()
                .uri("/api/v1/orders?customerId=" + customer.id())
                .retrieve()
                .bodyToFlux(Order.class);
    }

}

record Order(Integer id, Integer customerId){}
