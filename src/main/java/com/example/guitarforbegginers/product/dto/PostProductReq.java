package com.example.guitarforbegginers.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostProductReq {

    private String title;
    private String content;
    private String imgUrl;
    private Integer price;
    private Long CategoryId;
}
