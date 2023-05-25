package com.example.guitarforbegginers;

import com.example.guitarforbegginers.config.BaseException;
import com.example.guitarforbegginers.member.Member;
import com.example.guitarforbegginers.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final MemberService memberService;
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
            model.addAttribute("id", member.getId());
        }
        else {
            model.addAttribute("loginId", "");
            model.addAttribute("id", "");
        }


        return "index";
    }
    @GetMapping("/member/log-in")
    public String memberLogin() {
        return "member-login";
    }
    @GetMapping("/member/create")
    public String memberCreate() {
        return "member-signup";
    }

    @GetMapping("/member/my-info")
    public String memberMyInfo() {
        return "member-info";
    }
}
