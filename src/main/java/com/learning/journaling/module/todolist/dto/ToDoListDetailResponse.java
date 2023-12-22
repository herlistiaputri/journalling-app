package com.learning.journaling.module.todolist.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ToDoListDetailResponse {
    private String id;
    private String content;
    private boolean isDone;
    private String updatedAt;
}
