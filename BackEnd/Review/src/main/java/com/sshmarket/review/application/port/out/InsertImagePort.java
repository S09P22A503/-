package com.sshmarket.review.application.port.out;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface InsertImagePort {
    void insertImages(List<MultipartFile> images);
}
