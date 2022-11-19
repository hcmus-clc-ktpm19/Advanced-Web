package org.hcmus.sakila.exception;

public class ActorNotFound extends NotFoundException {
  public ActorNotFound(String message) {
    super(message);
  }
}
