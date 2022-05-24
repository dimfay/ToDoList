package ru.team.todo.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.team.todo.domain.User;
import ru.team.todo.dto.users.RemoveUserRequest;
import ru.team.todo.dto.users.RemoveUserResponse;
import ru.team.todo.repository.UserRepository;
import ru.team.todo.validation.requests.user.RemoveUserRequestValidation;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceRemoveTest {

    @Mock
    private UserRepository repository;

    @Mock
    private RemoveUserRequestValidation removeUserValidationService;

    @InjectMocks
    private UserService service;

    @Test
    public void shouldRemoveUserTest() {
        RemoveUserRequest request = new RemoveUserRequest("testUser");

        when(removeUserValidationService.validate(request)).thenReturn(List.of());

        when(repository.findByName("testUser")).thenReturn(new User("testUser"));

        RemoveUserResponse result = service.removeUser(request);

        verify(removeUserValidationService).validate(any());
        verify(repository).findByName("testUser");

        RemoveUserResponse excepted = new RemoveUserResponse(List.of());

        assertEquals(excepted, result);
    }

}
