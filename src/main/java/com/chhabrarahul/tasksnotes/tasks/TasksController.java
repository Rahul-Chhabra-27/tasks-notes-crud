package com.chhabrarahul.tasksnotes.tasks;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tasks")

public class TasksController {
    private TasksService tasksService;

    public TasksController(TasksService tasksService){
        this.tasksService = tasksService;
    }

    @GetMapping("")
    ResponseEntity<List<TaskDto>> getAllTheTasks() {
        var tasks = tasksService.getAllTheTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{taskId}")
    void getTaskById(@PathVariable Long taskId) {}

    @PostMapping("")
    ResponseEntity<TaskDto> createNewTask(@RequestBody TaskDto task) {
         var savedTask =  tasksService.createNewTask(task);
         return ResponseEntity.created(URI
                 .create("/tasks/" + savedTask.getId())).body(savedTask);
    }

    @PatchMapping("/{taskId}")
    void updateTheTask(@PathVariable Long taskId, @RequestBody TaskEntity task) {}



}
