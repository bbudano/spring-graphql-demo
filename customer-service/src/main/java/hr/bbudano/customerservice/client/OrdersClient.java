package hr.bbudano.customerservice.client;

import hr.bbudano.customerservice.dto.OrderDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Flux;

import java.util.List;

@HttpExchange("/api/v1/orders")
public interface OrdersClient {

    @PostExchange
    Flux<OrderDto> orders(@RequestBody List<Integer> customers);

}
