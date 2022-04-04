package ru.team.todo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "linkedTasks")
public class LinkedTasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "taskId", nullable = false)
    private int taskId;
    @Column(name = "linkedTaskId")
    private int linkedTaskId;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTaskId() {
        return this.taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getLinkedTaskId() {
        return this.linkedTaskId;
    }

    public void setLinkedTaskId(int linkedTaskId) {
        this.linkedTaskId = linkedTaskId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkedTasks that = (LinkedTasks) o;
        return taskId == that.taskId && linkedTaskId == that.linkedTaskId && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taskId, linkedTaskId);
    }

    @Override
    public String toString() {
        return "LinkedTasks{" +
                "id=" + id +
                ", taskId=" + taskId +
                ", linkedTaskId=" + linkedTaskId +
                '}';
    }
}
