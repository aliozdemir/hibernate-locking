package com.ozdemir.hibernatelocking.exception;

import lombok.Data;

@Data
public class UserExistException extends RuntimeException{

    private String email;
    private String message;

    public UserExistException(String message){
        super(message);
        this.message = message;
    }

    public UserExistException(String message, String email){
        super(message);
        this.message = message;
        this.email = email;
    }
}
