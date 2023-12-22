package com.learning.journaling.module.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private String id;
    private String fullName;
    private String email;
    private String lastUpdate;
    private int totalPost;
}
