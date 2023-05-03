package com.example.bloglv4.global.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResponseDto{

    private String message;
    private int value;

    public ExceptionResponseDto(String message, int value) {
        this.message = message;
        this.value = value;
    }
}
