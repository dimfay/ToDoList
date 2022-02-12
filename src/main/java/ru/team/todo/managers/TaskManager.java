package ru.team.todo.managers;

import ru.team.todo.objects.Task;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class TaskManager {

    private final Map<Integer, Task> taskId = new HashMap<>();
    private  Map<String, Task> taskName = new HashMap<>();
    //changed


    

    private int count = 1;

    public void addTask(String name, String desc) {
        Task task = new Task(this.count, name, desc);
        this.taskId.put(this.count, task);
        this.taskName.put(name, task);

        this.count++;
    }

    public void deleteTaskById (int id) {
        Task tmp = this.taskId.get(id);
        if (tmp == null) {
            return;
        }
        

        handleDelete(id, tmp.getName());
    }

    public void deleteTaskByName(String name) {
        Task tmp = this.taskName.get(name);
        if (tmp == null) {
            return;
        }

        handleDelete(tmp.getId(), name);
    }

    /**
     * @return Возвращает список всех задач
     */
    public Collection<Task> getAllTasks() {
        return this.taskId.values();
    }

    private void handleDelete(int id, String name) {
        this.taskId.remove(id);
        this.taskName.remove(name);
    }
}
