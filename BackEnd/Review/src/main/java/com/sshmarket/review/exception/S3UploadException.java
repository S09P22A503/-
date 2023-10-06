package com.sshmarket.review.exception;

import com.sshmarket.review.adapter.out.persistence.S3Uploader;

public class S3UploadException extends RuntimeException {

    public S3UploadException(String message) {
        super(message);
    }

}
