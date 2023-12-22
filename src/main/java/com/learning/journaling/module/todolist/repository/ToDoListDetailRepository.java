package com.learning.journaling.module.todolist.repository;

import com.learning.journaling.module.todolist.model.ToDoListDetail;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ToDoListDetailRepository extends MongoRepository<ToDoListDetail, String> {
}
