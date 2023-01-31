package com.ozdemir.hibernatelocking.service;

import com.ozdemir.hibernatelocking.exception.UserAddessNotFoundException;
import com.ozdemir.hibernatelocking.model.entity.User;
import com.ozdemir.hibernatelocking.model.response.UserResponse;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

import java.util.List;

public interface UserService {
    List<UserResponse> getUsers();
}
