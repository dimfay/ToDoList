package ru.team.todo.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.team.todo.domain.Task;
import ru.team.todo.domain.User;
import ru.team.todo.dto.tasks.FindTasksRequest;
import ru.team.todo.dto.tasks.FindTasksResponse;
import ru.team.todo.ui.ConsoleSession;
import ru.team.todo.validation.CoreError;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class TaskServiceFindAllTest {
    @Mock
    private User user;

    @Mock
    private ConsoleSession consoleSession;

    @InjectMocks
    private TaskService taskService;

    @Test
    public void shouldReturnAllTasksSuccessfully(){
        var request = new FindTasksRequest(List.of());
        Mockito.when(consoleSession.getSwitchedUser()).thenReturn(user);
        //Mockito.when(user.getAllTasks()).thenReturn(List.of(new Task(1, "task1", "desc")));
        var result = taskService.findAllTasks(request);
        var expected = new FindTasksResponse(List.of(), List.of(new Task(1, "task1", "desc")));
        assertEquals(expected, result);
    }

    @Test
    public void shouldReturnUserNullError(){
        var request = new FindTasksRequest(List.of());
        Mockito.when(consoleSession.getSwitchedUser()).thenReturn(null);

        var expected = new FindTasksResponse(List.of(new CoreError("The user is not switched")), List.of());
        var result = taskService.findAllTasks(request);

        Mockito.verifyNoInteractions(user);
    }


}
