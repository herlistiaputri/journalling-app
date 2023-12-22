package com.learning.journaling.module.user.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserAuthenticationRequest {
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Wrong email format")
    private String email;
    private String fullName;
    private String password;
    private String pin;
}
