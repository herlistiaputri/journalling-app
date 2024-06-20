package com.learning.journaling.module.wallet.repository;

import com.learning.journaling.module.wallet.module.Wallet;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WalletRepository extends MongoRepository<Wallet, String> {
}
