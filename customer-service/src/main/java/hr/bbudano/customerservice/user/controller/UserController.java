package hr.bbudano.customerservice.user.controller;

import hr.bbudano.customerservice.user.entity.User;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.Map;

@Controller
public class UserController {

    @QueryMapping
    Mono<Map<String, Object>> me(Authentication authentication) {
        var user = (User) authentication.getPrincipal();

        Map<String, Object> me = Map.of(
                "username", user.getUsername(),
                "role", user.getRole()
        );

        return Mono.just(me);
    }

}
