package com.chhabrarahul.tasksnotes.tasks;


import com.chhabrarahul.tasksnotes.common.BaseEntity;
import com.chhabrarahul.tasksnotes.notes.NoteEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity(name = "tasks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TaskEntity extends BaseEntity {

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