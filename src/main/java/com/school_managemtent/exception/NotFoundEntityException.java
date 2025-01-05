package com.school_managemtent.exception;

public class NotFoundEntityException extends RuntimeException {

  private String username;
    public NotFoundEntityException(String message, String username) {
        super(message);
        this.username = username;
    }

  public String getUsername() {
    return username;
  }
}
