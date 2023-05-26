package com.example.guitarforbegginers.board;

import com.example.guitarforbegginers.board.dto.GetBoardRes;
import com.example.guitarforbegginers.board.dto.PatchBoardReq;
import com.example.guitarforbegginers.board.dto.PostBoardReq;
import com.example.guitarforbegginers.board.dto.PostBoardRes;
import com.example.guitarforbegginers.config.BaseException;
import com.example.guitarforbegginers.member.Member;
import com.example.guitarforbegginers.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
            board.createBoard(postBoardReq.getTitle(), postBoardReq.getContent(), member);
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
            Board board = boardRepository.findById(id).orElseThrow();
            board.updateBoard(patchBoardReq.getTitle(), patchBoardReq.getContent());
            return board.getTitle();
        } catch(Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    /**
     * 게시글 하나 조회
     */
    public GetBoardRes getBoard(Long id) throws BaseException {
        try {
            Board board = boardRepository.findById(id).orElseThrow();
            Member member = board.getMember();
            String memberLoginId = member.getLoginId();
            return new GetBoardRes(board.getId(), board.getTitle(), board.getContent(), board.getMember().getLoginId(), board.getCreateDate(), board.getModifiedDate());
        } catch(Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
    /**
     * 게시글 전체 조회
     */
    public List<GetBoardRes> getBoards() throws BaseException {
        try {
            List<Board> boards = boardRepository.findBoards();
            List<GetBoardRes> getBoardRes = boards.stream()
                    .map(board -> new GetBoardRes(board.getId(), board.getTitle(), board.getContent(), board.getMember().getLoginId(), board.getCreateDate(), board.getModifiedDate()))
                    .collect(Collectors.toList());
            return getBoardRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


    /**
     * 게시글 삭제
     */
    @Transactional
    public String deleteBoard(Long id) throws BaseException {
        try {
            Board board = boardRepository.findById(id).orElseThrow();

            boardRepository.delete(board);
            return "삭제 완료!";
        } catch(Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


}
