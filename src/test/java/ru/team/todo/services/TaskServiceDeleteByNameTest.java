package ru.team.todo.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.team.todo.domain.User;
import ru.team.todo.dto.tasks.DeleteTaskByNameRequest;
import ru.team.todo.dto.tasks.DeleteTaskByNameResponse;
import ru.team.todo.ui.ConsoleSession;
import ru.team.todo.validation.requests.task.DeleteTaskByNameRequestValidation;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class TaskServiceDeleteByNameTest {
    @Mock
    DeleteTaskByNameRequestValidation validationService;
    @Mock
    ConsoleSession consoleSession;
    @Mock
    User user;
    @InjectMocks
    TaskService taskService;

    @Test
    public void shouldDeleteSuccessfully(){
        var request = new DeleteTaskByNameRequest("task");
        Mockito.when(validationService.validate(request)).thenReturn(List.of());
        Mockito.when(consoleSession.getSwitchedUser()).thenReturn(user);

        var result = taskService.deleteTaskByName(request);
        var expected = new DeleteTaskByNameResponse(List.of());
        //Mockito.verify(user).deleteTaskByName(request.getName());
        assertEquals(expected, result);
    }

}
