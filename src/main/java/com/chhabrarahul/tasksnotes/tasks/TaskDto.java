package com.chhabrarahul.tasksnotes.tasks;

import jakarta.annotation.Nullable;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDto {

    @Nullable private Long id;
    @Nullable private String name;
    @Nullable private Boolean done;
    @Nullable private Date dueDate;
}
