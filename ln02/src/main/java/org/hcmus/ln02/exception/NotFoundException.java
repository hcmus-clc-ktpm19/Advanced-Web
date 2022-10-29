package org.hcmus.ln02.exception;

public abstract class NotFoundException extends RuntimeException {
  protected NotFoundException(String message) {
    super(message);
  }
}
