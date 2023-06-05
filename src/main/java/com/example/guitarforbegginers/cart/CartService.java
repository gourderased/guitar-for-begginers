package com.example.guitarforbegginers.cart;

import com.example.guitarforbegginers.cart.dto.GetCartRepoRes;
import com.example.guitarforbegginers.cart.dto.GetCartRes;
import com.example.guitarforbegginers.cart.dto.PostCartReq;
import com.example.guitarforbegginers.cart.dto.PostPaymentReq;
import com.example.guitarforbegginers.config.BaseException;
import com.example.guitarforbegginers.member.Member;
import com.example.guitarforbegginers.member.MemberService;
import com.example.guitarforbegginers.product.Product;
import com.example.guitarforbegginers.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.example.guitarforbegginers.config.BaseResponseStatus.DATABASE_ERROR;

@RequiredArgsConstructor
@Service
public class CartService {

    private final CartRepository cartRepository;
    private final MemberService memberService;
    private final ProductService productService;

    @Transactional
    // 장바구니 생성
    public Long createCart(PostCartReq postCartReq) throws BaseException {
        Long memberId = postCartReq.getMemberId();
        Long productId = postCartReq.getProductId();

        Product product = productService.getProductReturnProduct(productId);
        Member member = memberService.findByIdAndReturnMember(memberId);

        Cart cart = new Cart();
        cart.createCart(member,product);
        cartRepository.save(cart);

        return cart.getId();
    }

    //접속한 멤버 장바구니 조회
    public List<GetCartRes> getCarts(Long id) throws BaseException {
        try {
            //주어진 멤버 id의 카트 가져오기
            List<Object[]> cartData = cartRepository.getProductCountByMemberIdAndUserId(id);
            List<GetCartRepoRes> cartResults = new ArrayList<>();


            for (Object[] row : cartData) {
                GetCartRepoRes cartResult;
                Long memberId = (Long) row[0];
                Long productId = (Long) row[1];
                Long productCount = (Long) row[2];

                cartResult = new GetCartRepoRes(memberId, productId, productCount);
                cartResults.add(cartResult);
            }
            List<GetCartRes> getCartResults = new ArrayList<>();

            for (GetCartRepoRes cartResult : cartResults) {
                GetCartRes getCartResult = null;
                Long productId = cartResult.getProductId();
                Long productCount = cartResult.getProductCount();

                Product product = productService.getProductReturnProduct(productId);
                getCartResult = new GetCartRes(productId, product.getTitle(), product.getImgUrl(), product.getPrice(), productCount);
                getCartResults.add(getCartResult);
            }
            return getCartResults;

        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 1. 결제한 상품들 수량 감소
    // 2.장바구니 삭제
    @Transactional
    public void processPayment( List<PostPaymentReq> paymentReqList) throws BaseException {
        for (PostPaymentReq paymentReq : paymentReqList) {
            Long memberId = paymentReq.getMemberId();
            Long productId = paymentReq.getProductId();
            int quantity = paymentReq.getQuantity();

            // 1. 수량 변경
            Product product = productService.getProductReturnProduct(productId);
            product.updateQuantity(quantity);
            // 2. Delete cart
            cartRepository.deleteByMemberIdAndProductId(memberId, productId);

            // Additional processing if needed
        }
    }
}
