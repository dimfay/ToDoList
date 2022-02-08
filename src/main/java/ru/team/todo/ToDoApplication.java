package ru.team.todo;

import ru.team.todo.managers.TaskManager;
import ru.team.todo.objects.Task;

import java.util.*;

public class ToDoApplication {

    private static final TaskManager manager = new TaskManager();

    public static void main(String[] args) {

        System.out.println("1.Add the task\n2.Get all tasks\n3.Delete the task");
        TaskManager taskManager = new TaskManager();
        taskManager.addTask("a1", "b1");
        taskManager.addTask("a2", "b2");
        String token = "y";

        while("y".equals(token)) {
            Scanner scanner = new Scanner(System.in);
            int response = Integer.parseInt(scanner.nextLine());
            switch (response) {
                case 1: {
                    String name = scanner.nextLine();
                    String description = scanner.nextLine();
                    taskManager.addTask(name, description);
                    break;
                }
                case 2: {
                    Collection<Task> tasks = taskManager.getAllTasks();
                    printAll(tasks);
                    break;
                }
                case 3: {
                    String name = scanner.nextLine();
                    taskManager.deleteTaskByName(name);
                    break;
                }
            }
            System.out.println("Повторить? y/n");
            token = scanner.next();
        }
    }

    private static void printAll(Collection<Task> tasks){
        for (Task task : tasks){
            System.out.println(task);
        }
    }
}
