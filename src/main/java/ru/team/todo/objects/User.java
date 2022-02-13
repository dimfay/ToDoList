package ru.team.todo.objects;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class User {

    private final String name;
    private final List<Task> tasks = new ArrayList<>();
}
