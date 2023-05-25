package com.example.guitarforbegginers.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostMemberReq {

    private String loginId;
    private String password;
    private String email;

}
