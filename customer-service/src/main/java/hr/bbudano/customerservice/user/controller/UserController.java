package hr.bbudano.customerservice.user.controller;

import hr.bbudano.customerservice.user.dto.UserProfile;
import hr.bbudano.customerservice.user.entity.User;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class UserController {

    @QueryMapping
    Mono<UserProfile> me(Authentication authentication) {
        var user = (User) authentication.getPrincipal();

        return Mono.just(new UserProfile(user.getId(), user.getUsername(), user.getRole()));
    }

}
