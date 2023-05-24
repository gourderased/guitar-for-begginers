package com.example.guitarforbegginers.category;

import com.example.guitarforbegginers.category.dto.GetCategoryRes;
import com.example.guitarforbegginers.category.dto.PostCategoryReq;
import com.example.guitarforbegginers.config.BaseException;
import com.example.guitarforbegginers.config.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("category")
public class CategoryController {

    private final CategoryService categoryService;
    /**
     * 카테고리 생성
     */
    @PostMapping("/create")
    public Long save(@RequestBody PostCategoryReq postCategoryReq) {
        return categoryService.save(postCategoryReq);
    }

    /**
     * 모든 카테고리 조회
     */
    @GetMapping("/read")
    public BaseResponse<List<GetCategoryRes>> getCategory(@RequestParam(required = false) Long id) throws BaseException {
        return new BaseResponse<>(categoryService.getCategorys());
    }
}
