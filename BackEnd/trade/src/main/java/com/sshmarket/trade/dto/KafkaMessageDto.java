package com.sshmarket.trade.dto;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class KafkaMessageDto implements Serializable {

    @NotBlank(message = "메시지를 입력하세요.")
    private String message;

    @NotNull
    private Long tradeId;

    @NotNull
    private Long memberId;
}
