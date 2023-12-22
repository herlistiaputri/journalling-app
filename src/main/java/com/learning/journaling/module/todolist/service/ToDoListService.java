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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ToDoListService {

    private final ToDoListDetailRepository toDoListDetailRepository;
    private final ToDoListRepository toDoListRepository;
    private final ToDoListMapper toDoListMapper;

    public void create(ToDoListRequest toDoListRequest){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<ToDoListDetail> toDoListDetails = toDoListRequest.getDetailRequests().stream().map(toDoListMapper::mapToDetail).toList();
        toDoListDetailRepository.saveAll(toDoListDetails);
        toDoListRepository.save(toDoListMapper.mapToModel(toDoListRequest, user, toDoListDetails));
    }

    public ToDoList getById(String id){
        return toDoListRepository.findById(id).orElseThrow(() -> new BaseException(404, "Not Found", "Id Not Found"));
    }

    public List<ToDoList> getListUser(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return toDoListRepository.findAllByUser(user);
    }

    public void checkList(String listId){
       ToDoListDetail toDoListDetail =  toDoListDetailRepository.findById(listId)
               .orElseThrow(() -> new BaseException(404, "Error Not Found", "Id Not Found"));
       toDoListDetail.setDone(!toDoListDetail.isDone());
       toDoListDetailRepository.save(toDoListDetail);
    }
}
