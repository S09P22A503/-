package com.sshmarket.trade.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TradeHistoryCreateRequestDto {

    @NotBlank
    private int price;

    @NotBlank
    private String mainImage;

    @NotBlank
    private String title;

}
