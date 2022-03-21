package ru.team.todo.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.team.todo.domain.User;
import ru.team.todo.dto.tasks.AddTaskRequest;
import ru.team.todo.dto.tasks.AddTaskResponse;
import ru.team.todo.ui.ConsoleSession;
import ru.team.todo.validation.ValidationService;
import ru.team.todo.validation.requests.task.AddTaskRequestValidation;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class TaskServiceAddTest {
    @Mock
    AddTaskRequestValidation validationService;
    @Mock
    ConsoleSession consoleSession;
    @Mock
    User user;
    @InjectMocks
    TaskService taskService;

    @Test
    public void shouldSaveTask(){
        var request = new AddTaskRequest("task1", "desc");
        Mockito.when(validationService.validate(request)).thenReturn(List.of());
        Mockito.when(consoleSession.getSwitchedUser()).thenReturn(user);

        var result = taskService.addTask(request);

        Mockito.verify(user).addTask(request.getName(), request.getDescription());

    }

}
