package com.hinawi.api.controller;


import com.hinawi.api.domains.Users;
import com.hinawi.api.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/home/")
public class HomeController {
    @GetMapping(value = "/test")
    public String test(@RequestParam("name") String name) {
        return "Message sent to the RabbitMQ JavaInUse Successfully";
    }

}
