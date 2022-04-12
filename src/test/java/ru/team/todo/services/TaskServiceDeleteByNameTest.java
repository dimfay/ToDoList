package ru.team.todo.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.team.todo.domain.Task;
import ru.team.todo.domain.User;
import ru.team.todo.dto.tasks.DeleteTaskByNameRequest;
import ru.team.todo.dto.tasks.DeleteTaskByNameResponse;
import ru.team.todo.repository.TaskRepository;
import ru.team.todo.ui.ConsoleSession;
import ru.team.todo.validation.requests.task.DeleteTaskByNameRequestValidation;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceDeleteByNameTest {

    @Mock
    private TaskRepository repository;

    @Mock
    private DeleteTaskByNameRequestValidation validationService;

    @Mock
    private ConsoleSession consoleSession;

    @InjectMocks
    private TaskService service;

    @Test
    public void shouldDeleteSuccessfully() {
        String taskMame = "testTask";
        String taskDescription = "desc";
        var request = new DeleteTaskByNameRequest("testTask");
        when(validationService.validate(request)).thenReturn(List.of());
        User user = new User("testUser");
        when(consoleSession.getSwitchedUser()).thenReturn(user);
        when(repository.findByName(taskMame)).thenReturn(new Task(1, user, taskMame, taskDescription));

        var result = service.deleteTaskByName(request);

        verify(validationService).validate(any());
        verify(consoleSession).getSwitchedUser();
        verify(repository).remove(any());

        var expected = new DeleteTaskByNameResponse(List.of());

        assertEquals(expected, result);
    }

}
