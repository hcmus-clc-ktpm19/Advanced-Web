package org.hcmus.sakila.controller;

import lombok.extern.slf4j.Slf4j;
import org.hcmus.sakila.model.dto.OutputMessageDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class WebSocketController extends AbstractApplicationController{

  @MessageMapping("/reset")
  @SendTo("/topic/messages") // send to all user subscribe to this topic
  public OutputMessageDto reset(@Payload OutputMessageDto message){
    log.debug("Message from controller: {}", message);
    return message;
  }
}
