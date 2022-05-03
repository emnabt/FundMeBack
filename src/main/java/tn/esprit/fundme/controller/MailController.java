package tn.esprit.fundme.controller;



import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.WriterException;

import tn.esprit.fundme.services.IAgentService;
import tn.esprit.fundme.services.MailService;
import tn.esprit.fundme.entities.Agent;



@RestController
public class MailController {

	@Autowired
	private MailService mailService;

	@Autowired(required=false)
	private Agent user;
	
	@Autowired
	IAgentService agentService; 

	
	@RequestMapping("/send-mail")
	public String send() throws MessagingException {

		/*
		 * Creating a User with the help of User class that we have declared and setting
		 * Email address of the sender.
		 */
		user=agentService.retrieveAgent((long) 15) ;
		
		
		user.getLogAgent().setEmail("elyes.ghrairi@esprit.tn");  //Receiver's email address
		/*
		 * Here we will call sendEmail() for Sending mail to the sender.
		 */
		try {
			mailService.sendEmail(user);
		} catch (MailException mailException) {
			System.out.println(mailException);
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String msg= "Congratulations! Your mail has been send to the user."+ user.getLogAgent().getFirstName();
		return msg;
	}

	/**
	 * 
	 * @return
	 * @throws MessagingException
	 */
	@RequestMapping("/send-mail-attachment")
	public String sendWithAttachment() throws MessagingException {

		user.getLogAgent().setEmail("elyes.ghrairi@esprit.tn"); 

		try {
			mailService.sendEmailWithAttachment(user);
		} 
		catch (MailException mailException) {
			System.out.println(mailException);
		}
		return "Congratulations! Your mail has been send to the user.";
	}
}




