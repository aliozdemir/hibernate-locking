package com.ozdemir.hibernatelocking.service;

import com.ozdemir.hibernatelocking.model.entity.User;
import com.ozdemir.hibernatelocking.model.response.UserResponse;
import com.ozdemir.hibernatelocking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final RetryableAddressService addressService;
    @Override
    public List<UserResponse> getUsers(long delay, boolean callAddressService) {

        log.warn("USER LIST SERVICE IS EXECUTING!!!!!!");
        List<UserResponse> response = new ArrayList<>();
        List<User> users = userRepository.findAll();
        users.forEach(user -> {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(callAddressService){
                user.setAddress(addressService.getUserAddress());
            }

            UserResponse userResponse = UserResponse.builder()
                    .email(user.getEmail())
                    .name(user.getFirstName().concat(" ").concat(user.getLastName()))
                    .address(user.getAddress())
                    .build();
            response.add(userResponse);
        });
        log.warn("USER LIST SERVICE IS EXECUTED!!!!!!");

        return response;
    }


}
