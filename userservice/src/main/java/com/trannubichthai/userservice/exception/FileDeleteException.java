package com.trannubichthai.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FileDeleteException extends RuntimeException {
    public FileDeleteException(String message) {
        super(message);
    }
}
