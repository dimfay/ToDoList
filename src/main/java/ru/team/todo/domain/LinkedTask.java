package ru.team.todo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Getter
@Setter
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
                ", task=" + task.getName() +
                ", linkedTask=" + linkedTask.getName() +
                '}';
    }
}
