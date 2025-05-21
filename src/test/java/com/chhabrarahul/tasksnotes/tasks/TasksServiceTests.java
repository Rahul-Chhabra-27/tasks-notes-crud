package com.chhabrarahul.tasksnotes.tasks;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Date;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TasksServiceTests {

    @Mock
    private TasksRepository tasksRepository;
    @InjectMocks
    private TasksService tasksService;
    @Mock
    private ModelMapper modelMapper;

    @Test
    public void taskServiceCreateNewTaskSuccessTest() {
        // TODO: Arrange
        var taskDto = TaskDto.builder()
                .name("Dummy Task")
                .done(false)
                .dueDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .build();
        var taskEntity = TaskEntity.builder()
                .name("Dummy Task")
                .done(false)
                .dueDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .build();
        var savedTaskEntity = TaskEntity.builder()
                .name("Dummy Task")
                .done(false)
                .dueDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 1 day in future
                .build();

        savedTaskEntity.setId(1L);
        // Mocking the behavior of the model mapper

        when(modelMapper.map(taskDto, TaskEntity.class)).thenReturn(taskEntity);
        when(tasksRepository.save(taskEntity)).thenReturn(savedTaskEntity);
        when(modelMapper.map(savedTaskEntity, TaskDto.class)).thenReturn(taskDto);

        // TODO : ACT
        var result = tasksService.createNewTask(taskDto);

//         TODO : Assert
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getId()).isEqualTo(1L);
        Assertions.assertThat(result.getName()).isEqualTo("Dummy Task");

//        var taskEntity = modelMapper.map(task,TaskEntity.class);
//        var savedTask = tasksRepository.save(taskEntity);
//        return modelMapper.map(savedTask, TaskDto.class);
    }
}
