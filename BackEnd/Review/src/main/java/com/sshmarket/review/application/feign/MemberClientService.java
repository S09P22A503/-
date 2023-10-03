package com.sshmarket.review.application.feign;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberClientService {

    private final MemberClient memberClient;

}
