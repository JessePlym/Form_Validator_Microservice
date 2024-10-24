package com.plymjesse.form_validator.service;

import org.springframework.stereotype.Service;

import com.plymjesse.form_validator.dto.Form;

@Service
public class FormProcesser {

  public void receiveForm(Form form) {
    System.out.println(form.toString());
  }

  public boolean processForm(Form form) {
    return true;
  }
}
