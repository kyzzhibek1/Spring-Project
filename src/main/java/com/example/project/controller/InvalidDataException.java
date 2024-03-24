package com.example.project.controller;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String errorMessage) {
        super(errorMessage);
    }
}
