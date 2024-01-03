package com.learning.journaling.module.todolist.repository;

import com.learning.journaling.module.todolist.model.ToDoList;
import com.learning.journaling.module.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ToDoListRepository extends MongoRepository<ToDoList, String> {
    List<ToDoList> findAllByUser(User user);
    Page<ToDoList> findAllByUser(Pageable pageable, User user);
}
