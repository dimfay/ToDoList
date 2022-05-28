package ru.team.todo.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.team.todo.domain.Task;
import ru.team.todo.domain.User;
import ru.team.todo.dto.linkedtasks.LinkTaskRequest;
import ru.team.todo.dto.linkedtasks.LinkTaskResponse;
import ru.team.todo.repository.LinkedTasksRepository;
import ru.team.todo.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LinkedTaskServiceLinkTest {
    @Mock
    private LinkedTasksRepository linkedTasksRepository;
    @Mock
    private TaskRepository taskRepository;
    @InjectMocks
    private LinkedTaskService linkedTaskService;

    @Test
    public void shouldLinkTasks() {
        when(taskRepository.findById(1))
                .thenReturn(Optional.of(new Task(1, new User("testUser"), "firstTask", "desc")));
        when(taskRepository.findById(2))
                .thenReturn(Optional.of(new Task(2, new User("testUser"), "secondTask", "desc")));

        LinkTaskRequest request = new LinkTaskRequest(1, 2);
        LinkTaskResponse result = linkedTaskService.linkTask(request);

        verify(taskRepository).findById(1);
        verify(taskRepository).findById(2);
        verify(linkedTasksRepository).save(Mockito.any());

        LinkTaskResponse expected = new LinkTaskResponse(List.of());
        assertEquals(expected, result);
    }
}
