package com.learning.journaling.module.wallet.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class WalletReportResponse {

    private String in;
    private String out;
}
