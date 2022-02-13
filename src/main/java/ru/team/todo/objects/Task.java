package ru.team.todo.objects;

import lombok.Data;

@Data
public class Task {

    private final int id;
    private final String name;
    private final String desc;
}
