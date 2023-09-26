package com.sshmarket.auth.auth.adapter.out.persistent.aws.exception;

import com.sshmarket.auth.auth.exception.BusinessException;

public class S3UploadException extends BusinessException {

    public S3UploadException(String message) {
        super(message);
    }
}
