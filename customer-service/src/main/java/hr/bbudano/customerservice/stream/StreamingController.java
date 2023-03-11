package hr.bbudano.customerservice.stream;

import hr.bbudano.customerservice.dto.HeartbeatEvent;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

@Controller
public class StreamingController {

    @SubscriptionMapping
    Flux<HeartbeatEvent> heartbeatEvents() {
        return Flux
                .fromStream(Stream.generate(() -> new HeartbeatEvent("heartbeat", Instant.now().toString())))
                .delayElements(Duration.ofSeconds(10L));
    }

}
