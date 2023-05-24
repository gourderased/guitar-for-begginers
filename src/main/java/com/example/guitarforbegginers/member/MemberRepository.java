package com.example.guitarforbegginers.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long> {
    @Query("select m from Member m where m.loginId = :loginId")
    Member findMemberByLoginId(@Param("loginId") String loginId);

    @Query("select m from Member m")
    List<Member> findMembers();

    @Query("select m from Member m where m.id = :id")
    Member findMemberById(@Param("id") Long id);
}
