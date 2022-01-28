package com.project.medicalanalize.logger;

public interface LoggerService {
    void commit(LoggerLevel level, String message);
}