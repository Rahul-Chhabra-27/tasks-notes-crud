package com.chhabrarahul.tasksnotes.tasks;

import com.chhabrarahul.tasksnotes.common.*;
import org.springframework.http.HttpStatus;
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
    ResponseEntity<TaskDto> getTaskById(@PathVariable Long taskId) {
        TaskDto task = tasksService.getTaskById(taskId);
        return ResponseEntity.ok(task);
    }

    @PostMapping("")
    ResponseEntity<TaskDto> createNewTask(@RequestBody TaskDto task) {
         var savedTask =  tasksService.createNewTask(task);
         return ResponseEntity.created(URI
                 .create("/tasks/" + savedTask.getId())).body(savedTask);
    }

    @PatchMapping("/{taskId}")
    void updateTheTask(@PathVariable Long taskId, @RequestBody TaskEntity task) {}

    // This method will handle the error and send the response with the meaning full message
    // to the client.
    @ExceptionHandler({ TasksService.TaskNotFoundException.class,
            TasksService.TaskInvalidException.class,
            TasksService.TaskAlreadyExistsException.class})

    ResponseEntity<ErrorResponseDto> handleError(Exception exception) {
        if(exception instanceof TasksService.TaskNotFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponseDto(exception.getMessage()));
        }
        if(exception instanceof TasksService.TaskInvalidException){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ErrorResponseDto(exception.getMessage()));
        }

        if(exception instanceof TasksService.TaskAlreadyExistsException){
            return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(new ErrorResponseDto(exception.getMessage()));
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponseDto("Something went wrong"));
    }
}
