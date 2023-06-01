package com.example.guitarforbegginers.cart;

import com.example.guitarforbegginers.member.Member;
import com.example.guitarforbegginers.product.Product;
import com.example.guitarforbegginers.utils.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Cart extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    public Cart createCart(Member member, Product product) {
        this.member = member;
        this.product = product;
        return this;
    }
}
