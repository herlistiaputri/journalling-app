package com.learning.journaling.module.journal.dto;

import com.learning.journaling.module.user.dto.UserResponse;
import com.learning.journaling.module.user.model.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
@Builder
public class JournalUserResponse {
    private UserResponse user;
    private int totalPost;
    Page<JournalResponse> journalResponses;
}
