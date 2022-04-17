package ru.team.todo.dto.tasks;

import lombok.Data;
import lombok.NonNull;


@Data
public class AddTaskRequest implements NameableTaskRequest {

    @NonNull
    private final String name;
    @NonNull
    private final String description;
}
