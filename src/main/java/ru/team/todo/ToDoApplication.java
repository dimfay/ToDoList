package ru.team.todo;

import ru.team.todo.managers.TaskManager;
import ru.team.todo.objects.Task;

public class ToDoApplication {

    private static final TaskManager manager = new TaskManager();

    public static void main(String[] args) {

        for (int i = 1; i < 10; i++) {
            manager.addTask("TaskName-" + i, "desc-" + i);
        }

        for (Task task : manager.getAllTasks()) {
            System.out.println("testTasks: " + task);
        }

    }
}
