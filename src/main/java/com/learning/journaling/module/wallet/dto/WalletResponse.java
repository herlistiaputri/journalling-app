package com.learning.journaling.module.wallet.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WalletResponse {

    private String id;
    private String userId;
    private String type;
    private String category;
    private String amount;
    private String description;
    private String transactionDate;

}
