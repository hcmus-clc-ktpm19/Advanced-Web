package org.hcmus.sakila.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.hcmus.sakila.model.dto.OutputMessageDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

public class WebSocketController extends AbstractApplicationController{

  @MessageMapping("/reset")
  @SendTo("/topic/messages")
  public OutputMessageDto reset() {
    String time = new SimpleDateFormat("yyyy dd MM HH:mm:ss").format(new Date());
    return new OutputMessageDto("Please refresh the lists now that the data has been updated!", time);
  }

}
