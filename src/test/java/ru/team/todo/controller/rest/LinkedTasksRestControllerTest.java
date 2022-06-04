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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestExecutionListeners(value = {DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class},
        mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
public class LinkedTasksRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DatabaseSetup(value = "classpath:dbunit/linkedtasks/find-linkedtasks-dataset.xml")
    @DatabaseTearDown(value = "classpath:dbunit/linkedtasks/find-linkedtasks-dataset.xml",
            type = DatabaseOperation.DELETE_ALL)
    public void shouldFindAllLinkedTasks() throws Exception {
        mockMvc.perform(get("/linkedtasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errors").value(new ArrayList<>()))
                .andExpect(jsonPath("$.linkedTasks.size()").value(1))
                .andExpect(jsonPath("$.linkedTasks[*].id").value(1))
                .andExpect(jsonPath("$.linkedTasks[*].task.id").value(1))
                .andExpect(jsonPath("$.linkedTasks[*].task.name").value("first task"))
                .andExpect(jsonPath("$.linkedTasks[*].task.description").value("first description"))
                .andExpect(jsonPath("$.linkedTasks[*].linkedTask.id").value(2))
                .andExpect(jsonPath("$.linkedTasks[*].linkedTask.name").value("second task"))
                .andExpect(jsonPath("$.linkedTasks[*].linkedTask.description").value("second description"));

    }

    @Test
    @DatabaseSetup(value = "classpath:dbunit/linkedtasks/find-linkedtasks-id-dataset.xml")
    @DatabaseTearDown(value = "classpath:dbunit/linkedtasks/find-linkedtasks-id-dataset.xml",
            type = DatabaseOperation.DELETE_ALL)
    public void shouldFindLinkedTasksById() throws Exception {
        mockMvc.perform(get("/linkedtasks/{id}", "3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errors").value(new ArrayList<>()))
                .andExpect(jsonPath("$.linkedTasks.size()").value(1))
                .andExpect(jsonPath("$.linkedTasks[*].id").value(2))
                .andExpect(jsonPath("$.linkedTasks[*].task.id").value(3))
                .andExpect(jsonPath("$.linkedTasks[*].task.name").value("third task"))
                .andExpect(jsonPath("$.linkedTasks[*].task.description").value("third description"))
                .andExpect(jsonPath("$.linkedTasks[*].linkedTask.id").value(4))
                .andExpect(jsonPath("$.linkedTasks[*].linkedTask.name").value("fourth task"))
                .andExpect(jsonPath("$.linkedTasks[*].linkedTask.description").value("fourth description"));

        mockMvc.perform(get("/linkedtasks/{id}", "4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errors").value(new ArrayList<>()))
                .andExpect(jsonPath("$.linkedTasks.size()").value(1))
                .andExpect(jsonPath("$.linkedTasks[*].id").value(2))
                .andExpect(jsonPath("$.linkedTasks[*].task.id").value(3))
                .andExpect(jsonPath("$.linkedTasks[*].task.name").value("third task"))
                .andExpect(jsonPath("$.linkedTasks[*].task.description").value("third description"))
                .andExpect(jsonPath("$.linkedTasks[*].linkedTask.id").value(4))
                .andExpect(jsonPath("$.linkedTasks[*].linkedTask.name").value("fourth task"))
                .andExpect(jsonPath("$.linkedTasks[*].linkedTask.description").value("fourth description"));

    }

    @Test
    @DatabaseSetup(value = "classpath:dbunit/linkedtasks/link-tasks-dataset.xml")
    @ExpectedDatabase(value = "classpath:dbunit/linkedtasks/link-tasks-expected.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown(value = "classpath:dbunit/linkedtasks/link-tasks-teardown.xml",
            type = DatabaseOperation.DELETE_ALL)
    public void shouldLinkTasks() throws Exception {
        mockMvc.perform(post("/linkedtasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(linkTasksJSON()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errors").value(new ArrayList<>()));


    }

    @Test
    @DatabaseSetup(value = "classpath:dbunit/linkedtasks/unlink-tasks-dataset.xml")
    @ExpectedDatabase(value = "classpath:dbunit/linkedtasks/unlink-tasks-expected.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown(value = "classpath:dbunit/linkedtasks/unlink-tasks-teardown.xml",
            type = DatabaseOperation.DELETE_ALL)
    public void shouldUnlinkTasks() throws Exception {
        mockMvc.perform(delete("/linkedtasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(unlinkTasksJSON()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errors").value(new ArrayList<>()));


    }

    private static String unlinkTasksJSON() throws JSONException {
        return new JSONObject()
                .put("parentTaskId", 1)
                .put("childTaskId", 2)
                .toString();
    }

    private static String linkTasksJSON() throws JSONException {
        return new JSONObject()
                .put("parentTaskId", 1)
                .put("childTaskId", 2)
                .toString();
    }
}