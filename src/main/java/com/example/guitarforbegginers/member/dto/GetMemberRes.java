package com.example.guitarforbegginers.member.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetMemberRes {
    private Long id;
    private String loginId;
    private String email;
    private int status;
}
