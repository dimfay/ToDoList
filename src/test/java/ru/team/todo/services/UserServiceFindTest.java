package ru.team.todo.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.team.todo.domain.User;
import ru.team.todo.dto.users.FindUserRequest;
import ru.team.todo.dto.users.FindUserResponse;
import ru.team.todo.dto.users.UserDTO;
import ru.team.todo.repository.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceFindTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;

    @Test
    public void shouldFindUserTest() {
        User user = new User("testUser");
        user.setId(1);
        when(repository.findByName("testUser")).thenReturn(user);

        FindUserRequest request = new FindUserRequest("testUser");

        FindUserResponse result = service.findUser(request);

        verify(repository).findByName("testUser");

        FindUserResponse excepted = new FindUserResponse(List.of(), List.of(new UserDTO(1, "testUser")));

        assertEquals(excepted, result);
    }

}
