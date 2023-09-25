package com.sshmarket.trade.exception;

public class ExpiredTokenException extends BusinessException {

    public ExpiredTokenException(String message) {
        super(401, message);
    }
}
