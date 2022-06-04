package ru.team.todo.dto.tasks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.team.todo.dto.CoreError;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddTaskResponse {
    private List<CoreError> errors;
}
