package ru.team.todo.dto.tasks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddTaskRequest implements NameableTaskRequest {
    private String userName;
    private String taskName;
    private String taskDescription;
}
