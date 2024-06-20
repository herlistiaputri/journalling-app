package com.learning.journaling.module.wallet.mapper;

import com.learning.journaling.module.wallet.dto.CategoryResponse;
import com.learning.journaling.module.wallet.module.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    public static CategoryResponse mapToResponse(Category category){
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .type(category.getType().name())
                .createdAt(category.getCreatedAt().toString())
                .updatedAt(category.getUpdatedAt().toString())
                .build();
    }

    public static List<CategoryResponse> mapToResponseList(List<Category> categoryList) {
        return categoryList.stream().map(CategoryMapper::mapToResponse).collect(Collectors.toList());
    }


}
