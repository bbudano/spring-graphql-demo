package hr.bbudano.customerservice.customer.controller;

import hr.bbudano.customerservice.customer.entity.Customer;
import hr.bbudano.customerservice.customer.service.CustomerService;
import hr.bbudano.customerservice.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

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

    @BatchMapping
    Mono<Map<Customer, List<OrderDto>>> orders(List<Customer> customers) {
        return customerService.getCustomerOrdersMap(customers);
    }

}
