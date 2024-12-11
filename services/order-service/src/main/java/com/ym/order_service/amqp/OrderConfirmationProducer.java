package com.ym.order_service.amqp;

import com.ym.order_service.order.OrderConfirmation;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderConfirmationProducer {

    @Value("${order-confirmation.exchange}")
    private String orderExchangeName;
    @Value("${order-confirmation.routing-key}")
    private String orderRoutingName;

    private final RabbitTemplate rabbitTemplate;

    public void sendOrderConfirmation(OrderConfirmation orderConfirmation){
        rabbitTemplate.convertAndSend(orderExchangeName, orderRoutingName, orderConfirmation);
    }
}
