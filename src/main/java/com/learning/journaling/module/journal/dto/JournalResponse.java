package com.learning.journaling.module.journal.dto;

import com.learning.journaling.module.user.model.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JournalResponse {
    private String id;
    private String title;
    private String content;
    private String createdBy;
    private String createdAt;
    private String lastUpdate;
}
