package com.example.guitarforbegginers.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetProductRes {
    private Long id;
    private String title;
    private String content;
    private String imgUrl;
    private int price;

}
