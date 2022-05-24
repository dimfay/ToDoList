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
import ru.team.todo.repository.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LinkedTaskServiceLinkTest {
    @Mock
    private LinkedTasksRepository linkedTasksRepository;
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private LinkedTaskService linkedTaskService;

    @Test
    public void shouldLinkTasks() {
        when(userRepository.findByName("testUser")).thenReturn(new User("testUser"));
        when(taskRepository.findByName("firstTask"))
                .thenReturn(new Task(1, new User("testUser"), "firstTask", "desc"));
        when(taskRepository.findByName("secondTask"))
                .thenReturn(new Task(2, new User("testUser"), "secondTask", "desc"));

        LinkTaskRequest request = new LinkTaskRequest("testUser", "firstTask", "secondTask");
        LinkTaskResponse result = linkedTaskService.linkTask(request);

        verify(userRepository).findByName("testUser");
        verify(taskRepository).findByName("firstTask");
        verify(taskRepository).findByName("secondTask");
        verify(linkedTasksRepository).save(Mockito.any());

        LinkTaskResponse expected = new LinkTaskResponse(List.of());
        assertEquals(expected, result);
    }
}
