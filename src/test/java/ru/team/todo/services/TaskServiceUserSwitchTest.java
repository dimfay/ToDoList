package ru.team.todo.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.team.todo.dto.tasks.FindTasksResponse;
import ru.team.todo.repository.TaskRepository;
import ru.team.todo.ui.ConsoleSession;
import ru.team.todo.validation.CoreError;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskServiceUserSwitchTest {

    @Mock
    private TaskRepository repository;

    @Mock
    private ConsoleSession consoleSession;

    @InjectMocks
    private TaskService service;

    @Test
    public void shouldReturnUserNullError() {
        when(consoleSession.getSwitchedUser()).thenReturn(null);

        //TODO findAllTasks не реализован
        var result = service.findAllTasks(null);

        verify(consoleSession).getSwitchedUser();

        var expected = new FindTasksResponse(List.of(new CoreError("The user is not switched")), List.of());

        assertEquals(expected, result);
    }
}
