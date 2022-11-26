package hr.bbudano.customerservice.customer.service;

import hr.bbudano.customerservice.customer.entity.Customer;
import hr.bbudano.customerservice.customer.repository.CustomerRepository;
import hr.bbudano.customerservice.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final WebClient webClient;

    public Mono<Customer> addCustomer(String name) {
        return this.customerRepository.save(new Customer(name));
    }

    public Flux<Customer> getCustomers() {
        return this.customerRepository.findAll();
    }

    public Flux<Customer> getCustomersByName(String name) {
        return this.customerRepository.findAllByName(name);
    }

    public Mono<Customer> getCustomerById(Integer id) {
        return this.customerRepository.findById(id);
    }

    public Mono<Customer> updateCustomer(Integer id, String name) {
        return this.customerRepository
                .findById(id)
                .flatMap(customer -> {
                    customer.setName(name);
                    return this.customerRepository.save(customer);
                });
    }

    public Mono<Integer> deleteCustomer(Integer id) {
        return this.customerRepository
                .findById(id)
                .flatMap(this.customerRepository::delete)
                .thenReturn(id);
    }

    public Flux<OrderDto> getCustomerOrders(Customer customer) {
        return webClient
                .get()
                .uri("/api/v1/orders?customerId=" + customer.getId())
                .retrieve()
                .bodyToFlux(OrderDto.class);
    }

}
