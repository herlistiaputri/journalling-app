package com.learning.journaling.module.wallet;

import com.learning.journaling.module.wallet.dto.CategoryRequest;
import com.learning.journaling.module.wallet.dto.CategoryResponse;
import com.learning.journaling.module.wallet.mapper.CategoryMapper;
import com.learning.journaling.module.wallet.module.Category;
import com.learning.journaling.module.wallet.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody CategoryRequest request){
        categoryService.create(request);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getById(@PathVariable String id){
        return ResponseEntity.ok(CategoryMapper.mapToResponse(categoryService.getById(id)));
    }

    @GetMapping("/list")
    public ResponseEntity<List<CategoryResponse>> getAll(){
        return ResponseEntity.ok(CategoryMapper.mapToResponseList(categoryService.getList()));
    }

    @GetMapping("/page")
    public ResponseEntity<Page<CategoryResponse>> getPage(Pageable pageable, @RequestParam(required = false) String search){
        Page<Category> categoryPage = categoryService.getPage(pageable, search);
        return ResponseEntity.ok(new PageImpl<>(CategoryMapper.mapToResponseList(categoryPage.getContent()), pageable, categoryPage.getTotalElements()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateById(@PathVariable String id, @RequestBody CategoryRequest request){
        categoryService.update(id, request);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable String id){
        categoryService.delete(id);
        return ResponseEntity.ok(null);
    }

}
