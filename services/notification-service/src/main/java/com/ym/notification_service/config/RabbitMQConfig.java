package com.ym.notification_service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${order-confirmation.queue}")
    private String orderQueueName;
    @Value("${order-confirmation.exchange}")
    private String orderExchangeName;
    @Value("${order-confirmation.routing-key}")
    private String orderRoutingName;

    @Value("${payment-confirmation.queue}")
    private String paymentQueueName;
    @Value("${payment-confirmation.exchange}")
    private String paymentExchangeName;
    @Value("${payment-confirmation.routing-key}")
    private String paymentRoutingName;

    @Bean
    public Queue orderQueue(){
        return new Queue(orderQueueName);
    }

    @Bean
    public TopicExchange orderExchange(){
       return new TopicExchange(orderExchangeName);
    }

    @Bean
    public Binding orderBinding(){
        return BindingBuilder
                .bind(orderQueue())
                .to(orderExchange())
                .with(orderRoutingName);
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
    public Binding binding(){
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
