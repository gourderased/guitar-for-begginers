package com.example.guitarforbegginers.product;

import com.example.guitarforbegginers.category.Category;
import com.example.guitarforbegginers.category.CategoryRepository;
import com.example.guitarforbegginers.config.BaseException;
import com.example.guitarforbegginers.product.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.guitarforbegginers.config.BaseResponseStatus.*;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    /**
     * 상품 등록
     */
    @Transactional
    public PostProductRes createProduct(PostProductReq postProductReq) throws BaseException {
        try {
            Product product = new Product();

            Category category = categoryRepository.findCategoryById(postProductReq.getCategoryId());

            product.createProduct(postProductReq.getTitle(), postProductReq.getContent(), postProductReq.getImgUrl(), postProductReq.getPrice(), category);
            productRepository.save(product);
            return new PostProductRes(product.getId(), product.getTitle());
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
    /**
     * 상품 조회
     */
    @Transactional
    public GetProductRes getProduct(Long id) throws BaseException {
        try{
            Product product = productRepository.findByProductId(id);
            return new GetProductRes(product.getId(), product.getTitle(), product.getContent(), product.getImgUrl(), product.getPrice(), product.getQuantity());
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
    /**
     * 상품 조회(Product 반환)
    */
    public Product getProductReturnProduct(Long id) {
            Product product = productRepository.findByProductId(id);
            return product;
    }

    /**
      * 상품 여러개 조회
     */
    @Transactional
    public List<GetProductRes> getProducts(Long categoryId) throws BaseException {

        try {
            Category category = categoryRepository.findCategoryById(categoryId);
            List<Product> products = productRepository.findProductsByCategory(category);

            List<GetProductRes> GetProductRes = products.stream()
                    .map(product -> new GetProductRes(product.getId(), product.getTitle(), product.getContent(), product.getImgUrl(), product.getPrice(), product.getQuantity()))
                    .collect(Collectors.toList());
            return GetProductRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }



}
