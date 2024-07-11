package com.xworkz.project.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

//this EmailConfi class to setup auto generate password and to set userName or email to send email from to, to whom I wanted to send
//@Component
@Component
public class EmailConfi {


    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        //this setUsername is the sender of email
        mailSender.setUsername("dhanushreegowda1999@gmail.com");
        //this setPassword is to set the one time password
        //the value inside() is the otp that generated in email that i have mention email above this otp is comes in the first when start to setup the password
        mailSender.setPassword("qjxu vinz wdkr zuqb");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
