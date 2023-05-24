package com.example.guitarforbegginers.Board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class PostBoardReq {

    private String title;
    private String content;
    private int view;
    private Long memberLoginId;
}
