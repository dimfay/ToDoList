package ru.team.todo.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.team.todo.domain.Task;
import ru.team.todo.domain.User;
import ru.team.todo.dto.tasks.DeleteTaskByIdRequest;
import ru.team.todo.dto.tasks.DeleteTaskByIdResponse;
import ru.team.todo.repository.TaskRepository;
import ru.team.todo.ui.ConsoleSession;
import ru.team.todo.validation.requests.task.DeleteTaskByIdRequestValidation;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceDeleteByIdTest {

    @Mock
    private TaskRepository repository;

    @Mock
    private DeleteTaskByIdRequestValidation  validationService;

    @Mock
    private ConsoleSession consoleSession;

    @InjectMocks
    private TaskService service;

    @Test
    public void shouldDeleteSuccessfully(){
        var request = new DeleteTaskByIdRequest(1);
        when(validationService.validate(request)).thenReturn(List.of());
        User user = new User("testUser");
        when(consoleSession.getSwitchedUser()).thenReturn(user);
        when(repository.findById(1)).thenReturn(new Task(1, user, "testTask", "desc"));

        var result = service.deleteTaskById(request);

        verify(validationService).validate(any());
        verify(consoleSession).getSwitchedUser();
        verify(repository).remove(any());

        var expected = new DeleteTaskByIdResponse(List.of());

        assertEquals(expected, result);
    }
}
