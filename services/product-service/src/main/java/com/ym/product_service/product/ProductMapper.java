package com.ym.product_service.product;

import com.ym.product_service.category.Category;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product mapToProduct(ProductRequest productRequest){
        if(productRequest == null)
            return null;
        return Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .availableQuantity(productRequest.getAvailableQuantity())
                .price(productRequest.getPrice())
                .category(
                        Category.builder()
                                .id(productRequest.getCategoryId())
                                .build()
                ).build();
    }

    public ProductResponse mapToProductResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .availableQuantity(product.getAvailableQuantity())
                .price(product.getPrice())
                .categoryId(product.getCategory().getId())
                .categoryName(product.getCategory().getName())
                .categoryDescription(product.getCategory().getDescription())
                .build();
    }

    public PurchaseResponse mapToPurchaseResponse(Product product, int quantity){
        return PurchaseResponse.builder()
                .productId(product.getId())
                .productName(product.getName())
                .price(product.getPrice())
                .quantity(quantity)
                .categoryName(product.getCategory().getName())
                .build();
    }
}
