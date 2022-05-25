package ru.team.todo.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.team.todo.domain.User;
import ru.team.todo.dto.users.DeleteUserRequest;
import ru.team.todo.dto.users.DeleteUserResponse;
import ru.team.todo.repository.UserRepository;
import ru.team.todo.validation.requests.user.RemoveUserRequestValidation;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceDeleteTest {

    @Mock
    private UserRepository repository;

    @Mock
    private RemoveUserRequestValidation removeUserValidationService;

    @InjectMocks
    private UserService service;

    @Test
    public void shouldRemoveUserTest() {
        DeleteUserRequest request = new DeleteUserRequest("testUser");

        when(removeUserValidationService.validate(request)).thenReturn(List.of());

        when(repository.findByName("testUser")).thenReturn(new User("testUser"));

        DeleteUserResponse result = service.removeUser(request);

        verify(removeUserValidationService).validate(any());
        verify(repository).findByName("testUser");

        DeleteUserResponse excepted = new DeleteUserResponse(List.of());

        assertEquals(excepted, result);
    }

}
