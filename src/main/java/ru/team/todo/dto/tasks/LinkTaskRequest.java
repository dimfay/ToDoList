package ru.team.todo.dto.tasks;

import lombok.Data;
import lombok.NonNull;

@Data
public class LinkTaskRequest {

    @NonNull
    private final String firstTask;
    @NonNull
    private final String secondTask;
}
