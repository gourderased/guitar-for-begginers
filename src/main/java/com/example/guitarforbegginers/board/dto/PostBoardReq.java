package com.example.guitarforbegginers.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class PostBoardReq {
    private String content;
    private Long memberId;
}
