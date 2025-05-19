package com.chhabrarahul.tasksnotes.notes;

import com.chhabrarahul.tasksnotes.tasks.TaskEntity;
import jakarta.persistence.*;

@Entity(name = "notes")
public class NoteEntity {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long Id;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="body", nullable = false)
    private String body;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "task_id", referencedColumnName = "Id") // foreign key
    private TaskEntity task;

}
