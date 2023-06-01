package com.example.guitarforbegginers.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class GetCartRepoRes {
    private Long memberId;
    private Long productId;
    private Long productCount;

}
