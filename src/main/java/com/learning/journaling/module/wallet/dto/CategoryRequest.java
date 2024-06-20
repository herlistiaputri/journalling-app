package com.learning.journaling.module.wallet.dto;

import com.learning.journaling.module.wallet.enums.TypeEnums;
import lombok.Data;

@Data
public class CategoryRequest {
    private String name;
    private TypeEnums type;
}
