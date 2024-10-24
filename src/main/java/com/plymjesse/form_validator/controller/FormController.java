package com.plymjesse.form_validator.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.plymjesse.form_validator.dto.Form;
import com.plymjesse.form_validator.service.FormProcesser;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/")
public class FormController {

  // Form form = new Form(" ", "Plym", "plymjesse@gmail.com", "This is a
  // message");

  private final FormProcesser processer;

  public FormController(FormProcesser processer) {
    this.processer = processer;
  }

  @PostMapping
  public ResponseEntity<String> home(@Valid @RequestBody Form form, BindingResult bindingResult) {
    System.out.println(bindingResult);
    if (bindingResult.hasErrors()) {
      return ResponseEntity.badRequest().body("Validation failed");
    }
    return ResponseEntity.ok(form.toString());
  }
}
