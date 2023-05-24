package com.example.guitarforbegginers.product.dto;

import com.example.guitarforbegginers.category.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostProductRes {
    private Long id;
    private String title;
}
