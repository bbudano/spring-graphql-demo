package hr.bbudano.orderservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(path = "/api/v1/orders")
public class OrderDataController {

    private final OrderDataRepository orderDataRepository;

    public OrderDataController(OrderDataRepository orderDataRepository) {
        this.orderDataRepository = orderDataRepository;
    }

    @GetMapping
    Flux<OrderData> getByCustomerId(@RequestParam Integer customerId) {
        return orderDataRepository.getByCustomerId(customerId);
    }

}
