package ru.team.todo.controller.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import ru.team.todo.dto.tasks.TaskDTO;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class},
        mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
class TasksListRestControllerIT {
    @Autowired
    MockMvc mockMvc;

    @Test
    @DatabaseSetup(value = "classpath:dbunit/tasks/list/find-tasks-dataset.xml")
    @DatabaseTearDown(value = "classpath:dbunit/tasks/list/find-tasks-dataset.xml")
    void shouldFindAllTasks() throws Exception {
        mockMvc.perform(get("/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errors").value(new ArrayList<>()))
                .andExpect(jsonPath("$.tasks[0].id").value(1))
                .andExpect(jsonPath("$.tasks[0].name").value("test name"))
                .andExpect(jsonPath("$.tasks[0].description").value("test description"))
                .andExpect(jsonPath("$.tasks[1].id").value(2))
                .andExpect(jsonPath("$.tasks[1].name").value("test name"))
                .andExpect(jsonPath("$.tasks[1].description").value("test description"));

    }

    @Test
    @DatabaseSetup(value = "classpath:dbunit/tasks/list/find-tasks-user-dataset.xml")
    @DatabaseTearDown(value = "classpath:dbunit/tasks/list/find-tasks-user-dataset.xml")
    void shouldFindTasksByUser() throws Exception {
        mockMvc.perform(get("/users/{username}/tasks", "admin").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errors").value(new ArrayList<>()))
                .andExpect(jsonPath("$.tasks[0].id").value("1"))
                .andExpect(jsonPath("$.tasks[0].name").value("test admin"))
                .andExpect(jsonPath("$.tasks[0].description").value("test description"));


    }

}