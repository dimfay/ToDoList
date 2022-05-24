package ru.team.todo.dto.tasks;

import ru.team.todo.dto.users.UserDTO;

public class TaskDTO {
    private Integer id;
    private UserDTO user;
    private String name;
    private String description;

    public TaskDTO(Integer id, UserDTO user, String name, String description) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
