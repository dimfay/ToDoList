package ru.team.todo.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.team.todo.domain.User;
import ru.team.todo.dto.users.SwitchUserRequest;
import ru.team.todo.dto.users.SwitchUserResponse;
import ru.team.todo.repository.UserRepository;
import ru.team.todo.ui.ConsoleSession;
import ru.team.todo.validation.ValidationService;
import ru.team.todo.validation.requests.user.SwitchUserRequestValidation;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceSwitchTest {

    @Mock
    private UserRepository repository;

    @Mock
    private SwitchUserRequestValidation switchUserValidationService;

    @Mock
    private ConsoleSession consoleSession;

    @InjectMocks
    private UserService service;

    @Test
    public void shouldSwitchUserTest() {
        String userName = "user";
        SwitchUserRequest request = new SwitchUserRequest(userName);
        when(switchUserValidationService.validate(request)).thenReturn(List.of());
        when(repository.getUserByName(userName)).thenReturn(new User(userName));

        SwitchUserResponse result = service.switchUser(request);

        verify(switchUserValidationService).validate(any());
        verify(repository).getUserByName(any());

        SwitchUserResponse excepted = new SwitchUserResponse(List.of());

        assertEquals(result, excepted);
    }

}
