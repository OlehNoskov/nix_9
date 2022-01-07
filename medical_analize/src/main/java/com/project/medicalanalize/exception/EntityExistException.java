package com.project.medicalanalize.exception;

public class EntityExistException extends RuntimeException {

    public EntityExistException(String message) {
        super(message);
    }
}