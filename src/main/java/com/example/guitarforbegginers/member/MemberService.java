package com.example.guitarforbegginers.member;


import com.example.guitarforbegginers.config.BaseException;
import com.example.guitarforbegginers.member.dto.GetMemberRes;
import com.example.guitarforbegginers.member.dto.PostLoginReq;
import com.example.guitarforbegginers.member.dto.PostMemberReq;
import com.example.guitarforbegginers.utils.AES128;
import com.example.guitarforbegginers.utils.Secret;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Long save(PostMemberReq postMemberReq) {
        String pwd;
        try { //암호화시켜 DB에 저장
            pwd = new AES128(Secret.USER_INFO_PASSWORD_KEY).encrypt(postMemberReq.getPassword());
        } catch (Exception ignored) { //암호화 실패시 에러 발생
            throw new IllegalAccessError("암호화 실패!");
        }
        try {
            Member member = new Member();
            member.createMember(postMemberReq.getLoginId(), pwd, postMemberReq.getEmail(), 1, 1);
            memberRepository.save(member);
            return member.getId();
        } catch (Exception e) {
            throw new IllegalAccessError("데이터베이스 에러");
        }
    }
    /**
     * 로그인
     */
    public Long login(PostLoginReq postLoginReq) throws BaseException{
        Member member = memberRepository.findByLoginId(postLoginReq.getLoginId());
        String password;
        try {
            password = new AES128(Secret.USER_INFO_PASSWORD_KEY).decrypt(member.getPassword());
        } catch (Exception ignored) {
            throw new BaseException(PASSWORD_DECRYPTION_ERROR);
        }
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
                    .map(member -> new GetMemberRes(member.getId(), member.getLoginId(), member.getEmail(), member.getStatus()))
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
            GetMemberRes getMemberRes =  new GetMemberRes(member.getId(), member.getLoginId(), member.getEmail(), member.getStatus());
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

    @Transactional
    public int findMemberCount() {
        int count = memberRepository.countMember();
        return count;
    }
}
