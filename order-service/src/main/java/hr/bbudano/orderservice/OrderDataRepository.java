package hr.bbudano.orderservice;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface OrderDataRepository extends ReactiveCrudRepository<OrderData, Integer> {

    Flux<OrderData> getByCustomerId(Integer customerId);

}
