package com.learning.journaling.module.wallet.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryResponse {
    private String id;
    private String name;
    private String type;
    private String createdAt;
    private String updatedAt;
}
