package ru.team.todo.controller.rest;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.json.JSONException;
import org.json.JSONObject;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class},
        mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
class TasksActionRestControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DatabaseSetup(value = "classpath:dbunit/tasks/action/add-task-dataset.xml")
    @ExpectedDatabase(value = "classpath:dbunit/tasks/action/add-task-expected.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown(value = "classpath:dbunit/tasks/action/add-task-teardown.xml",
            type = DatabaseOperation.DELETE_ALL)
    void shouldAddTask() throws Exception {
        mockMvc.perform(post("/action/task/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(addTaskJSON()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errors").value(new ArrayList<>()));
    }

    private String addTaskJSON() throws JSONException {
        return new JSONObject()
                .put("userName", "Administrator")
                .put("taskName", "test name")
                .put("taskDescription", "test description")
                .toString();
    }


    @Test
    @DatabaseSetup(value = "classpath:dbunit/tasks/action/delete-task-dataset.xml")
    @ExpectedDatabase(value = "classpath:dbunit/tasks/action/delete-task-expected.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown(value = "classpath:dbunit/tasks/action/delete-task-teardown.xml",
            type = DatabaseOperation.DELETE_ALL)
    void shouldDeleteTask() throws Exception {
        mockMvc.perform(post("/action/task/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(deleteTaskJSON()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errors").value(new ArrayList<>()));

    }

    private String deleteTaskJSON() throws JSONException {
        return new JSONObject()
                .put("id", 1)
                .toString();
    }

    @Test
    @DatabaseSetup(value = "classpath:dbunit/tasks/action/edit-task-dataset.xml")
    @ExpectedDatabase(value = "classpath:dbunit/tasks/action/edit-task-expected.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown(value = "classpath:dbunit/tasks/action/edit-task-teardown.xml",
            type = DatabaseOperation.DELETE_ALL)
    void shouldEditTask() throws Exception {
        mockMvc.perform(post("/action/task/edit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(editTaskJSON()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errors").value(new ArrayList<>()));
    }

    private String editTaskJSON() throws JSONException {
        return new JSONObject()
                .put("id", "1")
                .put("name", "new name")
                .put("description", "new description")
                .toString();
    }
}