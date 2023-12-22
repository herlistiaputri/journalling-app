package com.learning.journaling.module.journal.service;

import com.learning.journaling.configuration.exception.BaseException;
import com.learning.journaling.module.journal.dto.JournalRequest;
import com.learning.journaling.module.journal.model.Journal;
import com.learning.journaling.module.journal.repository.JournalRepository;
import com.learning.journaling.module.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class JournalService {

    private final JournalRepository journalRepository;

    public void create(JournalRequest request){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Journal journal = Journal.builder()
                .user(user)
                .title(request.getTittle())
                .content(request.getContent())
                .createdAt(LocalDateTime.now())
                .createdBy(user.getFullName())
                .updatedAt(LocalDateTime.now())
                .build();
        journalRepository.save(journal);
    }

    public Journal getById(String id){
        return journalRepository.findById(id).orElseThrow(() -> new BaseException(404, "Not Found", "Journal Id Not Found"));
    }

    public Page<Journal> getByUser(Pageable pageable, User user){
        return journalRepository.findAllByUserId(user.getId(), pageable);
    }
}
