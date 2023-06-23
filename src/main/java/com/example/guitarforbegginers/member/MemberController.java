package com.example.guitarforbegginers.member;


import com.example.guitarforbegginers.config.BaseException;
import com.example.guitarforbegginers.config.BaseResponse;
import com.example.guitarforbegginers.member.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.example.guitarforbegginers.config.BaseResponseStatus.*;
import static com.example.guitarforbegginers.utils.ValidationRegex.*;

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
    public BaseResponse<PostMemberRes> save(@RequestBody PostMemberReq postMemberReq){

        if(!isRegexId(postMemberReq.getLoginId())) return new BaseResponse<>(USERS_EMPTY_USER_ID);
        if(!isRegexPw(postMemberReq.getPassword())) return new BaseResponse<>(POST_USERS_INVALID_PW);
        if(!isRegexEmail(postMemberReq.getEmail())) return new BaseResponse<>(POST_USERS_INVALID_EMAIL);
        try{
            return new BaseResponse<>(memberService.save(postMemberReq));
        } catch(BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
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
     * 회원 정보 수정
     */
    @PatchMapping("/modify/{id}")
    public Long modifyMember(@PathVariable Long id, @RequestBody PatchMemberReq patchMemberReq) {
        return memberService.modifyMember(id, patchMemberReq);
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
