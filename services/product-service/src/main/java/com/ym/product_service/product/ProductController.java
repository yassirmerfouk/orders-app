package com.ym.product_service.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<?> addProduct(
            @Valid @RequestBody ProductRequest productRequest
    ){
        return new ResponseEntity<>(
                productService.addProduct(productRequest),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseProducts(
          @Valid @RequestBody List<@Valid PurchaseRequest> purchaseRequests
    ){
        return new ResponseEntity<>(
                productService.purchaseProducts(purchaseRequests),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(
            @PathVariable Long id
    ){
        return new ResponseEntity<>(
                productService.getProductById(id),
                HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts(){
        return new ResponseEntity<>(
                productService.getProducts(),
                HttpStatus.OK
        );
    }
}
