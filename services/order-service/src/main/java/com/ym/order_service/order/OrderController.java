package com.ym.order_service.order;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<?> addOrder(
          @Valid @RequestBody OrderRequest orderRequest
    ){
        return new ResponseEntity<>(
                orderService.addOrder(orderRequest),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getOrders(){
        return new ResponseEntity<>(
                orderService.getOrders(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(
            @PathVariable Long id
    ){
        return new ResponseEntity<>(
                orderService.getOrderById(id),
                HttpStatus.OK
        );
    }
}
