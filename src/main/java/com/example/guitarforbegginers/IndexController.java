package com.example.guitarforbegginers;

import com.example.guitarforbegginers.board.BoardService;
import com.example.guitarforbegginers.board.dto.GetBoardRes;
import com.example.guitarforbegginers.cart.CartService;
import com.example.guitarforbegginers.cart.dto.GetCartRes;
import com.example.guitarforbegginers.config.BaseException;
import com.example.guitarforbegginers.member.Member;
import com.example.guitarforbegginers.member.MemberService;
import com.example.guitarforbegginers.member.dto.GetMemberRes;
import com.example.guitarforbegginers.product.ProductService;
import com.example.guitarforbegginers.product.dto.GetProductRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final MemberService memberService;
    private final BoardService boardService;
    private final ProductService productService;
    private final CartService cartService;
    /**
     * 메인 페이지
     */
    @GetMapping("/")
    public String index(HttpSession session, Model model)  {
        //로그인 한 상태인지 확인
        boolean isLogin;

        if(session.getAttribute("isLogin") == null) {
           isLogin = false;
        }
        else {
            isLogin =true;
        }

        if(isLogin) {
            Long id = (Long) session.getAttribute("memberId");
            Member member = memberService.findByIdAndReturnMember(id);
            model.addAttribute("loginId", member.getLoginId());
            model.addAttribute("isLogin", true);
            model.addAttribute("isLogout", false);
            if(member.getRole() == 1) {
                model.addAttribute("isManager", false);
            }
            else {
                model.addAttribute("isManager", true);
            }
        }
        else {
            model.addAttribute("loginId", "");
            model.addAttribute("id", "");
            model.addAttribute("isLogout", true);
            model.addAttribute("isManager", false);
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
        boolean isLogin;

        if(session.getAttribute("isLogin") == null) {
            isLogin = false;
        }
        else {
            isLogin =true;
        }
        if(isLogin) {
            Long id = (Long)session.getAttribute("memberId");
            Member member = memberService.findByIdAndReturnMember(id);
            model.addAttribute("member", member);
            return "member-info";
        }
        else {
            return "member-login";
        }
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
    @GetMapping("/product-electric")
    public  String product_electric(HttpSession session, Model model) throws BaseException {
        //로그인 한 상태인지 확인
        boolean isLogin;

        if(session.getAttribute("isLogin") == null) {
            isLogin = false;
        }
        else {
            isLogin =true;
        }
        // 상품 받아오기
        List<GetProductRes> getProductRes = productService.getProducts(1L);

        Long id;
        if(isLogin) {
            // 멤버 id 받아오기
            id = (Long) session.getAttribute("memberId");
            //navbar
            Member member = memberService.findByIdAndReturnMember(id);
            model.addAttribute("loginId", member.getLoginId());
            model.addAttribute("isLogin", true);
            model.addAttribute("isLogout", false);
            if(member.getRole() == 1) {
                model.addAttribute("isManager", false);
            }
            else {
                model.addAttribute("isManager", true);
            }

        }
        else {
            id = 0L;
            model.addAttribute("loginId", "");
            model.addAttribute("id", "");
            model.addAttribute("isLogout", true);
            model.addAttribute("isManager", false);

        }
        model.addAttribute("products", getProductRes);
        model.addAttribute("memberId", id);
        return "product-electric";
    }
    /**
     * 상품 목록 - 베이스
     */
    @GetMapping("/product-bass")
    public  String product_bass(HttpSession session, Model model) throws BaseException {
        //로그인 한 상태인지 확인
        boolean isLogin;

        if(session.getAttribute("isLogin") == null) {
            isLogin = false;
        }
        else {
            isLogin =true;
        }
        // 상품 받아오기
        List<GetProductRes> getProductRes = productService.getProducts(2L);

        Long id;
        if(isLogin) {
            // 멤버 id 받아오기
            id = (Long) session.getAttribute("memberId");
            //navbar
            Member member = memberService.findByIdAndReturnMember(id);
            model.addAttribute("loginId", member.getLoginId());
            model.addAttribute("isLogin", true);
            model.addAttribute("isLogout", false);
            if(member.getRole() == 1) {
                model.addAttribute("isManager", false);
            }
            else {
                model.addAttribute("isManager", true);
            }

        }
        else {
            id = 0L;
            model.addAttribute("loginId", "");
            model.addAttribute("id", "");
            model.addAttribute("isLogout", true);
            model.addAttribute("isManager", false);

        }
        model.addAttribute("products", getProductRes);
        model.addAttribute("memberId", id);
        return "product-bass";
    }

    /**
     * 상품 목록 - 어쿠스틱
     */
    @GetMapping("/product-acoustic")
    public  String product_acoustic(HttpSession session, Model model) throws BaseException {
        //로그인 한 상태인지 확인
        boolean isLogin;

        if(session.getAttribute("isLogin") == null) {
            isLogin = false;
        }
        else {
            isLogin =true;
        }
        // 상품 받아오기
        List<GetProductRes> getProductRes = productService.getProducts(3L);

        Long id;
       if(isLogin) {
           // 멤버 id 받아오기
          id = (Long) session.getAttribute("memberId");
          //navbar
           Member member = memberService.findByIdAndReturnMember(id);
           model.addAttribute("loginId", member.getLoginId());
           model.addAttribute("isLogin", true);
           model.addAttribute("isLogout", false);
           if(member.getRole() == 1) {
               model.addAttribute("isManager", false);
           }
           else {
               model.addAttribute("isManager", true);
           }

       }
       else {
           id = 0L;
           model.addAttribute("loginId", "");
           model.addAttribute("id", "");
           model.addAttribute("isLogout", true);
           model.addAttribute("isManager", false);

       }
        model.addAttribute("products", getProductRes);
        model.addAttribute("memberId", id);
        return "product-acoustic";
    }

    /**
     * 장바구니 화면
     */
    @GetMapping("/cart")
    public String cart(HttpSession session, Model model) throws BaseException {
        boolean isLogin;

        if(session.getAttribute("isLogin") == null) {
            isLogin = false;
        }
        else {
            isLogin =true;
        }
        if(isLogin) {
            Long id = (Long)session.getAttribute("memberId");
            List<GetCartRes>  getCartRes= cartService.getCarts(id);
            model.addAttribute("cartItems", getCartRes);
            model.addAttribute("memberId",id);
            //navbar
            Member member = memberService.findByIdAndReturnMember(id);
            model.addAttribute("loginId", member.getLoginId());
            model.addAttribute("isLogin", true);
            model.addAttribute("isLogout", false);
            if(member.getRole() == 1) {
                model.addAttribute("isManager", false);
            }
            else {
                model.addAttribute("isManager", true);
            }


            return "cart";
        }
        else {
            return "member-login";
        }
    }


    /**
     * 게시판 목록
     */
    @GetMapping("/board")
    public String board(HttpSession session, Model model) throws BaseException {
        boolean isLogin;

        if(session.getAttribute("isLogin") == null) {
            isLogin = false;
        }
        else {
            isLogin =true;
        }
        Long id;
        if(isLogin) {
            // 멤버 id 받아오기
            id = (Long) session.getAttribute("memberId");
            //navbar
            Member member = memberService.findByIdAndReturnMember(id);
            model.addAttribute("loginId", member.getLoginId());
            model.addAttribute("isLogin", true);
            model.addAttribute("isLogout", false);
            if(member.getRole() == 1) {
                model.addAttribute("isManager", false);
            }
            else {
                model.addAttribute("isManager", true);
            }

        }
        else {
            id = 0L;
            model.addAttribute("loginId", "");
            model.addAttribute("id", "");
            model.addAttribute("isLogout", true);
            model.addAttribute("isManager", false);

        }
        model.addAttribute("memberId", id);
        model.addAttribute("boards", boardService.getBoards());


        return "board-list";
    }
    /**
     * 게시글 작성
     */
    @GetMapping("/board-save")
    public String board_save(HttpSession session,Model model) throws BaseException {
        boolean isLogin;

        if(session.getAttribute("isLogin") == null) {
            isLogin = false;
        }
        else {
            isLogin =true;
        }

        if(isLogin) {
            Long id = (Long)session.getAttribute("memberId");
            model.addAttribute("memberId", id);
            return "board-save";
        }
        else {
            return "member-login";
        }
    }
    /**
     * 게시글 수정
     */
    @GetMapping("/member-info-update")
    public String board_update(HttpSession session, Model model) throws BaseException {
       Long memberId = (Long)session.getAttribute("memberId");
       Member member = memberService.findByIdAndReturnMember(memberId);

       String memberEmail = member.getEmail();

       model.addAttribute("memberId", memberId);
       model.addAttribute("memberEmail", memberEmail);
        return "member-info-modify";
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
