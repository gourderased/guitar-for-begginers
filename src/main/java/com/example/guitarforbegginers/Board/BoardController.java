package com.example.guitarforbegginers.Board;

import com.example.guitarforbegginers.Board.dto.*;
import com.example.guitarforbegginers.config.BaseException;
import com.example.guitarforbegginers.config.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
     * 게시글 수정
     */
    @PatchMapping("/update/{id}")
    public BaseResponse<String> modifyBoard(@PathVariable Long id, @RequestBody PatchBoardReq patchBoardReq) {
        try {
            return new BaseResponse<>(boardService.modifyBoard(id, patchBoardReq));
        } catch(BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
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
