package ru.team.todo.dto;

import java.util.Objects;

public class AddUserRequest {
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AddUserRequest other = (AddUserRequest) obj;
        return Objects.equals(name, other.name);
    }

    @Override
    public String toString() {
        return "addUserRequest [name=" + name + "]";
    }


}
