package com.learning.journaling.module.wallet.service;

import com.learning.journaling.configuration.RequestResponseEnums;
import com.learning.journaling.configuration.exception.BaseException;
import com.learning.journaling.module.wallet.dto.CategoryRequest;
import com.learning.journaling.module.wallet.module.Category;
import com.learning.journaling.module.wallet.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final MongoTemplate mongoTemplate;

    public void create(CategoryRequest request){
        if(categoryRepository.findByNameAndType(request.getName(), request.getType()).isPresent()){
            throw new BaseException(RequestResponseEnums.DUPLICATE_DATA_EXCEPTION);
        }

        Category category = new Category();
        category.setType(request.getType());
        category.setName(request.getName());
        category.setCreatedAt(LocalDateTime.now());
        category.setUpdatedAt(LocalDateTime.now());

        categoryRepository.save(category);

    }

    public Category getById(String id){
        return categoryRepository.findById(id).orElseThrow(() -> new BaseException(RequestResponseEnums.ID_NOT_FOUND_EXCEPTION));
    }

    public void update(String id, CategoryRequest request){
        Category category = getById(id);
        category.setName(request.getName());
        category.setUpdatedAt(LocalDateTime.now());
        categoryRepository.save(category);
    }

    public void delete(String id){
        Category category = getById(id);
        categoryRepository.delete(category);
    }

    public List<Category> getList(){
        return categoryRepository.findAll();
    }

    public Page<Category> getPage(Pageable pageable, String search){
        Query query = new Query().with(pageable);
        List<Criteria> criteria = new ArrayList<>();
        if(search != null && !search.isBlank()){
            criteria.add(Criteria.where("name").regex(".*"+search+"*.", "i"));
        }
        query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[0])));
        return PageableExecutionUtils.getPage(
                mongoTemplate.find(query, Category.class), pageable,
                () -> mongoTemplate.count(query.skip(0).limit(0), Category.class));
    }
}
