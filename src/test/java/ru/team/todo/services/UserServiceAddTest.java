package ru.team.todo.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ru.team.todo.dto.users.AddUserRequest;
import ru.team.todo.dto.users.AddUserResponse;
import ru.team.todo.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceAddTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;

    @Test
    public void shouldAddUserTest() {
        AddUserRequest request = new AddUserRequest("testUser");

        when(repository.findByName("testUser")).thenReturn(null);

        AddUserResponse result = service.addUser(request);

        verify(repository).findByName("testUser");

        AddUserResponse excepted = new AddUserResponse();

        assertEquals(excepted, result);
    }

}
