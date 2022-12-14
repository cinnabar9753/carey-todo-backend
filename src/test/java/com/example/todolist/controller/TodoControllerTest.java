package com.example.todolist.controller;

import com.example.todolist.entity.Todo;
import com.example.todolist.repository.JpaTodoRepo;
import com.example.todolist.service.TodoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class TodoControllerTest {

    @Autowired
    private MockMvc client;
    @Autowired
    private TodoService todoService;

    @Autowired
    private JpaTodoRepo todoRepo;

    @BeforeEach
    public void clearDB() {
        todoRepo.deleteAll();
    }

    @Test
    public void should_return_all_todos_when_perform_get_given_null() throws Exception {
        //given
        todoRepo.save(new Todo("aaa",false));
        todoRepo.save(new Todo("bbb",false));

        //when & then
        client.perform(MockMvcRequestBuilders.get("/todos"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].context").value("aaa"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].done").value(false))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void should_add_a_todo_when_perform_post_given_a_todo() throws Exception {
        //given
        Todo request = new Todo("aaa",false);
        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(request);
        //when & then
        client.perform(MockMvcRequestBuilders.post("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.context").value("aaa"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.done").value(false))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void should_delete_a_todo_when_perform_delete_given_a_id() throws Exception {
        //given
        Todo todo = todoRepo.save(new Todo("aaa", false));
        todoRepo.save(new Todo("bbb",false));

        //when & then
        client.perform(MockMvcRequestBuilders.delete("/todos/{id}",todo.getId()))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void should_update_a_todo_when_perform_put_given_a_todo() throws Exception {
        //given
        Todo todo = todoRepo.save(new Todo("aaa", false));
        Todo request = new Todo();
        request.setDone(true);
        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(request);
        //when & then
        client.perform(MockMvcRequestBuilders.put("/todos/{id}",todo.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.context").value("bbb"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.done").value(true))
                .andDo(MockMvcResultHandlers.print());
    }

}