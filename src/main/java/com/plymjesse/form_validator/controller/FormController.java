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

import java.util.List;

@RestController
@RequestMapping("/api/form")
public class FormController {

  private final FormProcesser processer;

  public FormController(FormProcesser processer) {
    this.processer = processer;
  }

  @PostMapping("/submit")
  public ResponseEntity<String> home(@Valid @RequestBody Form form, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      List<String> errors = processer.getErrorMessages(bindingResult);

      return ResponseEntity.badRequest().body(String.join(", ", errors));
    }

    String responseMsg = processer.sendForm(form);

    if (!responseMsg.equals("OK")) {
      return ResponseEntity.badRequest().body(responseMsg);
    }

    return ResponseEntity.ok(responseMsg);

  }
}
