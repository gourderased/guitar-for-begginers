package com.example.guitarforbegginers.board;


import com.example.guitarforbegginers.board.dto.GetBoardRes;
import com.example.guitarforbegginers.board.dto.PostBoardReq;
import com.example.guitarforbegginers.board.dto.PostBoardRes;
import com.example.guitarforbegginers.config.BaseException;
import com.example.guitarforbegginers.config.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("board")
public class BoardController {

    private final BoardService boardService;

    /**
     * 게시글 등록
     */
    @PostMapping("/create")
    public BaseResponse<PostBoardRes> createBoard(@RequestBody PostBoardReq postBoardReq) {
        System.out.println("controller");
        try {
            return new BaseResponse<>(boardService.createBoard(postBoardReq));
        } catch(BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
    /**
     * 게시글 하나 조회
     */
    @GetMapping("/read/{id}")
    public BaseResponse<GetBoardRes> getBoard(@PathVariable Long id) {
        try {
            return new BaseResponse<>(boardService.getBoard(id));
        } catch(BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 게시글 전체 조회
     */
    @GetMapping("/read")
    public List<GetBoardRes> getBoards(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size) {
        try {
            PageRequest pageRequest = PageRequest.of(page, size);
            Page<GetBoardRes> boardPage = boardService.getPagingBoards(pageRequest);
            return boardPage.getContent();
        } catch (BaseException exception) {
            throw new RuntimeException(exception);
        }
    }
    /**
     * 게시글 삭제
     */
    @DeleteMapping("delete/{id}")
    public BaseResponse<String> deleteBoard(@PathVariable Long id) {
        try {
            return new BaseResponse<>(boardService.deleteBoard(id));
        } catch (BaseException exception)  {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
