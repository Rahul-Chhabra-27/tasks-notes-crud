package com.chhabrarahul.tasksnotes.tasks;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")

public class TasksController {

    @GetMapping("")
    void getAllTheTasks() {}

    @GetMapping("/{taskId}")
    void getTaskById(@PathVariable Long taskId) {}

    @PostMapping("")
    void createNewTask(@RequestBody TaskEntity task) {}

    @PatchMapping("/{taskId}")
    void updateTheTask(@PathVariable Long taskId, @RequestBody TaskEntity task) {}



}
