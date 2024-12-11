package com.ym.notification_service.amqp;

import com.ym.notification_service.email.EmailService;
import com.ym.notification_service.notification.NotificationService;
import com.ym.notification_service.notification.NotificationType;
import com.ym.notification_service.order.OrderConfirmation;
import com.ym.notification_service.payment.PaymentConfirmation;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ConfirmationConsumer {

    private final NotificationService notificationService;

    private final EmailService emailService;

    @RabbitListener(queues = {"${order-confirmation.queue}"}, ackMode = "AUTO")
    public void consumeOrderConfirmation(@Payload OrderConfirmation orderConfirmation) {
        notificationService.addNotification(NotificationType.ORDER_CONFIRMATION);
        try{
            emailService.sendEmail(
                    orderConfirmation.getCustomer().getEmail(),
                    orderConfirmation.getCustomer().getFullName(),
                    NotificationType.ORDER_CONFIRMATION,
                    Map.of(
                            "reference", orderConfirmation.getReference(),
                            "totalAmount", orderConfirmation.getTotalAmount(),
                            "paymentMethod", orderConfirmation.getPaymentMethod(),
                            "date", orderConfirmation.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")),
                            "customer", orderConfirmation.getCustomer(),
                            "products", orderConfirmation.getLines()
                    )
            );
        }catch (MessagingException exception){
            System.out.println("cannot send email to : " + orderConfirmation.getCustomer().getEmail());
        }
    }

    @RabbitListener(queues = {"${payment-confirmation.queue}"})
    public void consumePaymentConfirmation(@Payload PaymentConfirmation paymentConfirmation) throws MessagingException{
        notificationService.addNotification(NotificationType.PAYMENT_CONFIRMATION);
        emailService.sendEmail(
                paymentConfirmation.getEmail(),
                paymentConfirmation.getFirstName() + " " + paymentConfirmation.getLastName(),
                NotificationType.PAYMENT_CONFIRMATION,
                Map.of(
                        "amount", paymentConfirmation.getAmount(),
                        "paymentMethod", paymentConfirmation.getPaymentMethod(),
                        "orderReference", paymentConfirmation.getOrderReference(),
                        "date", paymentConfirmation.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")),
                        "firstName", paymentConfirmation.getFirstName(),
                        "lastName", paymentConfirmation.getLastName(),
                        "email", paymentConfirmation.getEmail()
                )
        );
    }
}
