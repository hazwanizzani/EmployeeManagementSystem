package com.project.manager.exceptions;

public class NoProjectExistsException extends RuntimeException{


    private String message;

    public NoProjectExistsException() {
    }

    public NoProjectExistsException(String msg) {
        super(msg);
        this.message = msg;
    }
}
