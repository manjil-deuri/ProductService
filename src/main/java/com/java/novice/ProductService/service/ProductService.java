package com.java.novice.ProductService.service;

import com.java.novice.ProductService.model.ProductRequest;
import com.java.novice.ProductService.model.ProductResponse;

public interface ProductService {
    ProductResponse addProduct(ProductRequest productRequest);

    ProductResponse getProductById(long productId) throws Throwable;

    void reduceQuantity(long productId, long quantity);
}
