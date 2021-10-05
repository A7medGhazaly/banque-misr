package com.banqueMisr.champion.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.banqueMisr.champion.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService{
	@Autowired
	JavaMailSender javaMailSender;
	@Override
	 public void sendMessage(
		      String to) {
		        SimpleMailMessage message = new SimpleMailMessage(); 
		        message.setFrom("raga2yAhmed@gmail.com");
		        message.setTo(to); 
		        message.setSubject("League Winner"); 
		        message.setText("Congratulation Champion");
		        javaMailSender.send(message);
		    }
}
