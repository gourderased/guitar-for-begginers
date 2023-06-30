package com.example.guitarforbegginers.board;

import com.example.guitarforbegginers.board.dto.GetBoardRes;
import com.example.guitarforbegginers.board.dto.PostBoardReq;
import com.example.guitarforbegginers.board.dto.PostBoardRes;
import com.example.guitarforbegginers.config.BaseException;
import com.example.guitarforbegginers.member.Member;
import com.example.guitarforbegginers.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
            Member member = memberRepository.findMemberById(postBoardReq.getMemberId());
            Board board = new Board();
            board.createBoard( postBoardReq.getContent(), member);
            boardRepository.save(board);
            return new PostBoardRes(board.getContent());
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
            return new GetBoardRes(board.getId(),  board.getContent(), board.getMember().getLoginId(), board.getCreateDate(), board.getModifiedDate());
        } catch(Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
    /**
     * 게시글 전체 조회(페이징 처리 X)
     */
    public List<GetBoardRes> getBoards() throws BaseException {
        try {
            List<Board> boards = boardRepository.findBoards();
            return boards.stream()
                    .map(board -> new GetBoardRes(board.getId(),  board.getContent(), board.getMember().getLoginId(), board.getCreateDate(), board.getModifiedDate()))
                    .collect(Collectors.toList());
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
    /**
     * 게시글 전체 조회(페이징 처리 O)
     */
    public Page<GetBoardRes> getPagingBoards(Pageable pageable) throws BaseException {
        try {
            Page<Board> boardPage = boardRepository.findPagingBoards(pageable);
            List<GetBoardRes> getBoardRes = boardPage.getContent().stream()
                    .map(board -> new GetBoardRes(board.getId(),  board.getContent(), board.getMember().getLoginId(), board.getCreateDate(), board.getModifiedDate()))
                    .collect(Collectors.toList());
            return new PageImpl<>(getBoardRes, pageable, boardPage.getTotalElements());
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
