package com.example.guitarforbegginers.member.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class GetMemberRes {
    private Long id;
    private String loginId;
    private String email;
    private String password;
    private int status;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
