package com.ozdemir.hibernatelocking.service;

import com.ozdemir.hibernatelocking.exception.UserAddressNotFoundException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class RetryableAddressService {

    @Retryable(value = UserAddressNotFoundException.class, maxAttempts = 4, backoff = @Backoff(delay = 500, multiplier = 3))
    public String getUserAddress(){
        String address = externalAddressSource();
        return address;
    }

    @Recover
    public String recoverAddress(UserAddressNotFoundException ex){
        return "ABC";
    }

    int errorCode = 0;
    private String externalAddressSource(){
        while(errorCode < 5){
            errorCode += 1;
            throw new UserAddressNotFoundException("User address not found!");
        }
        errorCode = 0;
        return "ABC";
    }
}
