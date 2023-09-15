package com.sshmarket.trade.dto;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TradeCreateRequestDto {

    @NotNull(message = "판매글 정보를 입력해주세요.")
    private Long articleId;
    @NotNull(message = "구매자 정보를 입력해주세요.")
    private Long buyerId;
}
