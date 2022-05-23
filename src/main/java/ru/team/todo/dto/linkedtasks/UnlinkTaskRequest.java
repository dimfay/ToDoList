package ru.team.todo.dto.linkedtasks;

import java.util.Objects;

public class UnlinkTaskRequest {
    private String userName;

    private String firstTask;
    private String secondTask;

    public UnlinkTaskRequest() {

    }

    public UnlinkTaskRequest(String userName, String firstTask, String secondTask) {
        this.userName = userName;
        this.firstTask = firstTask;
        this.secondTask = secondTask;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstTask() {
        return this.firstTask;
    }

    public void setFirstTask(String firstTask) {
        this.firstTask = firstTask;
    }

    public String getSecondTask() {
        return this.secondTask;
    }

    public void setSecondTask(String secondTask) {
        this.secondTask = secondTask;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnlinkTaskRequest that = (UnlinkTaskRequest) o;
        return Objects.equals(userName, that.userName) && Objects.equals(firstTask, that.firstTask) && Objects.equals(secondTask, that.secondTask);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, firstTask, secondTask);
    }

    @Override
    public String toString() {
        return "UnlinkTaskRequest{" +
                "userName='" + userName + '\'' +
                ", firstTask='" + firstTask + '\'' +
                ", secondTask='" + secondTask + '\'' +
                '}';
    }
}
