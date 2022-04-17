package ru.team.todo.dto.tasks;

import lombok.Data;
import lombok.NonNull;

@Data
public class UnlinkTaskRequest {

    @NonNull
    private final String firstName;
    @NonNull
    private final String secondName;

}
