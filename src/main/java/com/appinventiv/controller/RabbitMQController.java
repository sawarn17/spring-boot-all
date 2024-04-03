package com.appinventiv.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.appinventiv.Message;

@RestController
public class RabbitMQController {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@PostMapping(value = "/send", produces = "application/json", consumes = "application/json")
	public ResponseEntity<String> sendMessage(@RequestBody Message payload) {
		rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
		rabbitTemplate.convertAndSend("message-queue", payload);
		return ResponseEntity.ok("Message sent successfully");
	}

}
