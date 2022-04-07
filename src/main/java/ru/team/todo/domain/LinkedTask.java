package ru.team.todo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "linkedTasks")
public class LinkedTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "taskId", nullable = false)
    private int taskId;
    @Column(name = "linkedTaskId", nullable = false)
    private int linkedTaskId;

    @ManyToOne
    @JoinColumn(name = "taskId", insertable = false, updatable = false, nullable = false)
    private Task task;
    @ManyToOne
    @JoinColumn(name = "linkedTaskId", insertable = false, updatable = false, nullable = false)
    private Task linkedTask;

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
    public String toString() {
        return "LinkedTasks {" + this.task.getName() + " | " + this.linkedTask.getName() + "}";
    }
}
