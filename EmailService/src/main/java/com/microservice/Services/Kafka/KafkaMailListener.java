package com.microservice.Services.Kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.Dto.MailMessageDto;
import com.microservice.Services.MailSendingService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMailListener {

	private final MailSendingService mailSendingService;
	private final ObjectMapper objectMapper;
	private static final Logger log = LoggerFactory.getLogger(KafkaMailListener.class);

	public KafkaMailListener(MailSendingService mailSendingService, ObjectMapper objectMapper) {
		this.mailSendingService = mailSendingService;
		this.objectMapper = objectMapper;
	}

	@KafkaListener(topics = "auth-topic", groupId = "mail-group")
	public void listen(ConsumerRecord<String, String> message) {
		try {
			log.info("Message received: {}", message.value());
			MailMessageDto messageDto = objectMapper.readValue(message.value(), MailMessageDto.class);
			mailSendingService.sendMail(messageDto);
			log.info("Message sent successfully");
		} catch (Exception e) {
			log.error(e.getMessage());
		}

	}

}
