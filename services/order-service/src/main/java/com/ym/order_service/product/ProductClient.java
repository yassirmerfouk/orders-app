package com.ym.order_service.product;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductClient {

    private final RestTemplate restTemplate;

    @Value("${api.key}")
    private String apiKey;
    @Value("${api.secret}")
    private String apiSecret;

    public List<PurchaseResponse> purchase(List<PurchaseRequest> purchaseRequests){
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.set("X-API-KEY", apiKey);
        headers.set("X-API-SECRET", apiSecret);
        HttpEntity<List<PurchaseRequest>> request = new HttpEntity<>(purchaseRequests, headers);
        ResponseEntity<PurchaseResponse[]> response = restTemplate.exchange(
                "http://PRODUCT-SERVICE/api/products/purchase",
                HttpMethod.POST,
                request,
                PurchaseResponse[].class
        );
        return List.of(response.getBody());
    }
}
