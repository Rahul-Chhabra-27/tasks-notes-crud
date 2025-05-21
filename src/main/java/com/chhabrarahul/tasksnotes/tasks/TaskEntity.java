package com.chhabrarahul.tasksnotes.tasks;


import com.chhabrarahul.tasksnotes.common.BaseEntity;
import com.chhabrarahul.tasksnotes.notes.NoteEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity(name = "tasks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
