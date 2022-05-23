package ru.team.todo.dto.tasks;

import java.util.List;
import java.util.Objects;

public class FindTasksRequest {
    private String userName;

    private List<String> tasks;

    public FindTasksRequest() {

    }

    public FindTasksRequest(String userName, List<String> tasks) {
        this.userName = userName;
        this.tasks = tasks;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String name) {
        this.userName = userName;
    }

    public List<String> getTasks() {
        return tasks;
    }

    public void setTasks(List<String> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FindTasksRequest that = (FindTasksRequest) o;
        return Objects.equals(userName, that.userName) && Objects.equals(tasks, that.tasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, tasks);
    }

    @Override
    public String toString() {
        return "FindTasksByNameRequest{" +
                "userName='" + userName + '\'' +
                ", tasks=" + tasks +
                '}';
    }
}
