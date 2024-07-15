package com.learning.journaling.module.wallet.repository;

import com.learning.journaling.module.user.model.User;
import com.learning.journaling.module.wallet.module.Wallet;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface WalletRepository extends MongoRepository<Wallet, String> {

    List<Wallet> findAllByUserAndTransactionDateBetween(User user, LocalDate start, LocalDate end);
    List<Wallet> findAllByUser(User user);
}
