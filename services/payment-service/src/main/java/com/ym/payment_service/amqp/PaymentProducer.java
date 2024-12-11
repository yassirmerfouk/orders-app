package com.ym.payment_service.amqp;

import com.ym.payment_service.payment.PaymentConfirmation;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentProducer {

    @Value("${payment-confirmation.exchange}")
    private String paymentExchangeName;
    @Value("${payment-confirmation.routing-key}")
    private String paymentRoutingName;

    private final RabbitTemplate rabbitTemplate;

    public void sendPaymentConfirmation(PaymentConfirmation paymentConfirmation){
        rabbitTemplate.convertAndSend(paymentExchangeName, paymentRoutingName, paymentConfirmation);
    }
}
