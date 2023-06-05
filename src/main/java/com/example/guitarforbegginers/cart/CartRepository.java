package com.example.guitarforbegginers.cart;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository <Cart ,Long>{


    @Query("SELECT c.member.id as memberId, c.product.id as productId, COUNT(c.product.id) as productCount FROM Cart c WHERE c.member.id = :memberId GROUP BY c.member.id, c.product.id")
    List<Object[]> getProductCountByMemberIdAndUserId(@Param("memberId") Long id);


    void deleteByMemberIdAndProductId(Long memberId, Long productId);
}
