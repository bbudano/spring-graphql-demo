package hr.bbudano.orderservice;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/orders")
public class OrderDataController {

    private final OrderDataRepository orderDataRepository;

    public OrderDataController(OrderDataRepository orderDataRepository) {
        this.orderDataRepository = orderDataRepository;
    }

    @PostMapping
    Flux<OrderData> getByCustomers(@RequestBody List<Integer> customers) {
        return Flux
                .fromStream(customers.stream().map(orderDataRepository::getByCustomerId))
                .flatMap(o -> o);
    }

}
