package com.project.manager.exceptions;

public class NoRoleExistsException extends RuntimeException{


    private String message;

    public NoRoleExistsException() {
    }

    public NoRoleExistsException(String msg) {
        super(msg);
        this.message = msg;
    }
}
