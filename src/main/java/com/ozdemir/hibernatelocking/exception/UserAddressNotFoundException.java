package com.ozdemir.hibernatelocking.exception;

public class UserAddressNotFoundException extends RuntimeException{

    public UserAddressNotFoundException(String msg){
        super(msg);
    }
}
