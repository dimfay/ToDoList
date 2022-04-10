package ru.team.todo.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.team.todo.domain.Task;
import ru.team.todo.domain.User;
import ru.team.todo.dto.tasks.FindTasksResponse;
import ru.team.todo.repository.TaskRepository;
import ru.team.todo.ui.ConsoleSession;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//TODO findAllTasks не до конца реализован, поэтому нет смысла сейчас делать для него тесты
//@ExtendWith(MockitoExtension.class)
class TaskServiceFindAllTest {

    //@Mock
    private TaskRepository repository;

    //@Mock
    private ConsoleSession consoleSession;

    //@InjectMocks
    private TaskService service;

    //@Test
    public void shouldReturnAllTasksSuccessfully() {
        User user = new User("testUser");
        //var request = new FindTasksRequest(List.of(new Task(1, user, "name1", "desc1"), new Task(2, user, "name2", "desc2")));
        when(consoleSession.getSwitchedUser()).thenReturn(user);

        //TODO На данный момент findAllTasks возвращает только все таски свичнутого пользователя.
        var result = service.findAllTasks(null);

        verify(consoleSession).getSwitchedUser();

        var expected = new FindTasksResponse(List.of(), List.of(new Task(1, user, "name1", "desc1"), new Task(2, user, "name2", "desc2")));

        assertEquals(expected, result);
    }

}
