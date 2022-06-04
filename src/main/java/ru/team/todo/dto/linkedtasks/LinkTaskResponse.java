package ru.team.todo.dto.linkedtasks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.team.todo.dto.CoreError;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkTaskResponse {
    private List<CoreError> errors;
}
