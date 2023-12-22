package com.learning.journaling.module.todolist.model;

import com.learning.journaling.module.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "to_do_lists")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ToDoList {

    @Id
    private String id;
    @DBRef
    private User user;
    private String title;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private boolean deleted;
    private LocalDateTime deletedAt;
    private String deletedBy;
    @DBRef
    List<ToDoListDetail> toDoListDetails;
}
