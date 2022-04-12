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
import ru.team.todo.ui.ConsoleSession;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LinkTaskTest {
    @Mock
    LinkedTasksRepository linkedTasksRepository;
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private ConsoleSession consoleSession;
    @InjectMocks
    LinkedTaskService linkedTaskService;

    @Test
    public void shouldLinkTasks(){
        var request = new LinkTaskRequest("First task", "Second task");
        when(consoleSession.getSwitchedUser()).thenReturn(new User("testUser"));
        when(taskRepository.findByName(request.getFirstTask()))
                .thenReturn(new Task(1, new User("testUser"), "First task", "desc"));

        when(taskRepository.findByName(request.getSecondTask()))
                .thenReturn(new Task(2, new User("testUser"), "Second task", "desc"));
        var result = linkedTaskService.linkTask(request);

        verify(consoleSession).getSwitchedUser();
        verify(taskRepository, Mockito.times(2)).findByName(Mockito.any());
        verify(linkedTasksRepository).add(Mockito.any());

        var expected = new LinkTaskResponse(List.of());
        assertEquals(expected, result);
    }
}
