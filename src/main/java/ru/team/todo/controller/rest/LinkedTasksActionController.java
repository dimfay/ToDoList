package ru.team.todo.controller.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.team.todo.dto.linkedtasks.*;
import ru.team.todo.services.LinkedTaskService;

import javax.validation.Valid;

@RestController
@RequestMapping("/action/linkedtasks")
@AllArgsConstructor
public class LinkedTasksActionController {
    private final LinkedTaskService linkedTaskService;

    @PostMapping("/link")
    public LinkTaskResponse linkTasks(@RequestBody @Valid LinkTaskRequest request){
        return linkedTaskService.linkTask(request);
    }

    @PostMapping("/unlink")
    public UnlinkTaskResponse unlinkTasks(@RequestBody @Valid UnlinkTaskRequest request){
        return linkedTaskService.unlinkTask(request);
    }
}
