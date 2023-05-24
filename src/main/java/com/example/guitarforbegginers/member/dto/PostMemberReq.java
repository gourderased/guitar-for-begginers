package com.example.guitarforbegginers.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Column;

@Getter
@AllArgsConstructor
public class PostMemberReq {

    private String loginId;
    private String password;
    private String email;

}
