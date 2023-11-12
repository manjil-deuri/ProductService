package com.java.novice.ProductService.exception;

import lombok.Data;

@Data
public class ProductServiceCustomException extends RuntimeException{
    private String errorMsg;
    private String errorCode;

    public ProductServiceCustomException(String errorMsg, String errorCode) {
        this.errorMsg=errorMsg;
        this.errorCode=errorCode;
    }
}
