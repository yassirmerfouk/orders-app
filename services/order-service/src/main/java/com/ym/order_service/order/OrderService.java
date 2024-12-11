package com.ym.order_service.order;

import com.ym.order_service.amqp.OrderConfirmationProducer;
import com.ym.order_service.customer.CustomerClient;
import com.ym.order_service.customer.CustomerResponse;
import com.ym.order_service.exception.EntityNotFoundException;
import com.ym.order_service.payment.PaymentClient;
import com.ym.order_service.payment.PaymentMapper;
import com.ym.order_service.product.ProductClient;
import com.ym.order_service.product.PurchaseRequest;
import com.ym.order_service.product.PurchaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    private final CustomerClient customerClient;

    private final ProductClient productClient;

    private final OrderConfirmationProducer confirmationProducer;

    private final PaymentClient paymentClient;

    private final PaymentMapper paymentMapper;

    public Long addOrder(OrderRequest orderRequest){
        // Check id customer exists
        CustomerResponse customer = customerClient.getCustomerById(orderRequest.getCustomerId());
        // Purchase products from product-service
        List<PurchaseRequest> purchaseRequests = orderRequest.getOrderLines().stream()
                .map(line -> new PurchaseRequest(line.getProductId(), line.getQuantity()))
                .toList();
        List<PurchaseResponse> purchaseResponses = productClient.purchase(purchaseRequests);
        // Persist Order
        Order order = orderMapper.mapToOrder(orderRequest, purchaseResponses);
        orderRepository.save(order);
        // Send payment request to paymentService
        paymentClient.addPayment(
                paymentMapper.mapToPaymentRequest(order, customer)
        );
        // Send confirmation to the broker queue
        confirmationProducer.sendOrderConfirmation(
                orderMapper.mapToOrderConfirmation(order, customer, purchaseResponses)
        );
        return order.getId();
    }

    public OrderResponse getOrderById(Long id){
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(format("Order %d not found.", id)));
        CustomerResponse customer = customerClient.getCustomerById(order.getCustomerId());
        return orderMapper.mapToOrderResponse(order, customer);
    }

    public List<OrderResponse> getOrders(){
        List<Order> orders = orderRepository.findAll();
        List<Long> ids = orders.stream().map(Order::getCustomerId).distinct().toList();
        Map<Long, CustomerResponse> customers = customerClient.getCustomersByIds(ids).stream()
                        .collect(Collectors.toMap(
                                CustomerResponse::getId,
                                key -> key
                        ));
        return orders.stream()
                .map(order -> {
                    CustomerResponse customer = customers.getOrDefault(order.getCustomerId(), null);
                    return orderMapper.mapToOrderResponse(order, customer);
                }).toList();
    }
}
