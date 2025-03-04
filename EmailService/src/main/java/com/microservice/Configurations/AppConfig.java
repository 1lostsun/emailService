package com.microservice.Configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Value("${app.mail}")
	private String mail;

	public String getMail() {
		return mail;
	}
}
