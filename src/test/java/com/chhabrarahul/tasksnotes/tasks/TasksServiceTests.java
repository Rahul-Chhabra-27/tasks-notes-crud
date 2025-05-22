package com.chhabrarahul.tasksnotes.tasks;


import org.assertj.core.api.AssertionErrorCollector;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TasksServiceTests {

    private static final Logger log = LoggerFactory.getLogger(TasksServiceTests.class);
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
        log.info(savedTaskEntity.getId().toString() + " ID");
        // Mocking the behavior of the model mapper

        when(modelMapper.map(taskDto, TaskEntity.class)).thenReturn(taskEntity);
        when(tasksRepository.save(taskEntity)).thenReturn(savedTaskEntity);
        when(modelMapper.map(savedTaskEntity, TaskDto.class)).thenReturn(taskDto);

        // TODO : ACT
        var result = tasksService.createNewTask(taskDto);

        // TODO : Assert
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getName()).isEqualTo("Dummy Task");

    }
    @Test
    public void taskServiceCreateNewTaskDueDatePassExceptionTest() {
        // TODO: Arrange
        var taskWithDueDateInPast = TaskEntity.builder()
                .name("Dummy Task")
                .done(false)
                .dueDate(new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24)) // previous day
                .build();
        var taskDto = TaskDto.builder()
                .name("Dummy Task")
                .done(false)
                .dueDate(new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24)) // previous day
                .build();

        // TODO : Act and Assert
        Assertions.assertThatThrownBy(() -> tasksService.createNewTask(taskDto))
                .isInstanceOf(TasksService.TaskAlreadyExistsException.class)
                .hasMessageContaining("Task due date is in the past");

    }
    @Test
    public void taskServiceCreateNewTaskTaskInvalidExceptionExceptionTest() {
        // TODO: Arrange
        var taskDto = TaskDto.builder()
                .name("")
                .done(false)
                .dueDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // next day
                .build();
        // TODO: Act && Assert
        Assertions.assertThatThrownBy(() -> tasksService.createNewTask(taskDto))
                .isInstanceOf(TasksService.TaskInvalidException.class)
                .hasMessageContaining("Task name or due date is null");
    }
}
