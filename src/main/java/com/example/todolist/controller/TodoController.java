package com.example.todolist.controller;


import com.example.todolist.entity.Todo;
import com.example.todolist.service.TodoService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/todos")
@CrossOrigin
public class TodoController {
    @Resource
    private TodoService todoService;


    @GetMapping()
    public List<Todo> getAllTodos(){
        return todoService.findAll();
    }
}
