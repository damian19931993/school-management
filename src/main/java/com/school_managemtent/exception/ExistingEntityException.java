package com.school_managemtent.exception;


public class ExistingEntityException extends RuntimeException {
  private String username;

  public ExistingEntityException(String message, String username) {
    super(message);
    this.username = username;
  }

  public String getUsername() {
    return username;
  }
}