package ru.team.todo.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.team.todo.domain.User;
import ru.team.todo.dto.tasks.FindTasksRequest;
import ru.team.todo.dto.tasks.FindTasksResponse;
import ru.team.todo.repository.TaskRepository;
import ru.team.todo.repository.UserRepository;
import ru.team.todo.validation.requests.task.FindTaskRequestValidation;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskServiceFindTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private FindTaskRequestValidation validation;

    @InjectMocks
    private TaskService service;

    @Test
    public void shouldFindTaskSuccessfully() {
        User user = new User("testUser");
        user.setTasks(Collections.emptySet());

        //На данный момент этот запрос игнорирует список пользователей которых надо извлечь, поэтому он извлекает пока что всех.
        FindTasksRequest request = new FindTasksRequest("testUser", List.of("firstTask", "secondTask"));

        when(validation.validate(request)).thenReturn(List.of());
        when(userRepository.findByName("testUser")).thenReturn(user);
        //when(taskRepository.findByName("firstTask")).thenReturn(new Task(1, user, "firstTask", "desc"));
        //when(taskRepository.findByName("secondTask")).thenReturn(new Task(2, user, "secondTask", "desc"));

        FindTasksResponse result = service.findTasks(request);

        verify(userRepository).findByName("testUser");
        verify(validation).validate(any());

        FindTasksResponse expected = new FindTasksResponse(List.of(), List.of());

        assertEquals(expected, result);
    }

}
