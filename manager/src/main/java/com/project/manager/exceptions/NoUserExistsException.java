package com.project.manager.exceptions;

public class NoUserExistsException extends RuntimeException{

    private String message;

    public NoUserExistsException(){

    }

    public NoUserExistsException(String msg){
        super(msg);
        this.message = msg;
    }


}
