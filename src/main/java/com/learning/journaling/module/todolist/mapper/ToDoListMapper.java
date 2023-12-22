package com.learning.journaling.module.todolist.mapper;

import com.learning.journaling.module.todolist.dto.ToDoListDetailRequest;
import com.learning.journaling.module.todolist.dto.ToDoListDetailResponse;
import com.learning.journaling.module.todolist.dto.ToDoListRequest;
import com.learning.journaling.module.todolist.dto.ToDoListResponse;
import com.learning.journaling.module.todolist.model.ToDoList;
import com.learning.journaling.module.todolist.model.ToDoListDetail;
import com.learning.journaling.module.user.model.User;
import com.learning.journaling.util.DateTimeUtil;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ToDoListMapper {

    public ToDoList mapToModel(ToDoListRequest request, User user, List<ToDoListDetail> toDoListDetails){
        return ToDoList.builder()
                .title(request.getTitle())
                .user(user)
                .toDoListDetails(toDoListDetails)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public ToDoListDetail mapToDetail(ToDoListDetailRequest request){
        return ToDoListDetail.builder()
                .content(request.getContent())
                .isDone(false)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public ToDoListResponse mapToResponse(ToDoList toDoList){
        return ToDoListResponse.builder()
                .id(toDoList.getId())
                .title(toDoList.getTitle())
                .createdAt(DateTimeUtil.parseDateTimeToString(toDoList.getCreatedAt()))
                .total(toDoList.getToDoListDetails().size())
                .toDoListDetails(toDoList.getToDoListDetails().stream().map(this::mapToDetailResponse).toList())
                .build();
    }

    public ToDoListDetailResponse mapToDetailResponse(ToDoListDetail toDoListDetail){
        return ToDoListDetailResponse.builder()
                .id(toDoListDetail.getId())
                .content(toDoListDetail.getContent())
                .isDone(toDoListDetail.isDone())
                .updatedAt(DateTimeUtil.parseDateTimeToString(toDoListDetail.getUpdatedAt()))
                .build();
    }

}
