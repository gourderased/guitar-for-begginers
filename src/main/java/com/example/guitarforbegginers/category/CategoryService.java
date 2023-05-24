package com.example.guitarforbegginers.category;

import com.example.guitarforbegginers.category.dto.GetCategoryRes;
import com.example.guitarforbegginers.category.dto.PostCategoryReq;
import com.example.guitarforbegginers.config.BaseException;
import com.example.guitarforbegginers.config.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.guitarforbegginers.config.BaseResponseStatus.DATABASE_ERROR;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;


    /**
     * 카테고리 생성
     */
    @Transactional
    public Long save(PostCategoryReq postCategoryReq) {

        Category category = new Category();
        category.createCategory(postCategoryReq.getName());
        categoryRepository.save(category);
        return category.getId();
    }

    public List<GetCategoryRes> getCategorys() throws BaseException {
        try{
            List<Category> categories = categoryRepository.findCategories();
            List<GetCategoryRes> GetCategoryRes = categories.stream()
                    .map(category -> new GetCategoryRes(category.getId(), category.getName()))
                    .collect(Collectors.toList());
            return GetCategoryRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
