package com.learning.journaling.module.wallet.dto;

import com.learning.journaling.module.wallet.enums.TypeEnums;
import lombok.Data;

import java.math.BigInteger;

@Data
public class WalletRequest {
    private TypeEnums type;
    private String categoryId;
    private BigInteger amount;
    private String description;
    private String transactionDate;
}
