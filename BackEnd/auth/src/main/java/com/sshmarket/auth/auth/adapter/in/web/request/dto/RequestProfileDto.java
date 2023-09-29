package com.sshmarket.auth.auth.adapter.in.web.request.dto;

import com.sshmarket.auth.auth.adapter.in.web.request.valid.AllowedContentType;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RequestProfileDto {

    @AllowedContentType(allowedTypes = {"image/jpg", "image/jpeg", "image/png"},
            allowedExtensions = {"jpg", "jpeg", "png"},
            message = "jpg,jpeg,png 파일만 등록 가능합니다.")
    MultipartFile profile;

}
