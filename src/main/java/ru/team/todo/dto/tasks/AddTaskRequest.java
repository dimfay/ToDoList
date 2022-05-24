package ru.team.todo.dto.tasks;

import java.util.Objects;

public class AddTaskRequest implements NameableTaskRequest {
    private String userName;
    private String taskName;
    private String taskDescription;

    public AddTaskRequest() {

    }

    public AddTaskRequest(String userName, String taskName, String taskDescription) {
        this.userName = userName;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddTaskRequest that = (AddTaskRequest) o;
        return Objects.equals(userName, that.userName) && Objects.equals(taskName, that.taskName) && Objects.equals(taskDescription, that.taskDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, taskName, taskDescription);
    }

    @Override
    public String toString() {
        return "AddTaskRequest{" +
                "userName='" + userName + '\'' +
                ", taskName='" + taskName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                '}';
    }
}
