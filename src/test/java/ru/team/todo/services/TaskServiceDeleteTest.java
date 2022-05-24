package ru.team.todo.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.team.todo.domain.Task;
import ru.team.todo.domain.User;
import ru.team.todo.dto.tasks.DeleteTaskRequest;
import ru.team.todo.dto.tasks.DeleteTaskResponse;
import ru.team.todo.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskServiceDeleteTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService service;

    @Test
    public void shouldDeleteSuccessfully(){
        DeleteTaskRequest request = new DeleteTaskRequest(1);

        when(taskRepository.findById(1)).thenReturn(Optional.of(new Task(1, new User("testUser"), "testTask", "desc")));

        DeleteTaskResponse result = service.deleteTask(request);

        verify(taskRepository).delete(any());

        DeleteTaskResponse expected = new DeleteTaskResponse(List.of());

        assertEquals(expected, result);
    }
}
