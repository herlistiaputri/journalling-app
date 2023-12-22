package com.learning.journaling.module.journal.model;


import com.learning.journaling.module.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "journals")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Journal {

    @Id
    private String id;
    @DBRef
    private User user;
    private String title;
    private String content;
    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;
    private boolean deleted;
    private String deletedBy;
    private LocalDateTime deletedAt;
}
