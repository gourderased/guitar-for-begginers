package com.example.guitarforbegginers.product;


import com.example.guitarforbegginers.category.Category;
import com.example.guitarforbegginers.utils.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Product extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String imgUrl;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private int quantity;

    @OneToOne
    @JoinColumn(name="CATEGORY_ID")
    private Category category;

    public Product createProduct(String title, String content, String imgUrl, Integer price, Category category) {
        this.title = title;
        this.content = content;
        this.imgUrl = imgUrl;
        this.price = price;
        this.category = category;
        return this;
    }

    public void updateQuantity(int sold) {
        this.quantity -= sold;
        if(this.quantity < 0) this.quantity =0;
    }
}
