package com.chhabrarahul.tasksnotes.tasks;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TasksService {
    private TasksRepository tasksRepository;
    private ModelMapper modelMapper;

    // constructor


    public TasksService(TasksRepository tasksRepository, ModelMapper modelMapper){
        this.tasksRepository = tasksRepository;
        this.modelMapper = modelMapper;
    }

    List<TaskDto> getAllTheTasks() {
        // TODO : Use the repository to get all the tasks
        // TODO : Convert the task entity to task dto to
        //  send back response to controller
        // TODO : Use model mapper to convert entity to dto
        // TODO : return the list of task dtos

        var tasks = tasksRepository.findAll();
        return tasks
                .stream()
                .map(task -> modelMapper.map(task, TaskDto.class)).toList();

    }

    TaskDto createNewTask(TaskDto task) {

       // TODO : Validation the taskDto to validate dueDate < today.
        if(task.getDueDate() != null && task.getDueDate().before(new Date())){
            throw new TaskAlreadyExistsException("Task due date is in the past");
        }
        // TODO : Check if the body of the TaskDto is not null.
        if((task.getName() != null && task.getName().length() <= 1) || task.getDueDate() == null){
            throw new TaskInvalidException("Task name or due date is null");
        }
        var taskEntity = modelMapper.map(task,TaskEntity.class);
        var savedTask = tasksRepository.save(taskEntity);
        return modelMapper.map(savedTask, TaskDto.class);
    }
    TaskDto getTaskById(Long taskId) {
        TaskEntity task  = tasksRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + taskId));

        TaskDto taskDto = modelMapper.map(task,TaskDto.class);

        return taskDto;
    }

    static class TaskNotFoundException extends IllegalArgumentException{
        public TaskNotFoundException(String message) {
            super(message);
        }
    }
    static class TaskAlreadyExistsException extends IllegalArgumentException{
        public TaskAlreadyExistsException(String message) {
            super(message);
        }
    }
    static class TaskInvalidException extends IllegalArgumentException{
        public TaskInvalidException(String message) {
            super(message);
        }
    }
}
