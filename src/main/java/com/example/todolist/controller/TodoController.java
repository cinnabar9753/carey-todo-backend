package com.example.todolist.controller;


import com.example.todolist.entity.Todo;
import com.example.todolist.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {
    @Resource
    private TodoService todoService;


    @GetMapping()
    public List<Todo> getAllTodos(){
        return todoService.findAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Todo addTodo(@RequestBody Todo todo){
        return todoService.add(todo);
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable int id, @RequestBody Todo todo){
        return todoService.update(id,todo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable int id) {
        todoService.delete(id);
    }

}
