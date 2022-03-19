package ru.team.todo.services.acceptance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ru.team.todo.config.ToDoConfiguration;
import ru.team.todo.dto.users.AddUserRequest;
import ru.team.todo.dto.users.AddUserResponse;
import ru.team.todo.services.UserService;

import java.util.List;

public class UserServiceAddTest {

    private ApplicationContext appContext;

    @BeforeEach
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(ToDoConfiguration.class);
    }

    @Test
    public void shouldAddUserTest() {
        String userName = "test";
        AddUserRequest request = new AddUserRequest(userName);
        AddUserResponse response = appContext.getBean(UserService.class).addUser(request);

        assertEquals(response, new AddUserResponse(List.of()));
    }

}
