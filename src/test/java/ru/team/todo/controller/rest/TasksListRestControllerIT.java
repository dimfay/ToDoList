package ru.team.todo.controller.rest;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
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

import java.util.ArrayList;

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
    private MockMvc mockMvc;

    @Test
    @DatabaseSetup(value = "classpath:dbunit/tasks/list/find-tasks-dataset.xml")
    @DatabaseTearDown(value = "classpath:dbunit/tasks/list/find-tasks-dataset.xml",
        type = DatabaseOperation.DELETE_ALL)
    void shouldFindAllTasks() throws Exception {
        mockMvc.perform(get("/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errors").value(new ArrayList<>()))
                .andExpect(jsonPath("$.tasks.size()").value(2))
                .andExpect(jsonPath("$.tasks[*].id", Matchers.containsInAnyOrder(1, 2)))
                .andExpect(jsonPath("$.tasks[*].name",
                        Matchers.containsInAnyOrder("test name1", "test name2")))
                .andExpect(jsonPath("$.tasks[*].description",
                        Matchers.containsInAnyOrder("test description1", "test description2")));
    }

    @Test
    @DatabaseSetup(value = "classpath:dbunit/tasks/list/find-tasks-user-dataset.xml")
    @DatabaseTearDown(value = "classpath:dbunit/tasks/list/find-tasks-user-dataset.xml",
            type = DatabaseOperation.DELETE_ALL)
    void shouldFindTasksByUser() throws Exception {
        mockMvc.perform(get("/users/{username}/tasks", "admin").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errors").value(new ArrayList<>()))
                .andExpect(jsonPath("$.tasks.size()").value("1"))
                .andExpect(jsonPath("$.tasks[0].id").value("1"))
                .andExpect(jsonPath("$.tasks[0].name").value("test admin"))
                .andExpect(jsonPath("$.tasks[0].description").value("test description"));


    }

}