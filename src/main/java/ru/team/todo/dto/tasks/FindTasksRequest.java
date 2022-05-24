package ru.team.todo.dto.tasks;

import java.util.Objects;

public class FindTasksRequest {
    private String userName;
    //Не зачем нам пока искать конкретные таски у пользователя
    //private String task;

    public FindTasksRequest() {

    }

    public FindTasksRequest(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String name) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FindTasksRequest that = (FindTasksRequest) o;
        return Objects.equals(userName, that.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }

    @Override
    public String toString() {
        return "FindTasksRequest{" +
                "userName='" + userName + '\'' +
                '}';
    }
}
