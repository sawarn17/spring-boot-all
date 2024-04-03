package com.appinventiv.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Configuration;



@Configuration
class RabbitConfig {
	
    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    
    @Bean
    public Queue createUserRegistrationQueue() {
    	//this bean queue is activated when we send the message in it.
        return new Queue("q.user-registration");
    }
}
