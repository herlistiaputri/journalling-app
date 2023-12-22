package com.learning.journaling.module.todolist.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ToDoListResponse {

    private String id;
    private String title;
    private String createdAt;
    private Integer total;
    private List<ToDoListDetailResponse> toDoListDetails;

}
