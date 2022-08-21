package hr.bbudano.springgraphqldemo;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
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

}
