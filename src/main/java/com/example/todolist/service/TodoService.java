package com.example.todolist.service;

import com.example.todolist.entity.Todo;
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

}
