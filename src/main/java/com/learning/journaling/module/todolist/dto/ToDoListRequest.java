package com.learning.journaling.module.todolist.dto;

import com.learning.journaling.module.todolist.model.ToDoListDetail;
import lombok.Data;

import java.util.List;

@Data
public class ToDoListRequest {
    private String title;
    private List<ToDoListDetailRequest> detailRequests;
}
