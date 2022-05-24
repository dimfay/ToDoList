package ru.team.todo.dto.users;

public class FindUserRequest {
    private String name;

    public FindUserRequest(){

    }

    public FindUserRequest(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

