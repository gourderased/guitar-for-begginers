package com.example.guitarforbegginers.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class PostBoardReq {

    private String title;
    private String content;
    private Long memberLoginId;
}
