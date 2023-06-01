package com.example.guitarforbegginers.cart.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetCartRes {
    private Long productId;
    private String title;
    private String imgUrl;
    private int price;
    private Long quantity;
}
