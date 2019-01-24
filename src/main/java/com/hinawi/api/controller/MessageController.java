package com.hinawi.api.controller;

import com.hinawi.api.domains.Users;
import com.hinawi.api.domains.WebMessages;
import com.hinawi.api.dto.ApiResponse;
import com.hinawi.api.services.UserService;
import com.hinawi.api.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Calendar;

//https://github.com/stomp-js/ng4-stompjs-demo/blob/sockjs/src/app/app.module.ts
//https://www.devglan.com/spring-boot/spring-boot-angular-websocket


@Controller
public class MessageController {
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    UserService userService;

    @MessageMapping("hello")
    @SendTo("/topic/hi")
    public ApiResponse<String> sendTopicMessage(WebMessages webMessages) throws Exception {
        int count= addWebMessages(webMessages);
        logger.info("topic>> " + webMessages.getUserName());
        String msg="Hi, " + webMessages.getUserName() + "!";
        return new ApiResponse<>(HttpStatus.OK.value(),
                "Websocket message send successfully.", count);
    }

   // @RequestMapping(value= Constants.ADD_MESSAGE, method= RequestMethod.POST)
    private int addWebMessages(WebMessages webMessages) {
        try {
            Calendar cal = Calendar.getInstance();
            webMessages.setMessageDate(cal.getTime());
            webMessages.setStatusId(1);
            WebMessages dbwebMessages = userService.addWebMessages(webMessages);
            return userService.getWebMessages().size();
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
            return 0;
        }
    }

//    @MessageMapping("/hello")
//    @SendTo("/topic/hi")
//    public Hello greeting(User user) throws Exception {
//        return new Hello("Hi, " + user.getName() + "!");
//    }
}
