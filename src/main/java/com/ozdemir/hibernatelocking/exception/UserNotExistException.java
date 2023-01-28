package com.ozdemir.hibernatelocking.exception;

import lombok.Data;

@Data
public class UserNotExistException extends RuntimeException{

    private String email;
    private String message;

    public UserNotExistException(String message){
        super(message);
        this.message = message;
    }

    public UserNotExistException(String message, String email){
        super(message);
        this.message = message;
        this.email = email;
    }
}
