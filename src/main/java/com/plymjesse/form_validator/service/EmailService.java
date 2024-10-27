package com.plymjesse.form_validator.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

  private final JavaMailSender mailSender;
  private final String emailAddress;

  public EmailService(JavaMailSender mailSender, @Value("${EMAIL_ADDRESS}") String emailAddress) {
    this.mailSender = mailSender;
    this.emailAddress = emailAddress;
  }

  public void sendEmail(String to, String subject, String text) {
    MimeMessage message = mailSender.createMimeMessage();

    try {
      MimeMessageHelper helper = new MimeMessageHelper(message, true);
      helper.setFrom(emailAddress);
      helper.setTo(to);
      helper.setText(text);
      helper.setSubject(subject);
      mailSender.send(message);

    } catch (MessagingException me) {
      System.out.println("Error occured while sending mail:" + me.getMessage());
      throw new IllegalStateException("Failed to send email");

    } catch (MailAuthenticationException mae) {
      System.out.println("Authentication error occured while sending mail:" + mae.getMessage());
      throw new IllegalStateException("Failed to send email");

    } catch (Exception e) {
      System.out.println("Error occured while sending mail:" + e.getMessage());
      throw new IllegalStateException("Failed to send email");
    }

  }

}
