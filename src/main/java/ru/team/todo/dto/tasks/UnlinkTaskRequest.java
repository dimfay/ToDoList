package ru.team.todo.dto.tasks;

import java.util.Objects;

public class UnlinkTaskRequest {
    private final String firstName;
    private final String secondName;

    public UnlinkTaskRequest(String firstName, String secondName){
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnlinkTaskRequest that = (UnlinkTaskRequest) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(secondName, that.secondName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, secondName);
    }

    @Override
    public String toString() {
        return "UnlinkTaskRequest{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                '}';
    }
}
