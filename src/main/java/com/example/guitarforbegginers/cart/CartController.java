package com.example.guitarforbegginers.cart;

import com.example.guitarforbegginers.cart.dto.PostCartReq;
import com.example.guitarforbegginers.cart.dto.PostPaymentReq;
import com.example.guitarforbegginers.cart.dto.PostPaymentRes;
import com.example.guitarforbegginers.config.BaseException;
import com.example.guitarforbegginers.config.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.guitarforbegginers.config.BaseResponseStatus.EMPTY_CART_LIST;

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
    public BaseResponse<PostPaymentRes> processPayment(@RequestBody List<PostPaymentReq> paymentReqList)  {
        if(paymentReqList.isEmpty()) return new BaseResponse<>(EMPTY_CART_LIST);
        try {
            return new BaseResponse<>(cartService.processPayment(paymentReqList));
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
