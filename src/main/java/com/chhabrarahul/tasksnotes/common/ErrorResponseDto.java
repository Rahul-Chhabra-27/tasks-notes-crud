package com.chhabrarahul.tasksnotes.common;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;


@Data
@Builder
public class ErrorResponseDto {

    @NonNull private String message;

    public ErrorResponseDto(String message) {
        this.message = message;
    }
}
