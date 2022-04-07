package ru.team.todo.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.team.todo.domain.User;
import ru.team.todo.dto.tasks.DeleteTaskByIdRequest;
import ru.team.todo.dto.tasks.DeleteTaskByIdResponse;
import ru.team.todo.ui.ConsoleSession;
import ru.team.todo.validation.requests.task.DeleteTaskByIdRequestValidation;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TaskServiceDeleteByIdTest {
    @Mock
    DeleteTaskByIdRequestValidation validationService;
    @Mock
    ConsoleSession consoleSession;
    @Mock
    User user;
    @InjectMocks
    TaskService taskService;

    @Test
    public void shouldDeleteSuccessfully(){
        var request = new DeleteTaskByIdRequest(1);
        Mockito.when(validationService.validate(request)).thenReturn(List.of());
        Mockito.when(consoleSession.getSwitchedUser()).thenReturn(user);

        var result = taskService.deleteTaskById(request);
        var expected = new DeleteTaskByIdResponse(List.of());

        //verify(user).deleteTaskById(1);
        assertEquals(expected, result);
    }
}
