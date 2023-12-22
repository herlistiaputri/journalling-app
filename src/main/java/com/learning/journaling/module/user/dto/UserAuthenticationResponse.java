package com.learning.journaling.module.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserAuthenticationResponse {
    private String type;
    private String token;
}
