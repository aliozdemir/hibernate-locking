package com.ozdemir.hibernatelocking.service;

import com.ozdemir.hibernatelocking.model.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getUsers(long delay, boolean callAddressService);
}
