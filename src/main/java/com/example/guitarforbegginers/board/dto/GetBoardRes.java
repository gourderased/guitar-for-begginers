package com.example.guitarforbegginers.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetBoardRes {
    private String title;
    private String content;
    private int view;
    private String MemberLoginId;
}
