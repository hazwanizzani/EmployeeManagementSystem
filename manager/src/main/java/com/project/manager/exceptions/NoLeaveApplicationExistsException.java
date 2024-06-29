package com.project.manager.exceptions;

public class NoLeaveApplicationExistsException extends RuntimeException {

    private String message;

    public NoLeaveApplicationExistsException() {
    }

    public NoLeaveApplicationExistsException(String msg) {
        super(msg);
        this.message = msg;
    }

}
