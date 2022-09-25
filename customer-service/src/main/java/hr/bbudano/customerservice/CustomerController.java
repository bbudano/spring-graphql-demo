package hr.bbudano.customerservice;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final WebClient webClient;

    @MutationMapping
    Mono<Customer> addCustomer(@Argument String name) {
        return this.customerRepository.save(new Customer(name));
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

    @MutationMapping
    Mono<Customer> updateCustomer(@Argument Integer id, @Argument String name) {
        return this.customerRepository
                .findById(id)
                .flatMap(customer -> {
                    customer.setName(name);
                    return this.customerRepository.save(customer);
                });
    }

    @MutationMapping
    Mono<Integer> deleteCustomer(@Argument Integer id) {
        return this.customerRepository
                .findById(id)
                .flatMap(customer -> this.customerRepository
                        .delete(customer)
                        .thenReturn(id));
    }

    @SchemaMapping(typeName = "Customer")
    Flux<Order> orders(Customer customer) {
        return webClient
                .get()
                .uri("/api/v1/orders?customerId=" + customer.getId())
                .retrieve()
                .bodyToFlux(Order.class);
    }

}
