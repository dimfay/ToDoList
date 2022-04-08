package ru.team.todo.dto.linkedtasks;

import java.util.Objects;

public class LinkTaskRequest {
    private final String firstTask;
    private final String secondTask;

    public LinkTaskRequest(String firstTask, String secondTask){
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
        LinkTaskRequest that = (LinkTaskRequest) o;
        return Objects.equals(firstTask, that.firstTask) && Objects.equals(secondTask, that.secondTask);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstTask, secondTask);
    }


}
