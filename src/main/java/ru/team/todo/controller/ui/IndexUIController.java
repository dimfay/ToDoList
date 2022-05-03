package ru.team.todo.controller.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexUIController {

    @GetMapping("/ui/")
    public String getIndex(@RequestParam(value = "name", defaultValue = "World", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }

}
