package com.example.guitarforbegginers.board;


import com.example.guitarforbegginers.member.Member;
import com.example.guitarforbegginers.utils.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Board extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;


    @ManyToOne
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    Board createBoard( String content,  Member member) {
        this.content = content;
        this.member = member;
        return this;
    }

    public void updateBoard( String content) {
        this.content = content;
    }
}
