package com.example.guitarforbegginers;

import com.example.guitarforbegginers.board.BoardService;
import com.example.guitarforbegginers.board.dto.GetBoardRes;
import com.example.guitarforbegginers.config.BaseException;
import com.example.guitarforbegginers.member.Member;
import com.example.guitarforbegginers.member.MemberService;
import com.example.guitarforbegginers.member.dto.GetMemberRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final MemberService memberService;
    private final BoardService boardService;

    /**
     * 메인 페이지
     */
    @GetMapping("/")
    public String index(HttpSession session, Model model)  {

        boolean isLogin;
       if(session.getAttribute("isLogin") == null) {
           isLogin = false;
       }
       else {
           isLogin =true;
       }
        int memberCount = memberService.findMemberCount();
        if(memberCount != 0 && isLogin) {

            Long id = (Long) session.getAttribute("memberId");

            Member member = memberService.findByIdAndReturnMember(id);

            model.addAttribute("loginId", member.getLoginId());
            model.addAttribute("isLogin", true);
            model.addAttribute("isLogout", false);

            if(member.getStatus() == 1) {
                model.addAttribute("isManger", false);
            }
            else model.addAttribute("isManger", true);
        }
        else {
            model.addAttribute("loginId", "");
            model.addAttribute("id", "");
            model.addAttribute("isLogout", true);
            model.addAttribute("isManger", false);
        }
        return "index";
    }
    /**
     * 로그인
     */
    @GetMapping("/member/log-in")
    public String memberLogin() {
        return "member-login";
    }
    /**
     * 회원가입
     */
    @GetMapping("/member/create")
    public String memberCreate() {
        return "member-signup";
    }
    /**
     * 내 정보
     */
    @GetMapping("/member/my-info")
    public String memberMyInfo(HttpSession session, Model model) {
        Long id = (Long)session.getAttribute("memberId");
        Member member = memberService.findByIdAndReturnMember(id);
        model.addAttribute("member", member);
        return "member-info";
    }

    /**
     * 관리자 페이지
     */
    @GetMapping("/manager")
    public String memberMyInfo() {
        return "manager";
    }
    /**
     * 관리자 페이지 - 회원 관리
     */
    @GetMapping("/manager-member")
    public String manager_member(Model model) throws BaseException {

        List<GetMemberRes> getMemberRes = memberService.getMembers();
        model.addAttribute("Members", getMemberRes);
        return "manager-member";
    }
    /**
     * 관리자 페이지 - 게시글 관리
     */
    @GetMapping("/manager-post")
    public String manager_post(Model model) throws BaseException {

        List<GetBoardRes> getBoardRes = boardService.getBoards();
        model.addAttribute("boards", getBoardRes);
        return "manager-post";
    }


    /**
     * 상품 목록 - 일렉
     */

    /**
     * 상품 목록 - 베이스
     */
    /**
     * 상품 목록 - 어쿠스틱
     */

    /**
     * 상품 상세화면
     */

    /**
     * 장바구니 화면
     */

    /**
     * 게시판 목록
     */
    @GetMapping("/board")
    public String board(Model model) throws BaseException {
        model.addAttribute("boards", boardService.getBoards());
        return "board-list";
    }
    /**
     * 게시글 작성
     */
    @GetMapping("/board-save")
    public String board_save(HttpSession session,Model model) throws BaseException {
        Long id = (Long)session.getAttribute("memberId");
        model.addAttribute("memberId", id);
        return "board-save";
    }
    /**
     * 게시글 수정
     */
    @GetMapping("/board-update/{id}")
    public String board_update(@PathVariable Long id, HttpSession session,Model model) throws BaseException {
        GetBoardRes getBoardRes= boardService.getBoard(id);
        Long memberId = (Long)session.getAttribute("memberId");

        model.addAttribute("board", getBoardRes);
        model.addAttribute("memberId",id);
        return "board-update";
    }
    /**
     * 로그아웃
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
