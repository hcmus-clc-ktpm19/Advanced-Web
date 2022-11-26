package org.hcmus.sakila.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.hcmus.sakila.model.dto.OutputMessageDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
public class WebSocketController extends AbstractApplicationController{

  @MessageMapping("/reset") // /app/reset
  @SendTo("/topic/messages") // send to all user subscribe to this topic
  public OutputMessageDto reset(@Payload OutputMessageDto message){
    System.out.println("From controller " +  message);
    return message;
  }

}
