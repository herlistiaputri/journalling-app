package com.learning.journaling.module.journal.repository;

import com.learning.journaling.module.journal.model.Journal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface JournalRepository extends MongoRepository<Journal, String> {
    List<Journal> findAllByUserId(String userId);
    Page<Journal> findAllByUserId(String id, Pageable pageable);
    int countAllByUserId(String userId);
}
