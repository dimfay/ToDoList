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
@TestExecutionListeners(value = {DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class},
        mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
public class LinkedTasksActionControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DatabaseSetup(value = "classpath:dbunit/linkedtasks/action/link-tasks-dataset.xml")
    @ExpectedDatabase(value = "classpath:dbunit/linkedtasks/action/link-tasks-expected.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown(value = "classpath:dbunit/linkedtasks/action/link-tasks-teardown.xml",
            type = DatabaseOperation.DELETE_ALL)
    void shouldLinkTasks() throws Exception {
        mockMvc.perform(post("/action/linkedtasks/link")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(linkTasksJSON()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errors").value(new ArrayList<>()));


    }

    private String linkTasksJSON() throws JSONException {
        return new JSONObject()
                .put("parentTaskId", 1)
                .put("childTaskId", 2)
                .toString();
    }

    @Test
    @DatabaseSetup(value = "classpath:dbunit/linkedtasks/action/unlink-tasks-dataset.xml")
    @ExpectedDatabase(value = "classpath:dbunit/linkedtasks/action/unlink-tasks-expected.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown(value = "classpath:dbunit/linkedtasks/action/unlink-tasks-teardown.xml",
            type = DatabaseOperation.DELETE_ALL)
    void shouldUnlinkTasks() throws Exception {
        mockMvc.perform(post("/action/linkedtasks/unlink")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(unlinkTasksJSON()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errors").value(new ArrayList<>()));


    }

    private String unlinkTasksJSON() throws JSONException {
        return new JSONObject()
                .put("parentTaskId", 1)
                .put("childTaskId", 2)
                .toString();
    }
}
