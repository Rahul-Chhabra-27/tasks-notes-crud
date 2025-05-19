package com.chhabrarahul.tasksnotes.notes;

import com.chhabrarahul.tasksnotes.common.BaseEntity;
import com.chhabrarahul.tasksnotes.tasks.TaskEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "notes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class NoteEntity extends BaseEntity {

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="body", nullable = false)
    private String body;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "task_id", referencedColumnName = "Id") // foreign key
    private TaskEntity task;

}
