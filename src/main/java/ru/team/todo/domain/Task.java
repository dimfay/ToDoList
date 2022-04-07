package ru.team.todo.domain;

import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "userId", nullable = false)
    private int userId;
    @NaturalId
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "userId", insertable = false, updatable = false, nullable = false)
    private User user;
    @OneToMany(mappedBy = "taskId", fetch = FetchType.EAGER)
    private Set<LinkedTask> linkedTasks;

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Task() {

    }

    public Task(Integer id, int userId, String name, String desc) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.description = desc;
    }

    public Task(int userId, String name, String desc) {
        this.userId = userId;
        this.name = name;
        this.description = desc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayInfo() {
        return "[ID: " + this.id + ". Name: " + this.name + ". Description: '" + this.description + "']";
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<LinkedTask> getLinkedTasks() {
        return linkedTasks;
    }

    public void setLinkedTasks(Set<LinkedTask> linkedTasks) {
        this.linkedTasks = linkedTasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return userId == task.userId && Objects.equals(id, task.id) && Objects.equals(name, task.name) && Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, name, description);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user +
                ", linkedTasks=" + linkedTasks +
                '}';
    }
}
