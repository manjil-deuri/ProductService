package com.java.novice.ProductService.service;

import com.java.novice.ProductService.entity.Product;
import com.java.novice.ProductService.exception.ProductServiceCustomException;
import com.java.novice.ProductService.model.ProductRequest;
import com.java.novice.ProductService.model.ProductResponse;
import com.java.novice.ProductService.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductResponse addProduct(ProductRequest productRequest) {
        log.info("Inside addProduct");
        Product product = Product.builder()
                .productName(productRequest.getName())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .build();
        productRepository.save(product);
        log.info("Product created with id: {}", product.getProductId());
        return ProductResponse.builder().productId(product.getProductId()).build();
    }

    @Override
    public ProductResponse getProductById(long productId) {
        log.info("Inside getProductById");
        Product product = productRepository.findById(productId)
                .orElseThrow(
                        () -> new ProductServiceCustomException("Couldn't get product with Id: " + productId,"PRODUCT_NOT_FOUND"));
        ProductResponse productResponse = new ProductResponse();
        copyProperties(product, productResponse);
        log.info("Found product with id: {}", productId);
        return productResponse;
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
        log.info("Reduce Quantity {} for product id {}", quantity,productId);
        Product product = productRepository.findById(productId)
                .orElseThrow(
                        () -> new ProductServiceCustomException("Product with given Id: " + productId,"PRODUCT_NOT_FOUND"));

        if(product.getQuantity() < quantity ) {
            throw new ProductServiceCustomException("Product doesn't have sufficient quantity ","INSUFFICIENT_QUANTITY");
        }

        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
        log.info("Product quantity updated successfully");
    }
}
