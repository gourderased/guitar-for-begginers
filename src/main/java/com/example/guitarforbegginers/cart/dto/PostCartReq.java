package com.example.guitarforbegginers.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostCartReq {
    private Long memberId;
    private Long productId;
}
