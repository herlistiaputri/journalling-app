package com.learning.journaling.module.journal.service;

import com.learning.journaling.configuration.RequestResponseEnums;
import com.learning.journaling.configuration.exception.BaseException;
import com.learning.journaling.module.journal.dto.JournalRequest;
import com.learning.journaling.module.journal.model.Journal;
import com.learning.journaling.module.journal.repository.JournalRepository;
import com.learning.journaling.module.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class JournalService {

    private final JournalRepository journalRepository;
    private final MongoTemplate mongoTemplate;

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
        return journalRepository.findById(id).orElseThrow(() -> new BaseException(RequestResponseEnums.ID_NOT_FOUND_EXCEPTION));
    }

    public Page<Journal> getByUser(Pageable pageable, User user, String search, String year){
        Query query = new Query().with(pageable);
        List<Criteria> criteria = new ArrayList<>();
        criteria.add(Criteria.where("user").is(user));
        if(search != null && !search.isBlank()){
            criteria.add(Criteria.where("title").regex(".*"+search+"*.", "i"));
        }

        if (year != null && !year.isBlank()){
            LocalDateTime startOfYear = LocalDateTime.of(Integer.parseInt(year), 1, 1, 0,0);
            LocalDateTime endOfYear = LocalDateTime.of(Integer.parseInt(year), 12, 31, 23, 59, 59);
            criteria.add(Criteria.where("createdAt").gte(startOfYear).lt(endOfYear));
        }

        query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[0])));
        return PageableExecutionUtils.getPage(
                mongoTemplate.find(query, Journal.class), pageable,
                () -> mongoTemplate.count(query.skip(0).limit(0), Journal.class));
    }
}
