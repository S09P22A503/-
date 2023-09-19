package com.sshmarket.review.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }
}
