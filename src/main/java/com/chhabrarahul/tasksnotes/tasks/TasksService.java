package com.chhabrarahul.tasksnotes.tasks;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

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

       // TODO : From the controller we are getting TaskDto
        // TODO : CONVERT THIS INTO TASK ENITY  USE REPO LAYER
        // TODO : Convert the task entity to task dto to
        //  send back response to controller

        var taskEntity = modelMapper.map(task,TaskEntity.class);
        var savedTask = tasksRepository.save(taskEntity);
        return modelMapper.map(savedTask, TaskDto.class);
    }
    TaskDto getTaskById(Long taskId) {
        TaskEntity task  = tasksRepository.findById(taskId).orElse(null);

        TaskDto taskDto = modelMapper.map(task,TaskDto.class);

        return taskDto;
    }
}
