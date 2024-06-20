package com.learning.journaling.module.wallet.module;

import com.learning.journaling.module.wallet.enums.TypeEnums;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "category")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {

    @Id
    private String id;
    private TypeEnums type;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
