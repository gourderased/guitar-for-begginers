package com.example.guitarforbegginers.Board;


import com.example.guitarforbegginers.Board.dto.PatchBoardReq;
import com.example.guitarforbegginers.Board.dto.PostBoardReq;
import com.example.guitarforbegginers.Board.dto.GetBoardRes;
import com.example.guitarforbegginers.Board.dto.PostBoardRes;
import com.example.guitarforbegginers.config.BaseException;
import com.example.guitarforbegginers.member.Member;
import com.example.guitarforbegginers.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.guitarforbegginers.config.BaseResponseStatus.DATABASE_ERROR;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    /**
     * 글 등록
     */
    @Transactional
    public PostBoardRes createBoard(PostBoardReq postBoardReq) throws BaseException {
        try{
            Member member = memberRepository.findMemberById(postBoardReq.getMemberLoginId());
            Board board = new Board();
            board.createBoard(postBoardReq.getTitle(), postBoardReq.getContent(), postBoardReq.getView(), member);
            boardRepository.save(board);
            return new PostBoardRes(board.getTitle());
        } catch(Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    /**
     * 글 수정
     */
    @Transactional
    public String modifyBoard(Long id, PatchBoardReq patchBoardReq) throws BaseException {
        try{
            Board board = boardRepository.getReferenceById(id);
            board.updateBoard(patchBoardReq.getTitle(), patchBoardReq.getContent());
            return board.getTitle();
        } catch(Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public GetBoardRes getBoard(Long id) throws BaseException {
        try {
            Board board = boardRepository.getReferenceById(id);
            Member member = board.getMember();
            String memberLoginId = member.getLoginId();
            return new GetBoardRes(board.getTitle(), board.getContent(), board.getView(), memberLoginId);
        } catch(Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    /**
     * 게시글 삭제
     */
    @Transactional
    public String deleteBoard(Long id) throws BaseException {
        try {
            Board board = boardRepository.getReferenceById(id);
            boardRepository.delete(board);
            return "삭제 완료!";
        } catch(Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


}
