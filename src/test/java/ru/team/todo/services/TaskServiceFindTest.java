package ru.team.todo.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.team.todo.domain.Task;
import ru.team.todo.domain.User;
import ru.team.todo.dto.tasks.FindTasksRequest;
import ru.team.todo.dto.tasks.FindTasksResponse;
import ru.team.todo.dto.tasks.TaskDTO;
import ru.team.todo.repository.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskServiceFindTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TaskService service;

    @Test
    public void shouldFindTaskSuccessfully() {
        User user = new User("testUser");
        user.setTasks(List.of(new Task(1, user, "test", "test")));

        FindTasksRequest request = new FindTasksRequest("testUser");

        when(userRepository.findByName("testUser")).thenReturn(user);

        FindTasksResponse result = service.findTask(request);

        verify(userRepository).findByName("testUser");

        FindTasksResponse expected = new FindTasksResponse(List.of(), List.of(new TaskDTO(1, "test", "test")));

        assertEquals(expected, result);
    }

}
