package ru.team.todo.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.team.todo.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceFindTest {

    @Mock
    private UserRepository repository;

    @Mock
    private FindUserRequestValidation findUserValidationService;

    @InjectMocks
    private UserService service;

    @Test
    public void shouldFindAllUsersTest() {
        //Запрос без конкретных пользователей должен вернуть полный список всех пользователей
        /*FindUserRequest request = new FindUserRequest(List.of());
        when(findUserValidationService.validate(request)).thenReturn(List.of());
        when(repository.findAll()).thenReturn(List.of(new User("user_1"), new User("user_2")));

        FindUserResponse result = service.findUsers(request);

        verify(findUserValidationService).validate(any());
        verify(repository).findAll();

        FindUserResponse excepted = new FindUserResponse(List.of(), List.of(new User("user_1"), new User("user_2")));

        assertEquals(excepted, result);*/
    }

}
