package com.example.todolist.service;

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

    public Todo update(int id, Todo todo){
        Todo toUpdate = todoRepo.findById(id).orElseThrow(TodoNotFound::new);
        if (!toUpdate.getContext().isEmpty())
            toUpdate.setContext(todo.getContext());
        toUpdate.setDone(todo.isDone());
        return todoRepo.save(toUpdate);
    }

    public void delete(int id){
        todoRepo.deleteById(id);
    }

}
