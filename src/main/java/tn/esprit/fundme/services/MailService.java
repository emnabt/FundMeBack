

package tn.esprit.fundme.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.google.zxing.WriterException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import tn.esprit.fundme.QRCodeGenerator;
import tn.esprit.fundme.entities.Agent;
import tn.esprit.fundme.entities.User;



@Service
public class MailService {

    

	/*
	 * The Spring Framework provides an easy abstraction for sending email by using
	 * the JavaMailSender interface, and Spring Boot provides auto-configuration for
	 * it as well as a starter module.
	 */
	private JavaMailSender javaMailSender;
	private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/QRCode.png";



	public MailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void sendEmail(Agent user) throws MailException, MessagingException, WriterException, IOException {

		
		MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        
 	int code = (int) (Math.random() * ( 9999 ));		
		
		SimpleMailMessage mail = new SimpleMailMessage();
        //mail.setFrom("no-reply@memorynotfound.com");
		mail.setTo("elyes.ghrairi@esprit.tn");
		mail.setSubject("VERIFICATION DU COMPTE");
		String msg = "veuillez entrer le code verification ou le code QR(ci-desssous) :      "+code;

		String x = String.valueOf(code);
		 QRCodeGenerator.generateQRCodeImage(x, 350, 350, QR_CODE_IMAGE_PATH);
		 
		 
        helper.addAttachment("QRCode.png", new ClassPathResource("QRCode.png"));
        String inlineImage = "<img src=\"cid:QRCode.png\"></img><br/>";

        helper.setText(inlineImage + msg, true);
        helper.setSubject(mail.getSubject());
        helper.setTo(mail.getTo());

        javaMailSender.send(message);
        
		
	}



	/**
	 * This fucntion is used to send mail that contains a attachment.
	 * 
	 * @param user
	 * @throws MailException
	 * @throws MessagingException
	 */
	public void sendEmailWithAttachment(Agent user) throws MailException, MessagingException {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

		helper.setTo("elyes.ghrairi@esprit.tn");
		helper.setSubject("Testing Mail API with Attachment");
		helper.setText("Please find the attached document below.");

		ClassPathResource classPathResource = new ClassPathResource("Q.png");
		helper.addAttachment(classPathResource.getFilename(), classPathResource);

		javaMailSender.send(mimeMessage);
	}
public void sendEmaildest(User user) throws MailException {

		

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo("ahmed.kridiss@esprit.tn");
		mail.setSubject("Testing Mail API");
		mail.setText("you recieved");
		

		javaMailSender.send(mail);
	}

public void sendEmailemet(User user) throws MailException {

	

	SimpleMailMessage mail = new SimpleMailMessage();
	mail.setTo("ahmed.kridiss@esprit.tn");
	mail.setSubject("Testing Mail API");
	mail.setText("you sent ");
	


	javaMailSender.send(mail);
}

}
