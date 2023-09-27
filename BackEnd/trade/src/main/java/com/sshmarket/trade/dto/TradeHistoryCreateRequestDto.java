package com.sshmarket.trade.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TradeHistoryCreateRequestDto {

    @NotNull
    private Integer price;

    @NotBlank
    private String mainImage;

    @NotBlank
    private String title;

}
