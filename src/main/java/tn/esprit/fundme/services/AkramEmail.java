package tn.esprit.fundme.services;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import tn.esprit.fundme.entities.User;

@Service
public class AkramEmail {
	
	@Autowired
	private JavaMailSender mailSender ;
	
	public void sendEmail(User user) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("");
		message.setText("aa");
		message.setSubject("aa");
		mailSender.send(message);
		
		System.out.println("Mail Sent successfully..");
	}

}
