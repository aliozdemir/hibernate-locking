package com.ozdemir.hibernatelocking.service;

import com.ozdemir.hibernatelocking.repository.UserRepository;
import com.ozdemir.hibernatelocking.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DatabaseUserDetailService implements UserDetailsService{

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> byEmail = userRepository.findByEmail(username);
        if(!byEmail.isPresent())
            throw new UsernameNotFoundException("User not found");

        return byEmail.get();
    }
}
