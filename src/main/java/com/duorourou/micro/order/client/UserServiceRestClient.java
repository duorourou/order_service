package com.duorourou.micro.order.client;

import com.duorourou.micro.users.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class UserServiceRestClient {

    @Autowired
    private RestTemplate restTemplate;

    public Optional<User> getUser(String userId) {

        User user = restTemplate.exchange(
                "http://users/users/{id}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<User>() {
                },
                userId).getBody();

        return Optional.ofNullable(user);
    }

}
