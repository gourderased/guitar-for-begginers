package com.example.guitarforbegginers.board;

import com.example.guitarforbegginers.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {


    @Query("select b from Board b")
    List<Board> findBoards();
}
