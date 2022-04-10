package ru.team.todo.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.team.todo.domain.User;
import ru.team.todo.dto.tasks.AddTaskRequest;
import ru.team.todo.dto.tasks.AddTaskResponse;
import ru.team.todo.repository.TaskRepository;
import ru.team.todo.ui.ConsoleSession;
import ru.team.todo.validation.requests.task.AddTaskRequestValidation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class TaskServiceAddTest {

    @Mock
    private TaskRepository repository;

    @Mock
    private AddTaskRequestValidation addUserValidationService;

    @Mock
    private ConsoleSession consoleSession;

    @InjectMocks
    private TaskService service;

    @Test
    public void shouldSaveTask() {
        var request = new AddTaskRequest("testTask", "desc");
        when(addUserValidationService.validate(request)).thenReturn(List.of());
        when(consoleSession.getSwitchedUser()).thenReturn(new User("testUser"));

        AddTaskResponse result = service.addTask(request);

        verify(addUserValidationService).validate(any());
        verify(consoleSession).getSwitchedUser();

        AddTaskResponse excepted = new AddTaskResponse(List.of());

        assertEquals(result, excepted);

    }

}
