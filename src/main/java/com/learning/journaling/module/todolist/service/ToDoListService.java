package com.learning.journaling.module.todolist.service;

import com.learning.journaling.configuration.exception.BaseException;
import com.learning.journaling.module.todolist.dto.ToDoListRequest;
import com.learning.journaling.module.todolist.mapper.ToDoListMapper;
import com.learning.journaling.module.todolist.model.ToDoList;
import com.learning.journaling.module.todolist.model.ToDoListDetail;
import com.learning.journaling.module.todolist.repository.ToDoListDetailRepository;
import com.learning.journaling.module.todolist.repository.ToDoListRepository;
import com.learning.journaling.module.user.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ToDoListService {

    private final ToDoListDetailRepository toDoListDetailRepository;
    private final ToDoListRepository toDoListRepository;
    private final ToDoListMapper toDoListMapper;

    private final MongoTemplate mongoTemplate;

    public void create(ToDoListRequest toDoListRequest){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<ToDoListDetail> toDoListDetails = toDoListRequest.getDetailRequests().stream().map(toDoListMapper::mapToDetail).toList();
        toDoListDetailRepository.saveAll(toDoListDetails);
        toDoListRepository.save(toDoListMapper.mapToModel(toDoListRequest, user, toDoListDetails));
    }

    public ToDoList getById(String id){
        return toDoListRepository.findById(id).orElseThrow(() -> new BaseException(404, "Not Found", "Id Not Found"));
    }

    public void delete(String id){
        ToDoList toDoList = toDoListRepository.findById(id).orElseThrow(() -> new BaseException(404, "Not Found", "Id Not Found"));
        try{
            toDoListDetailRepository.deleteAll(toDoList.getToDoListDetails());
            toDoListRepository.delete(toDoList);
        } catch (Exception e){
            log.error("Error : {}", e.getMessage());
        }
    }

    public List<ToDoList> getListUser(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return toDoListRepository.findAllByUser(user);
    }

    public Page<ToDoList> getPage(Pageable pageable, String search){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Query query = new Query().with(pageable);
        List<Criteria> criteria = new ArrayList<>();
        criteria.add(Criteria.where("user").is(user));
        if(search != null && !search.isBlank()){
            criteria.add(Criteria.where("title").regex(".*"+search+"*.", "i"));
        }
        if(!criteria.isEmpty()){
            query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[0])));
        }
        return PageableExecutionUtils.getPage(
                mongoTemplate.find(query,ToDoList.class), pageable,
                () -> mongoTemplate.count(query.skip(0).limit(0), ToDoList.class));
    }

    public void checkList(String listId){
       ToDoListDetail toDoListDetail =  toDoListDetailRepository.findById(listId)
               .orElseThrow(() -> new BaseException(404, "Error Not Found", "Id Not Found"));
       toDoListDetail.setDone(!toDoListDetail.isDone());
       toDoListDetailRepository.save(toDoListDetail);
    }
}
