package com.appinventiv.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    
    private final CachingConnectionFactory connectionFactory;
    
    public RabbitConfig(CachingConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }
    
    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    
    @Bean
    public Queue createUserRegistrationQueue() {
        // This bean queue is activated when we send the message in it.
        return new Queue("q.user-registration");
    }
    
    @Bean
    public RabbitTemplate rabbitTemplate(Jackson2JsonMessageConverter jackson2JsonMessageConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jackson2JsonMessageConverter);
        return template;
    }
}
