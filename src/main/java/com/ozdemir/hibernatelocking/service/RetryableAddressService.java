package com.ozdemir.hibernatelocking.service;

import com.ozdemir.hibernatelocking.exception.UserAddessNotFoundException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class RetryableAddressService {

    @Retryable(value = UserAddessNotFoundException.class, maxAttempts = 4, backoff = @Backoff(delay = 500, multiplier = 3))
    public String getUserAddress(){
        String address = externalAddressSource();
        return address;
    }

    @Recover
    public String recoverAddress(UserAddessNotFoundException ex){
        return "ABC";
    }

    int errorCode = 0;
    private String externalAddressSource(){
        while(errorCode < 5){
            errorCode += 1;
            throw new UserAddessNotFoundException("User address not found!");
        }
        errorCode = 0;
        return "ABC";
    }
}
