package com.learning.journaling.module.todolist;

import com.learning.journaling.module.todolist.dto.ToDoListRequest;
import com.learning.journaling.module.todolist.dto.ToDoListResponse;
import com.learning.journaling.module.todolist.mapper.ToDoListMapper;
import com.learning.journaling.module.todolist.model.ToDoList;
import com.learning.journaling.module.todolist.service.ToDoListService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/to-do-list")
@RequiredArgsConstructor
public class ToDoListController {
    private final ToDoListService toDoListService;
    private final ToDoListMapper toDoListMapper;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody ToDoListRequest request){
        toDoListService.create(request);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDoListResponse> getById(@PathVariable String id){
        return ResponseEntity.ok(toDoListMapper.mapToResponse(toDoListService.getById(id)));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ToDoListResponse> delete(@PathVariable String id){
        toDoListService.delete(id);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/user/list")
    public ResponseEntity<List<ToDoListResponse>> getListByUser(){
        return ResponseEntity.ok(toDoListService.getListUser().stream().map(toDoListMapper::mapToResponse).toList());
    }

    @GetMapping("/user/page")
    public ResponseEntity<Page<ToDoListResponse>> getPageByUser(Pageable pageable, @RequestParam(required = false) String search){
        Page<ToDoList> toDoListPage = toDoListService.getPage(pageable, search);
        return ResponseEntity.ok(new PageImpl<>(toDoListPage.getContent().stream().map(toDoListMapper::mapToResponse).toList(), pageable, toDoListPage.getTotalElements()));
    }

    @PutMapping("/update-check/{id}")
    public ResponseEntity<Object> checkList(@PathVariable String id){
        toDoListService.checkList(id);
        return ResponseEntity.ok(null);
    }
}
