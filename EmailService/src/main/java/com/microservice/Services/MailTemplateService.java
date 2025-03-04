package com.microservice.Services;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Service
public class MailTemplateService {

	private final TemplateEngine templateEngine;

	public MailTemplateService(TemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}

	public String generateMailTemplate(String templateName, Map<String, Object> model) {
		Context context = new Context();
		context.setVariables(model);
		return templateEngine.process(templateName, context);
	}
}
