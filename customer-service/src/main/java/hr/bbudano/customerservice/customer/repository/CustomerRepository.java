package hr.bbudano.customerservice.customer.repository;

import hr.bbudano.customerservice.customer.entity.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {

    Flux<Customer> findAllByName(String name);

}
