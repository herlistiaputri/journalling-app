package com.learning.journaling.module.wallet.module;

import com.learning.journaling.module.user.model.User;
import com.learning.journaling.module.wallet.enums.TypeEnums;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "wallet")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Wallet {

    @Id
    private String id;
    @DBRef
    private User user;
    private TypeEnums typeEnums;
    @DBRef
    private Category category;
    private BigInteger amount;
    private String description;
    private LocalDate transactionDate;
    private LocalDateTime createdAt;

}
