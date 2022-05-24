package ru.team.todo.dto.tasks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.team.todo.validation.CoreError;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteTaskResponse {
    private List<CoreError> errors;
}
