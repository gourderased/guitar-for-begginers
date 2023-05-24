package com.example.guitarforbegginers.member;


import com.example.guitarforbegginers.utils.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private int role;

    @Column(nullable = false)
    private int status;

    public Member createMember(String loginId, String password, String email, int role, int status) {

        this.loginId = loginId;
        this.password = password;
        this.email = email;
        this.role = role;
        this.status = status;

        return this;
    }
    public void updateStatus(int status) {
        this.status = status;
    }
}
