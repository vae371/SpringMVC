package com.me.app1.service;

import java.io.IOException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class MailService {
	
//	@Autowired
	private JavaMailSenderImpl sender = new JavaMailSenderImpl();


	public void sendSimpleMail(String username, String password, String from, String to, String subject, String content) {
//		sender.setHost("smtp.163.com");
		sender.setHost("smtp.gmail.com");
		sender.setPort(587);
		sender.setProtocol("smtp");
		sender.setUsername(username);
		sender.setPassword(password);
		Properties pro = new Properties();
		pro.setProperty("mail.smtp.auth", "true");
		pro.setProperty("mail.smtp.starttls.enable", "true");
       //pro.setProperty("mail.smtp.ssl.enable", "true");
       //pro.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
 
		sender.setJavaMailProperties(pro);
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(from);
		msg.setTo(to);
		msg.setReplyTo(from);
		msg.setSubject(subject);
		msg.setText(content);
		sender.send(msg);
	}
}