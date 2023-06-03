package com.example.guitarforbegginers.member;


import com.example.guitarforbegginers.config.BaseException;
import com.example.guitarforbegginers.config.BaseResponse;
import com.example.guitarforbegginers.member.dto.GetMemberRes;
import com.example.guitarforbegginers.member.dto.PostLoginReq;
import com.example.guitarforbegginers.member.dto.PostMemberReq;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("member")
public class MemberController{

    private final MemberService memberService;

    @Autowired
    private HttpServletRequest request;
    /**
     * 회원 가입
     */
    @PostMapping("/create")
    public Long save(@RequestBody PostMemberReq postMemberReq) {
        return memberService.save(postMemberReq);
    }
    /**
     * 유저 로그인
     */
    @PostMapping("/log-in")
    public Long login(@RequestBody PostLoginReq postLoginReq) throws BaseException {

        Long memberId = memberService.login(postLoginReq);

        HttpSession session = request.getSession();
        session.setAttribute("memberId", memberId);
        session.setAttribute("isLogin", true);

        return memberId;
    }
    /**
     * 회원 조회
     * 파라미터에 id없을 경우 모두 조회
     */
    @GetMapping("/read")
    public BaseResponse<List<GetMemberRes>> getMembers(@RequestParam(required = false) Long id) {
        try {
            if(id == null) {
                return new BaseResponse<>(memberService.getMembers());
            }
            return new BaseResponse(memberService.getMemberById(id));
        } catch(BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
    /**
     * 유저 삭제
     */
    @DeleteMapping("/delete/{id}")
    public Long delete(@PathVariable Long id) {
        memberService.delete(id);
        return id;
    }

}
