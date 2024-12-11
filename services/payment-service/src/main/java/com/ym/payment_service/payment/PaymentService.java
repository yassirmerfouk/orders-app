package com.ym.payment_service.payment;

import com.ym.payment_service.amqp.PaymentProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    private final PaymentMapper paymentMapper;

    private final PaymentProducer paymentProducer;

    public Long addPayment(PaymentRequest paymentRequest){
        Payment payment = paymentMapper.mapToPayment(paymentRequest);
        paymentRepository.save(payment);
        paymentProducer.sendPaymentConfirmation(paymentMapper
                .mapToPaymentConfirmation(payment, paymentRequest));
        return payment.getId();
    }
}
