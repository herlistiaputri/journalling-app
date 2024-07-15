package com.learning.journaling.module.wallet.repository;

import com.learning.journaling.module.wallet.enums.TypeEnums;
import com.learning.journaling.module.wallet.module.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CategoryRepository  extends MongoRepository<Category, String> {

    Optional<Category> findByName(String name);
    Optional<Category> findByNameAndType(String name, TypeEnums type);

}
