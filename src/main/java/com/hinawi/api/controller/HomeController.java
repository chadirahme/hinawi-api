package com.hinawi.api.controller;


import com.hinawi.api.domains.Users;
import com.hinawi.api.dto.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    @GetMapping(value = "/test")
    public String test(@RequestParam("name") String name) {
        logger.debug("This is a debug message");
        logger.info("This is an info message");
        logger.warn("This is a warn message");
        logger.error("This is an error message");
        return "Message sent to the RabbitMQ JavaInUse Successfully";
    }

    @GetMapping(value = "/health")
    public String health() {
        logger.debug("This is a debug message");
        logger.info("This is an info message");
        logger.warn("This is a warn message");
        logger.error("This is an error message");
        return "Testing Health Successfully";
    }

}
