package com.example.todolist.controller.mapper;

import com.example.todolist.controller.dto.TodoRequest;
import com.example.todolist.entity.Todo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class TodoMapper {
    public Todo toEntity(TodoRequest request) {
        Todo todo = new Todo();
        BeanUtils.copyProperties(request, todo);
        return todo;
    }
}
