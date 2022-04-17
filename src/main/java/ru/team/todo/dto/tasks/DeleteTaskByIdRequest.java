package ru.team.todo.dto.tasks;

import lombok.Data;
import lombok.NonNull;

@Data
public class DeleteTaskByIdRequest implements IdTaskRequest {

    @NonNull
    private final int id;
}

