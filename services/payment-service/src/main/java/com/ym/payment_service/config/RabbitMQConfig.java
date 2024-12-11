package com.ym.payment_service.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitMQConfig {

    @Value("${payment-confirmation.queue}")
    private String paymentQueueName;
    @Value("${payment-confirmation.exchange}")
    private String paymentExchangeName;
    @Value("${payment-confirmation.routing-key}")
    private String paymentRoutingName;

    private final ConnectionFactory connectionFactory;

    @Bean
    public RabbitAdmin rabbitAdmin(){
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.declareQueue(paymentQueue());
        rabbitAdmin.declareExchange(paymentExchange());
        rabbitAdmin.declareBinding(paymentBinding());
        return rabbitAdmin;
    }

    @Bean
    public Queue paymentQueue(){
        return new Queue(paymentQueueName);
    }

    @Bean
    public TopicExchange paymentExchange(){
        return new TopicExchange(paymentExchangeName);
    }

    @Bean
    public Binding paymentBinding(){
        return BindingBuilder
                .bind(paymentQueue())
                .to(paymentExchange())
                .with(paymentRoutingName);
    }

    @Bean
    public MessageConverter jsonConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonConverter());
        return rabbitTemplate;
    }
}
