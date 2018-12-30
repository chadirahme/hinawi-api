package com.hinawi.api.controller;

import com.hinawi.api.domains.Users;
import com.hinawi.api.dto.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

//https://github.com/stomp-js/ng4-stompjs-demo/blob/sockjs/src/app/app.module.ts
//https://www.devglan.com/spring-boot/spring-boot-angular-websocket


@Controller
public class MessageController {
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @MessageMapping("hello")
    @SendTo("/topic/hi")
    public ApiResponse<String> greeting(Users user) throws Exception {
        logger.info("topic>> " + user.getUserName());
        String msg="Hi, " + user.getUserName() + "!";
        return new ApiResponse<>(HttpStatus.OK.value(),
                "Customers balance fetched successfully.", msg);
    }

//    @MessageMapping("/hello")
//    @SendTo("/topic/hi")
//    public Hello greeting(User user) throws Exception {
//        return new Hello("Hi, " + user.getName() + "!");
//    }
}
