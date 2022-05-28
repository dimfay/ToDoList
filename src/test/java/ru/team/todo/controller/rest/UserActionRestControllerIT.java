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
class UserActionRestControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DatabaseSetup(value = "classpath:dbunit/user/action/add-user-dataset.xml")
    @ExpectedDatabase(value = "classpath:dbunit/user/action/add-user-expected.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown(value = "classpath:dbunit/user/action/add-user-expected.xml",
            type = DatabaseOperation.DELETE_ALL)
    void shouldAddUser() throws Exception {
        mockMvc.perform(post("/action/user/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(addUserJSON()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errors").value(new ArrayList<>()))
                .andExpect(jsonPath("$.user.name").value("admin"));
    }

    private String addUserJSON() throws JSONException {
        return new JSONObject()
                .put("name", "admin")
                .toString();
    }

    @Test
    @DatabaseSetup(value = "classpath:dbunit/user/action/delete-user-dataset.xml")
    @ExpectedDatabase(value = "classpath:dbunit/user/action/delete-user-expected.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown(value = "classpath:dbunit/user/action/delete-user-teardown.xml",
            type = DatabaseOperation.DELETE_ALL)
    void shouldDeleteUser() throws Exception {
        mockMvc.perform(post("/action/user/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(deleteUserJSON()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errors").value(new ArrayList<>()));
    }

    private String deleteUserJSON() throws JSONException {
        return new JSONObject()
                .put("name", "loshara")
                .toString();
    }
}