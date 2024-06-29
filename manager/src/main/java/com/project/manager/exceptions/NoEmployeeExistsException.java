package com.project.manager.exceptions;

public class NoEmployeeExistsException extends RuntimeException{


    private String message;

    public NoEmployeeExistsException() {
    }

    public NoEmployeeExistsException(String msg) {
        super(msg);
        this.message = msg;
    }
}
