package ru.team.todo.dto.tasks;


import lombok.Data;
import ru.team.todo.validation.CoreError;
import java.util.List;

@Data
public class AddTaskResponse {

    private final List<CoreError> errors;
}
