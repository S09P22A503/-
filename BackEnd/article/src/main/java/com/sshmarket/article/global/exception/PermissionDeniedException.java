package com.sshmarket.article.global.exception;

public class PermissionDeniedException extends BusinessException {

    public PermissionDeniedException(String message) {
        super(403, message);
    }
}
