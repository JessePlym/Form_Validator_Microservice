package com.plymjesse.form_validator.dto;

import jakarta.validation.constraints.NotEmpty;

public class Form {

  @NotEmpty
  private String firstName;
  private String lastName;
  private String email;
  private String message;

  public Form() {
  }

  public Form(String firstName, String lastName, String email, String message) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.message = message;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public String getEmail() {
    return this.email;
  }

  public String getMessage() {
    return this.message;
  }

  @Override
  public String toString() {
    return this.firstName + " " + this.lastName + "\n" + this.message + "\n" + this.email;
  }
}
