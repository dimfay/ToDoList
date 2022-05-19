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
import ru.team.todo.ui.ConsoleSession;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UnlinkTaskTest {
    @Mock
    LinkedTasksRepository linkedTasksRepository;
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private ConsoleSession consoleSession;
    @InjectMocks
    LinkedTaskService linkedTaskService;

    //TODO Исправить тест
    @Test
    public void shouldUnlinkTasks(){
        var firstTask = new Task(1, new User("testUser"), "First task", "desc");
        var secondTask = new Task(2, new User("testUser"), "Second task", "desc");

        var request = new UnlinkTaskRequest("First task", "Second task");
        when(consoleSession.getSwitchedUser()).thenReturn(new User("testUser"));
        when(taskRepository.findByName(request.getFirstTask()))
                .thenReturn(firstTask);
        when(taskRepository.findByName(request.getSecondTask()))
                .thenReturn(secondTask);
        when(linkedTasksRepository.findByTaskIdAndLinkedTaskId(1, 2)).thenReturn(new LinkedTask(1, firstTask, secondTask));

        var result = linkedTaskService.unlinkTask(request);

        verify(consoleSession).getSwitchedUser();
        verify(taskRepository, Mockito.times(2)).findByName(Mockito.any());
        verify(linkedTasksRepository).findByTaskIdAndLinkedTaskId(1, 2);
        verify(linkedTasksRepository).delete(Mockito.any());

        var expected = new UnlinkTaskResponse(List.of());
        assertEquals(expected, result);
    }
}
