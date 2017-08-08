package com.duorourou.micro.order;

import com.duorourou.micro.order.client.UserService;
import com.duorourou.micro.order.client.UserServiceRestClient;
import com.duorourou.micro.order.domain.Task;
import com.duorourou.micro.users.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableFeignClients
public class OrderServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }


    @Autowired
    private UserServiceRestClient userServiceRestClient;

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/tasks")
    public List<Task> listUserTasks(@RequestParam(name = "userId", defaultValue = "") String userId) {

        Optional<User> userOptional = userServiceRestClient.getUser(userId);

        User user = userService.getUser(userId);
        System.out.println(user);
        Task task = new Task();
        task.setUserId(userId);
        task.setUserName(userOptional.map(User::getName).orElse("None"));
        return Arrays.asList(task);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
