package ru.team.todo.services.acceptance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ru.team.todo.config.ToDoConfiguration;
import ru.team.todo.domain.User;
import ru.team.todo.dto.users.AddUserRequest;
import ru.team.todo.dto.users.AddUserResponse;
import ru.team.todo.repository.UserRepository;
import ru.team.todo.services.UserService;
import ru.team.todo.validation.requests.user.AddUserRequestValidation;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserServiceAddTest {

    @Mock
    private AddUserRequestValidation addUserRequestValidation;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @Test
    public void shouldAddUserTest() {
        var request = new AddUserRequest("user");
        Mockito.when(addUserRequestValidation.validate(request)).thenReturn(List.of());
        Mockito.when(userRepository.findByName("user")).thenReturn(null);

        var result = userService.addUser(request);

        verify(addUserRequestValidation).validate(any());
        verify(userRepository).findByName(any());
        verify(userRepository).add(any());

        var expected = new AddUserResponse(List.of());
        assertEquals(expected, result);
    }

}
