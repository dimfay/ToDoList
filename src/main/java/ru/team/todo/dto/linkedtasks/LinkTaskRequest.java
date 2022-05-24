package ru.team.todo.dto.linkedtasks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkTaskRequest {
    private String userName;

    private String firstTask;
    private String secondTask;
}
