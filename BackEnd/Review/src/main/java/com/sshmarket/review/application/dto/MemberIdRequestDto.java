package com.sshmarket.review.application.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberIdRequestDto {

    private List<Long> idList;
}
