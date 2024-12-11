package com.ym.product_service.product;

import com.ym.product_service.category.CategoryRepository;
import com.ym.product_service.exception.CustomException;
import com.ym.product_service.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.String.*;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    private final ProductMapper productMapper;

    public Long addProduct(ProductRequest productRequest){
        if(!categoryRepository.existsById(productRequest.getCategoryId()))
            throw new EntityNotFoundException(format("Category %d not found.", productRequest.getCategoryId()));
        Product product = productMapper.mapToProduct(productRequest);
        productRepository.save(product);
        return product.getId();
    }

    public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> purchaseRequests){
        List<Long> productRequestIds = purchaseRequests
                .stream().map(PurchaseRequest::getProductId)
                .toList();
        List<Product> storedProducts = productRepository.findAllByIdInOrderById(productRequestIds);
        // Check if all products requested exists in db
        if(purchaseRequests.size() > storedProducts.size())
            throw new CustomException("Products not matched.");
        // Verify if all product quantity requested exists in db
        Map<Long, PurchaseRequest> mapOfPurchases = purchaseRequests.stream().collect(Collectors.toMap(
                PurchaseRequest::getProductId,
                element -> element
        ));
        List<PurchaseResponse> responses = storedProducts.stream().map(product -> {
            PurchaseRequest request = mapOfPurchases.get(product.getId());
            if(product.getAvailableQuantity() < request.getQuantity())
                throw new CustomException(format("Quantity for product %d not available.", request.getProductId()));
            int newQuantity = product.getAvailableQuantity() - request.getQuantity();
            product.setAvailableQuantity(newQuantity);
            return productMapper.mapToPurchaseResponse(product, request.getQuantity());
        }).toList();
        productRepository.saveAll(storedProducts);
        return responses;
    }

    public ProductResponse getProductById(Long id){
        return productRepository.findById(id)
                .map(productMapper::mapToProductResponse)
                .orElseThrow(
                        () -> new EntityNotFoundException(format("Product %d not found.", id))
                );
    }

    public List<ProductResponse> getProducts(){
        return productRepository.findAll().stream()
                .map(productMapper::mapToProductResponse)
                .toList();
    }
}
