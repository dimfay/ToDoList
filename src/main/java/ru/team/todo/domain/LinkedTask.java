package ru.team.todo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "linkedTasks")
public class LinkedTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "taskId", referencedColumnName = "id", nullable = false)
    private Task task;
    @ManyToOne
    @JoinColumn(name = "linkedTaskId", referencedColumnName = "id", nullable = false)
    private Task linkedTask;

    public LinkedTask() {

    }

    public LinkedTask(Integer id, Task task, Task linkedTask) {
        this.id = id;
        this.task = task;
        this.linkedTask = linkedTask;
    }

    public LinkedTask(Task task, Task linkedTask) {
        this.task = task;
        this.linkedTask = linkedTask;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Task getTask() {
        return this.task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Task getLinkedTask() {
        return this.linkedTask;
    }

    public void setLinkedTask(Task linkedTask) {
        this.linkedTask = linkedTask;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkedTask that = (LinkedTask) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "LinkedTask{" +
                "id=" + id +
                ", task=" + task.getDisplayInfo() +
                ", linkedTask=" + linkedTask.getDisplayInfo() +
                '}';
    }
}
