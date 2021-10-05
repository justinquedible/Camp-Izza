package com.campizza.backend.campizzabackend.security;

import com.campizza.backend.campizzabackend.model.PasswordToken;
import com.campizza.backend.campizzabackend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class SendEmail {
    @Autowired
    private JavaMailSender javaMailSender;



    public SendEmail() {

    }

    public void sendMail(PasswordToken token, User user){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Password Reset");
        mailMessage.setFrom("test-email@gmail.com");
        mailMessage.setText("To complete the password reset process, please click here: "
                + "https://rugged-sunbeam-229808.uc.r.appspot.com/#/updatePasswordRequest/"+token.getToken());

        javaMailSender.send(mailMessage);
    }
}
