package ru.team.todo.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.team.todo.domain.LinkedTask;
import ru.team.todo.domain.Task;
import ru.team.todo.domain.User;

import ru.team.todo.dto.linkedtasks.UnlinkTaskRequest;
import ru.team.todo.dto.linkedtasks.UnlinkTaskResponse;
import ru.team.todo.repository.LinkedTasksRepository;
import ru.team.todo.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LinkedTaskServiceUnlinkTest {
    @Mock
    private LinkedTasksRepository linkedTasksRepository;
    @Mock
    private TaskRepository taskRepository;
    @InjectMocks
    private LinkedTaskService linkedTaskService;

    @Test
    public void shouldUnlinkTasks() {
        User user = new User("testUser");

        Task firstTask = new Task(1, user, "firstTask", "desc");
        Task secondTask = new Task(2, user, "secondTask", "desc");

        when(taskRepository.findById(1))
                .thenReturn(Optional.of(firstTask));
        when(taskRepository.findById(2))
                .thenReturn(Optional.of(secondTask));
        when(linkedTasksRepository
                .findByTaskIdAndLinkedTaskId(1, 2))
                .thenReturn(new LinkedTask(1, firstTask, secondTask));

        UnlinkTaskRequest request =
                new UnlinkTaskRequest(1, 2);

        UnlinkTaskResponse result = linkedTaskService.unlinkTask(request);

        verify(taskRepository).findById(1);
        verify(taskRepository).findById(2);
        verify(linkedTasksRepository, times(2)).findByTaskIdAndLinkedTaskId(1, 2);
        verify(linkedTasksRepository).delete(Mockito.any());

        UnlinkTaskResponse expected = new UnlinkTaskResponse(List.of());
        assertEquals(expected, result);
    }
}
