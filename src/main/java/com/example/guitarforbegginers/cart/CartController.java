package com.example.guitarforbegginers.cart;

import com.example.guitarforbegginers.cart.dto.PostCartReq;
import com.example.guitarforbegginers.cart.dto.PostPaymentReq;
import com.example.guitarforbegginers.config.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("cart")
public class CartController {

    private final CartService cartService;

    //장바구니 생성
    @PostMapping("/create")
    public Long addCart(@RequestBody PostCartReq postCartReq) throws BaseException {
        return cartService.createCart(postCartReq);
    }
    //장바구니 상품 결제

    @PostMapping("/payment")
    public Long processPayment(@RequestBody List<PostPaymentReq> paymentReqList) throws BaseException {
        cartService.processPayment(paymentReqList);
        return 1L;
    }
}
