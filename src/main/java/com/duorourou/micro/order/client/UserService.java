package com.duorourou.micro.order.client;

import com.duorourou.micro.users.domain.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "users")
public interface UserService {

    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET, produces = "application/json")
    User getUser(@PathVariable("userId") String userId);
}
