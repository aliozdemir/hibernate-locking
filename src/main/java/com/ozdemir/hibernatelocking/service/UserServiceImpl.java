package com.ozdemir.hibernatelocking.service;

import com.ozdemir.hibernatelocking.model.entity.User;
import com.ozdemir.hibernatelocking.model.response.UserResponse;
import com.ozdemir.hibernatelocking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final RetryableAddressService addressService;
    @Override
    public List<UserResponse> getUsers() {

        List<UserResponse> response = new ArrayList<>();
        List<User> users = userRepository.findAll();
        users.forEach(user -> {
            user.setAddress(addressService.getUserAddress());
            UserResponse userResponse = UserResponse.builder()
                    .email(user.getEmail())
                    .name(user.getFirstName().concat(" ").concat(user.getLastName()))
                    .address(user.getAddress())
                    .build();
            response.add(userResponse);
        });

        return response;
    }


}
