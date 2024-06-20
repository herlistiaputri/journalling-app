package com.learning.journaling.module.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class LoginRequest {
    @Email(message = "Please provide a valid email address")
    @NotEmpty(message = "Email is required")
    private String email;
    private String password;
}
