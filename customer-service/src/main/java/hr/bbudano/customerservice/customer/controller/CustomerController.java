package hr.bbudano.customerservice.customer.controller;

import hr.bbudano.customerservice.customer.entity.Customer;
import hr.bbudano.customerservice.customer.service.CustomerService;
import hr.bbudano.customerservice.dto.HeartbeatEvent;
import hr.bbudano.customerservice.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PreAuthorize("hasRole('ADMIN')")
    @MutationMapping
    Mono<Customer> addCustomer(@Argument String name) {
        return customerService.addCustomer(name);
    }

    @QueryMapping
    Flux<Customer> customers() {
        return customerService.getCustomers();
    }

    @QueryMapping
    Flux<Customer> customersByName(@Argument String name) {
        return customerService.getCustomersByName(name);
    }

    @QueryMapping
    Mono<Customer> customerById(@Argument Integer id) {
        return customerService.getCustomerById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @MutationMapping
    Mono<Customer> updateCustomer(@Argument Integer id, @Argument String name) {
        return customerService.updateCustomer(id, name);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @MutationMapping
    Mono<Integer> deleteCustomer(@Argument Integer id) {
        return customerService.deleteCustomer(id);
    }

//    @SchemaMapping(typeName = "Customer")
//    Flux<OrderDto> orders(Customer customer) {
//        return customerService.getCustomerOrders(customer);
//    }

    @BatchMapping
    Mono<Map<Customer, List<OrderDto>>> orders(List<Customer> customers) {
        return customerService.getCustomerOrdersMap(customers);
    }

    @SubscriptionMapping
    Flux<HeartbeatEvent> heartbeatEvents() {
        return Flux
                .fromStream(Stream.generate(() -> new HeartbeatEvent("heartbeat", Instant.now().toString())))
                .delayElements(Duration.ofSeconds(10L));
    }

}
