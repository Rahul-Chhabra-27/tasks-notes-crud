package com.chhabrarahul.tasksnotes.tasks;


import com.chhabrarahul.tasksnotes.notes.NoteEntity;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity(name = "tasks")
public class TaskEntity {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long Id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name ="due_date", nullable = false)
    private Date dueDate;

    @Column(name = "done", nullable = false)
    private boolean done; // whether the task is done or not

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    List<NoteEntity> notes;
}

// primary key in a relation / table is used to uniquely identify the record/tuple inside the table

//Table products
//
//        P1 @primary key id : 1 name : 'Macbook' color : 'midnignt'
//        P2 @primary key id : 2 name : 'Macbook' color : 'silver'