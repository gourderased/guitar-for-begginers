package com.example.guitarforbegginers.member;


import com.example.guitarforbegginers.config.BaseException;
import com.example.guitarforbegginers.member.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.guitarforbegginers.config.BaseResponseStatus.*;
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     */
    @Transactional
    public PostMemberRes save(PostMemberReq postMemberReq) throws BaseException{
        try {
            Member member = new Member();
            member.createMember(postMemberReq.getLoginId(), postMemberReq.getPassword(), postMemberReq.getEmail(), 1, 1);
            memberRepository.save(member);
            return new PostMemberRes(member.getLoginId(), member.getId());
        } catch (Exception e) {
            throw new IllegalAccessError("데이터베이스 에러");
        }
    }
    /**
     * 로그인
     */

    public Long login(PostLoginReq postLoginReq) throws BaseException{
        Member member = memberRepository.findByLoginId(postLoginReq.getLoginId());
        String password = member.getPassword();
        if(postLoginReq.getPassword().equals(password)) {
            return member.getId();
        }
        else {
            throw new BaseException(FAILED_TO_LOGIN);
        }
    }

    /**
     * 모든 회원 조회
     */
    public List<GetMemberRes> getMembers() throws BaseException {
        try {
            List<Member> members = memberRepository.findMembers();
            List<GetMemberRes> GetMemberRes = members.stream()
                    .map(member -> new GetMemberRes(member.getId(), member.getLoginId(), member.getEmail(), member.getPassword(), member.getStatus(), member.getCreateDate() , member.getModifiedDate()))
                    .collect(Collectors.toList());
            return GetMemberRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    /**
     * 특정 아이디 조회
     */
    public GetMemberRes getMemberById(Long id) throws BaseException{
        try {
            Member member = memberRepository.findMemberById(id);
            GetMemberRes getMemberRes =  new GetMemberRes(member.getId(), member.getLoginId(), member.getEmail(), member.getPassword(), member.getStatus(), member.getCreateDate() , member.getModifiedDate());
            return getMemberRes;

        } catch(Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional
    public void delete(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. id=" + id));
        memberRepository.delete(member);
    }

    @Transactional
    public Member findByIdAndReturnMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. id=" + id));
        return member;
    }

    //회원 정보 수정
    @Transactional
    public Long modifyMember(Long id, PatchMemberReq patchMemberReq) {
        System.out.println("wafdssadgdsgsdgsdgsdg");
        String email = patchMemberReq.getEmail();
        Member member = memberRepository.findMemberById(id);
        member.updateEmail(email);
        return id;
    }
}
