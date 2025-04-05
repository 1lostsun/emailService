package com.microservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailMessageDto {

	private String to;
	private String username;
	private String template;
	private String subject;
	private String body;

	public String getTo() {
		return to;
	}

	public String getUsername() {
		return username;
	}

	public String getTemplate() {
		return template;
	}

	public String getSubject() {
		return subject;
	}

	public String getBody() {
		return body;
	}
}
