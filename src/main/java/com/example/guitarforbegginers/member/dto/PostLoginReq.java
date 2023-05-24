package com.example.guitarforbegginers.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostLoginReq {
    private String loginId;
    private String password;
}
