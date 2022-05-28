package ru.team.todo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.team.todo.domain.LinkedTask;
import ru.team.todo.dto.linkedtasks.*;
import ru.team.todo.dto.tasks.TaskDTO;
import ru.team.todo.repository.LinkedTasksRepository;
import ru.team.todo.repository.TaskRepository;
import ru.team.todo.repository.UserRepository;
import ru.team.todo.validation.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class LinkedTaskService {

    @Autowired
    private LinkedTasksRepository linkedTaskRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    public LinkTaskResponse linkTask(LinkTaskRequest request) {
        var parentTaskId = request.getParentTaskId();
        var childTaskId = request.getChildTaskId();

        var parentTaskOpt = taskRepository.findById(parentTaskId);
        var childTaskOpt = taskRepository.findById(childTaskId);

        if (parentTaskOpt.isEmpty()) {
            return new LinkTaskResponse(List.of(new CoreError("Task with id " + parentTaskId + " is not found")));
        }
        if (childTaskOpt.isEmpty()) {
            return new LinkTaskResponse(List.of(new CoreError("Task with id " + childTaskId + " is not found")));
        }

        var parentUser = parentTaskOpt.get().getUser();
        var childUser = childTaskOpt.get().getUser();

        if (!parentUser.equals(childUser)) {
            return new LinkTaskResponse(
                    List.of(new CoreError("Tasks are from different users!")));
        }

        if (linkedTaskRepository.findByTaskIdAndLinkedTaskId(parentTaskOpt.get().getId(),
                childTaskOpt.get().getId()) != null) {
            return new LinkTaskResponse(List.of(new CoreError("Tasks are already linked!")));
        }

        linkedTaskRepository.save(new LinkedTask(parentTaskOpt.get(), childTaskOpt.get()));
        return new LinkTaskResponse(List.of());

    }

    public UnlinkTaskResponse unlinkTask(UnlinkTaskRequest request) {
        var parentTaskId = request.getParentTaskId();
        var childTaskId = request.getChildTaskId();

        var parentTaskOpt = taskRepository.findById(parentTaskId);
        var childTaskOpt = taskRepository.findById(childTaskId);

        if (parentTaskOpt.isEmpty()) {
            return new UnlinkTaskResponse(List.of(new CoreError("Task with id " + parentTaskId + " is not found")));
        }
        if (childTaskOpt.isEmpty()) {
            return new UnlinkTaskResponse(List.of(new CoreError("Task with id " + childTaskId + " is not found")));
        }

        if (linkedTaskRepository.findByTaskIdAndLinkedTaskId(parentTaskOpt.get().getId(),
                childTaskOpt.get().getId()) == null) {
            return new UnlinkTaskResponse(List.of(new CoreError("There was no link between the tasks!")));
        }
        var linkedTask = linkedTaskRepository
                .findByTaskIdAndLinkedTaskId(parentTaskOpt.get().getId(), childTaskOpt.get().getId());

        linkedTaskRepository.delete(linkedTask);
        return new UnlinkTaskResponse(List.of());
    }

    public FindLinkedTasksResponse findLinkedTasks(FindLinkedTasksRequest request) {
        var taskId = request.getId();
        if (taskId != null) {
            var task = taskRepository.findById(taskId);
            if (task.isPresent()) {
                var linkedTasksByTask = linkedTaskRepository.findAllByTask(task.get());
                var linkedTasksByLinkedTask = linkedTaskRepository.findAllByLinkedTask(task.get());
                var result = Stream.concat(linkedTasksByTask.stream(), linkedTasksByLinkedTask.stream())
                        .collect(Collectors.toList());
                return new FindLinkedTasksResponse(List.of(), convert(result));
            }
            return new FindLinkedTasksResponse(List.of(new CoreError("Requested task by id " + request.getId() +
                    " not found!")), List.of());
        }


        var linkedTasks = linkedTaskRepository.findAll();
        return new FindLinkedTasksResponse(List.of(), convert(linkedTasks));
    }

    private List<LinkedTasksDTO> convert(List<LinkedTask> linkedTasks) {
        List<LinkedTasksDTO> dtos = new ArrayList<>();
        for (LinkedTask linkedTask : linkedTasks) {
            dtos.add(convert(linkedTask));
        }
        return dtos;
    }

    private LinkedTasksDTO convert(LinkedTask linkedTask) {
        var taskDTO = new TaskDTO(linkedTask.getTask().getId(),
                linkedTask.getTask().getName(), linkedTask.getTask().getDescription());
        var linkedTaskDTO = new TaskDTO(linkedTask.getLinkedTask().getId(),
                linkedTask.getLinkedTask().getName(), linkedTask.getLinkedTask().getDescription());
        return new LinkedTasksDTO(linkedTask.getId(), taskDTO, linkedTaskDTO);
    }

}
