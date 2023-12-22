package com.learning.journaling.module.user.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class LoginRequest {
    private String email;
    private String password;
}
