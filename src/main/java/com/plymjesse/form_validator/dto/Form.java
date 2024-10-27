package com.plymjesse.form_validator.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Form {

  @NotEmpty(message = "Name must not be null")
  @NotBlank(message = "Name must not be blank")
  @Size(max = 25, message = "Name must be maximum of 25 letters")
  @Pattern(regexp = "^[A-Za-z\\s-]+$", message = "Name must contain only letters, spaces, and dashes")
  private String firstName, lastName;

  @Email(message = "Must be an email")
  private String email;

  @NotEmpty(message = "Message must not be null")
  @NotBlank(message = "Message must not be blank")
  @Size(max = 500, message = "Message must be maximum of 500 characters")
  @Size(min = 1, message = "Message must be at least 1 character")
  @Pattern(regexp = "^[A-Za-z\\s-\\?\\.:!'\",_]+$", message = "Message contains invalid characters")
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
