package org.hcmus.sakila.model.dto;

import lombok.Data;

@Data
public class OutputMessageDto {
  String message;
  String time;

  public OutputMessageDto(String message, String time) {
    this.message = message;
    this.time = time;
  }
}
