package ru.team.todo.dto.tasks;

import lombok.Data;
import lombok.NonNull;

@Data
public class DeleteTaskByNameRequest implements NameableTaskRequest {
    @NonNull
    private final String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return null;
    }
}
