package com.example.todolist.service;

import com.example.todolist.controller.dto.TodoRequest;
import com.example.todolist.entity.Todo;
import com.example.todolist.exception.TodoNotFound;
import com.example.todolist.repository.JpaTodoRepo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TodoService {

    @Resource
    private JpaTodoRepo todoRepo;

    public List<Todo> findAll(){
        return todoRepo.findAll();
    }

    public Todo add(Todo todo){
        return todoRepo.save(todo);
    }

    public Todo update(int id, TodoRequest todo){
        Todo toUpdate = todoRepo.findById(id).orElseThrow(TodoNotFound::new);
        if (todo.getContext()!=null)
            toUpdate.setContext(todo.getContext());
        if (todo.getDone()!=null)
            toUpdate.setDone(todo.getDone());
        return todoRepo.save(toUpdate);
    }

    public void delete(int id){
        todoRepo.deleteById(id);
    }

}
