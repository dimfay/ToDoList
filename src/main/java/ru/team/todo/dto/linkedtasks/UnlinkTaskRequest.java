package ru.team.todo.dto.linkedtasks;

import java.util.Objects;

public class UnlinkTaskRequest {
    private final String firstTask;
    private final String secondTask;

    public UnlinkTaskRequest(String firstTask, String secondTask){
        this.firstTask = firstTask;
        this.secondTask = secondTask;
    }

    public String getFirstTask() {
        return firstTask;
    }

    public String getSecondTask() {
        return secondTask;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnlinkTaskRequest that = (UnlinkTaskRequest) o;
        return Objects.equals(firstTask, that.firstTask) && Objects.equals(secondTask, that.secondTask);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstTask, secondTask);
    }

    @Override
    public String toString() {
        return "UnlinkTaskRequest{" +
                "firstTask='" + firstTask + '\'' +
                ", secondTask='" + secondTask + '\'' +
                '}';
    }
}
