package com.example.todolist.exception;

public class TodoNotFound extends RuntimeException{
    @Override
    public String getMessage() {
        return "todo not found";
    }
}
