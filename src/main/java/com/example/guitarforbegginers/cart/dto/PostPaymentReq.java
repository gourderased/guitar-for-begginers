package com.example.guitarforbegginers.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostPaymentReq {
    private Long memberId;
    private Long productId;
    private int price;
    private int quantity;
}
