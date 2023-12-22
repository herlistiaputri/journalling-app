package com.learning.journaling.module.todolist.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "to_do_list_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ToDoListDetail {

    @Id
    private String id;
    private String content;
    private boolean isDone;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private boolean deleted;
    private LocalDateTime deletedAt;
    private String deletedBy;

}
