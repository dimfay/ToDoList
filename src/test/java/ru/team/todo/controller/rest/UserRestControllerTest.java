package ru.team.todo.controller.rest;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.hamcrest.Matchers;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestExecutionListeners(value = {DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class},
        mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
public class UserRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DatabaseSetup(value = "classpath:dbunit/user/find-all-users-dataset.xml")
    @DatabaseTearDown(value = "classpath:dbunit/user/find-all-users-dataset.xml",
            type = DatabaseOperation.DELETE_ALL)
    public void shouldFindAllUsers() throws Exception {
        mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errors").value(new ArrayList<>()))
                .andExpect(jsonPath("$.users[0].id").value(5))
                .andExpect(jsonPath("$.users[0].name").value("admin"))
                .andExpect(jsonPath("$.users[1].id").value(6))
                .andExpect(jsonPath("$.users[1].name").value("loshara"));
    }

    @Test
    @DatabaseSetup(value = "classpath:dbunit/user/find-all-users-name-dataset.xml")
    @DatabaseTearDown(value = "classpath:dbunit/user/find-all-users-name-dataset.xml",
            type = DatabaseOperation.DELETE_ALL)
    public void shouldFindUserByName() throws Exception {
        mockMvc.perform(get("/users/{name}", "admin").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errors").value(new ArrayList<>()))
                .andExpect(jsonPath("$.users.size()").value(1))
                .andExpect(jsonPath("$.users[*].id", Matchers.containsInAnyOrder(5)))
                .andExpect(jsonPath("$.users[*].name", Matchers.containsInAnyOrder("admin")));

    }

    @Test
    @DatabaseSetup(value = "classpath:dbunit/user/add-user-dataset.xml")
    @ExpectedDatabase(value = "classpath:dbunit/user/add-user-expected.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown(value = "classpath:dbunit/user/add-user-expected.xml",
            type = DatabaseOperation.DELETE_ALL)
    public void shouldAddUser() throws Exception {
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(addUserJSON()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errors").value(new ArrayList<>()))
                .andExpect(jsonPath("$.user.name").value("admin"));
    }

    @Test
    @DatabaseSetup(value = "classpath:dbunit/user/delete-user-dataset.xml")
    @ExpectedDatabase(value = "classpath:dbunit/user/delete-user-expected.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown(value = "classpath:dbunit/user/delete-user-teardown.xml",
            type = DatabaseOperation.DELETE_ALL)
    public void shouldDeleteUser() throws Exception {
        mockMvc.perform(delete("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(deleteUserJSON()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errors").value(new ArrayList<>()));
    }

    private static String deleteUserJSON() throws JSONException {
        return new JSONObject()
                .put("name", "loshara")
                .toString();
    }

    private static String addUserJSON() throws JSONException {
        return new JSONObject()
                .put("name", "admin")
                .toString();
    }

}