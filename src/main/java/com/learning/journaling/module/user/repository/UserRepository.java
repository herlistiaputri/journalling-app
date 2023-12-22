package com.learning.journaling.module.user.repository;

import com.learning.journaling.module.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<Object> findByEmail(String email);

    boolean existsByEmail(String email);
}
