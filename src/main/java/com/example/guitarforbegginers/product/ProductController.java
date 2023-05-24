package com.example.guitarforbegginers.product;

import com.example.guitarforbegginers.config.BaseException;
import com.example.guitarforbegginers.config.BaseResponse;
import com.example.guitarforbegginers.product.dto.GetProductRes;
import com.example.guitarforbegginers.product.dto.PostProductReq;
import com.example.guitarforbegginers.product.dto.PostProductRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;

    /**
     * 상품 등록
     */
    @PostMapping("/create")
    public BaseResponse<PostProductRes> createProduct(@RequestBody PostProductReq postProductReq)  {
        try {
            return new BaseResponse<>(productService.createProduct(postProductReq));
        } catch (BaseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 특정 상품 조회
     */
    @GetMapping("/read/{id}")
    public BaseResponse<GetProductRes> getProduct(@PathVariable Long id) {
        try {
            return new BaseResponse<>(productService.getProduct(id));
        } catch(BaseException e) {
            throw new RuntimeException(e);
        }
    }



    /**
     * 카테고리 별 상품 조회
     */
    @GetMapping("/read")
    public BaseResponse<List<GetProductRes>> getProducts(@RequestParam Long categoryId) {
        try {
            return new BaseResponse<>(productService.getProducts(categoryId));
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
