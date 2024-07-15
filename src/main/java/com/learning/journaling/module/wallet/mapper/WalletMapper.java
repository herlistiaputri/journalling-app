package com.learning.journaling.module.wallet.mapper;

import com.learning.journaling.module.wallet.dto.WalletRequest;
import com.learning.journaling.module.wallet.dto.WalletResponse;
import com.learning.journaling.module.wallet.module.Wallet;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class WalletMapper {

    private static  final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static Wallet mapToModel(WalletRequest request, Wallet wallet){
        return wallet.builder()
                .typeEnums(request.getType())
                .description(request.getDescription())
                .amount(request.getAmount())
                .transactionDate(LocalDate.parse(request.getTransactionDate(), formatter))
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static WalletResponse mapToResponse(Wallet wallet){
        return WalletResponse.builder()
                .id(wallet.getId())
                .userId(wallet.getUser().getId())
                .type(wallet.getTypeEnums().name())
                .category(wallet.getCategory().getName())
                .description(wallet.getDescription())
                .transactionDate(wallet.getTransactionDate().toString())
                .amount(String.valueOf(wallet.getAmount()))
                .build();
    }

    public static List<WalletResponse> mapToResponseList(List<Wallet> walletList){
        return walletList.stream().map(WalletMapper::mapToResponse).collect(Collectors.toList());
    }

}
