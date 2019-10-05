package com.talent518.demo.mail;

import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@Service
public class MailService {
	@Autowired
	private JavaMailSender mailSender;

	@Value("${spring.mail.username}")
	private String mailFrom;

	public void send(String from, String to, String subject, String content) throws MessagingException {
		send(from, to, subject, content, false);
	}
	
	public void send(String from, String to, String subject, String content, boolean isHtml) throws MessagingException {
		send(from, to, subject, content, null, null, isHtml);
	}

	public void send(String from, String to, String subject, String content, File[] files, String[] names) throws MessagingException {
		send(from, to, subject, content, files, names, false);
	}
	
	public void send(String from, String to, String subject, String content, File[] files, String[] names, boolean isHtml) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(mailFrom);
		helper.setReplyTo(from);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(content, isHtml);
		
		if(files != null) {
			for(int i = 0; i< files.length; i++) {
				System.out.println(files[i].exists() + ", " + files[i].getAbsolutePath());
				helper.addAttachment(names == null ? files[i].getName() : names[i], new FileSystemResource(files[i]));
			}
		}
		
		mailSender.send(message);
	}
	
	@Autowired
	FreeMarkerConfigurer freeMarkerConfigurer;
	
	public String template(String view, Object model) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		Template template = freeMarkerConfigurer.getConfiguration().getTemplate(view);
		
		return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
	}
}
