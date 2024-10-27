package com.plymjesse.form_validator.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.plymjesse.form_validator.dto.Form;

@Service
public class FormProcesser {

  private final EmailService emailService;
  private final String email;

  public FormProcesser(EmailService emailService, @Value("${FROM_EMAIL}") String email) {
    this.emailService = emailService;
    this.email = email;
  }

  public void receiveForm(Form form) {
    System.out.println(form.toString());
  }

  public List<String> getErrorMessages(BindingResult bindingResult) {
    return bindingResult.getAllErrors()
        .stream()
        .map(error -> {
          if (error instanceof FieldError) {
            return error.getDefaultMessage();
          } else {
            return error.getObjectName() + ": " + error.getDefaultMessage();
          }
        })
        .collect(Collectors.toList());
  }

  public String sendForm(Form form) {
    try {
      emailService.sendEmail(email, "Message from " + form.getFirstName() + " " + form.getLastName(),
          form.getMessage() + "\n\n" + form.getEmail());
      return "OK";

    } catch (IllegalStateException ise) {
      return ise.getMessage();
    }
  }
}
