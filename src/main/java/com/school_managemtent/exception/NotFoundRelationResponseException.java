package com.school_managemtent.exception;

public class NotFoundRelationResponseException extends RuntimeException {

  private String username;
    public NotFoundRelationResponseException(String message, String username) {
        super(message);
        this.username = username;
    }

  public String getUsername() {
    return username;
  }
}
