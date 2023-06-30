package com.example.guitarforbegginers.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {


    @Query("select b from Board b")
    List<Board> findBoards();

    @Query("SELECT b FROM Board b")
    Page<Board> findPagingBoards(Pageable pageable);
}
