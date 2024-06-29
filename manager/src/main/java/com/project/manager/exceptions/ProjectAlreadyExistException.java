package com.project.manager.exceptions;

public class ProjectAlreadyExistException extends RuntimeException{

    private String message;

    public ProjectAlreadyExistException() {}

    public ProjectAlreadyExistException(String msg) {
        super(msg);
        this.message = msg;
    }
}
