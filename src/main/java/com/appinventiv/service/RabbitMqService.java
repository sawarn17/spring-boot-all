package com.appinventiv.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.appinventiv.Message;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RabbitMqService {
	
	 @RabbitListener(queues = {"message-queue"})
	  public void onUserRegistration(Message event) {
	            log.info("User Registration Event Received: :::::::::::::{}", event);
	    }
}
