package ru.team.todo.controller.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.team.todo.dto.linkedtasks.FindLinkedTasksRequest;
import ru.team.todo.dto.linkedtasks.FindLinkedTasksResponse;
import ru.team.todo.services.LinkedTaskService;

@RestController
@AllArgsConstructor
@RequestMapping("/linkedtasks")
public class LinkedTasksListRestController {
    private final LinkedTaskService linkedTaskService;

    @GetMapping
    public FindLinkedTasksResponse findAllLinkedTasks(){
        return linkedTaskService.findLinkedTasks(new FindLinkedTasksRequest(null));
    }

    @GetMapping("/{id}")
    public FindLinkedTasksResponse findLinkedTasksById(@PathVariable("id") Integer id){
        return linkedTaskService.findLinkedTasks(new FindLinkedTasksRequest(id));
    }
}
