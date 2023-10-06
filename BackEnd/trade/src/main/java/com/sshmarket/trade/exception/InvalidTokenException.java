package com.sshmarket.trade.exception;

public class InvalidTokenException extends BusinessException {

    public InvalidTokenException(String message) {
        super(401, message);
    }
}
