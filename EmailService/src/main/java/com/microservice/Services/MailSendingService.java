package com.microservice.Services;

import com.microservice.Configurations.AppConfig;
import com.microservice.Dto.MailMessageDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MailSendingService {

	private final JavaMailSender mailSender;
	private final String mail;
	private final MailTemplateService mailTemplateService;

	public MailSendingService(JavaMailSender mailSender, AppConfig appConfig, MailTemplateService mailTemplateService) {
		this.mailSender = mailSender;
		this.mail = appConfig.getMail();
		this.mailTemplateService = mailTemplateService;
	}

	public void sendMail(MailMessageDto mailMessageDto) throws MessagingException {
		Map<String, Object> model = new HashMap<>();
		model.put("username", mailMessageDto.getUsername());

		String htmlContent = mailTemplateService.generateMailTemplate("email-template", model);

		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

		mimeMessageHelper.setFrom(mail);
		mimeMessageHelper.setTo(mailMessageDto.getTo());
		mimeMessageHelper.setSubject(mailMessageDto.getSubject());
		mimeMessageHelper.setText(htmlContent, true);

		mailSender.send(mimeMessage);
	}
}
