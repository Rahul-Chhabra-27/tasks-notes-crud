package com.chhabrarahul.tasksnotes.tasks;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.scheduling.config.Task;

import java.util.Date;

// TODO: We want to test the Repo layer
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class TasksRepositoryTests {

    @Autowired
    private TasksRepository tasksRepository;

    // TODO : Testing the save method.
    @Test
    public void taskRepositorySaveAllTest() {
        // TODO : Arrange => creating an environment for the test
        var dummyTask = TaskEntity.builder()
                .name("Dummy Task")
                .done(false)
                .dueDate(new Date())
                .build();
        // TODO : Act
        var savedTask = tasksRepository.save(dummyTask);

        // TODO : Assert
        Assertions.assertThat(savedTask).isNotNull();
        Assertions.assertThat(savedTask.getId()).isGreaterThan(0);
        Assertions.assertThat(savedTask.getName()).isEqualTo("Dummy Task");
    }
    @Test
    public void taskRepositoryFindAllTest() {
       // TODO : Arrange
        var task1 = TaskEntity.builder()
                .name("Dummy Task 1")
                .done(false)
                .dueDate(new Date())
                .build();
        var task2 = TaskEntity.builder()
                .name("Dummy Task 2")
                .done(false)
                .dueDate(new Date())
                .build();
        tasksRepository.save(task1);
        tasksRepository.save(task2);

        // TODO: ACT
        var taskList = tasksRepository.findAll();

        // TODO: Assert
        Assertions.assertThat(taskList).isNotNull();
        Assertions.assertThat(taskList.size()).isEqualTo(2);
        Assertions.assertThat(taskList.get(0).getName()).isEqualTo("Dummy Task 1");
        Assertions.assertThat(taskList.get(1).getName()).isEqualTo("Dummy Task 2");
    }
    @Test
    public void taskRepositoryFindByIdSuccessTest() {}
    @Test
    public void taskRepositoryFindByIdFailureTest() {}
}
