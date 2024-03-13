package com.occasionalbaker.bakersite.backend.controller;

import com.occasionalbaker.bakersite.views.homepage.FeedBackView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;

@Controller
public class FeedBackController {
    JavaMailSender mailSender;

    @Autowired
    public FeedBackController(JavaMailSender javaMailSender){
        this.mailSender = javaMailSender;
    }

    public void sendFeedback(String firstname,String lastname,String from,String to,String message){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject("Customer feedback from-"+" "+firstname+" "+lastname);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }
}
