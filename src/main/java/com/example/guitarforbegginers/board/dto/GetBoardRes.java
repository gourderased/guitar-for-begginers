package com.example.guitarforbegginers.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class GetBoardRes {
    private Long id;
    private String content;
    private String MemberLoginId;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;
}
