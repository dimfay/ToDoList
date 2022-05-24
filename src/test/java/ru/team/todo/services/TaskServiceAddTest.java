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
import ru.team.todo.repository.UserRepository;
import ru.team.todo.validation.requests.task.AddTaskRequestValidation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TaskServiceAddTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private AddTaskRequestValidation addUserValidationService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TaskService service;

    @Test
    public void shouldSaveTask() {
        when(userRepository.findByName("testUser")).thenReturn(new User("testUser"));

        AddTaskRequest request = new AddTaskRequest("testUser", "task", "desc");

        when(addUserValidationService.validate(request)).thenReturn(List.of());

        AddTaskResponse result = service.addTask(request);

        verify(addUserValidationService).validate(request);
        verify(userRepository).findByName("testUser");
        verify(taskRepository).save(any());

        AddTaskResponse excepted = new AddTaskResponse(List.of());

        assertEquals(result, excepted);

    }

}
