package com.ozdemir.hibernatelocking.service;

import com.ozdemir.hibernatelocking.exception.UserNotExistException;
import com.ozdemir.hibernatelocking.jwt.*;
import com.ozdemir.hibernatelocking.model.entity.Role;
import com.ozdemir.hibernatelocking.model.entity.User;
import com.ozdemir.hibernatelocking.exception.UserExistException;
import com.ozdemir.hibernatelocking.model.request.AuthenticationRequest;
import com.ozdemir.hibernatelocking.model.request.RegisterRequest;
import com.ozdemir.hibernatelocking.model.response.AuthenticationResponse;
import com.ozdemir.hibernatelocking.repository.RoleRepository;
import com.ozdemir.hibernatelocking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final Sha256PasswordManager passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;

    public void register(RegisterRequest request) {
        Optional<User> byEmail = userRepository.findByEmail(request.getEmail());
        if(byEmail.isPresent()){
            throw new UserExistException("User already exist", request.getEmail());
        }

        List<Role> roles = new ArrayList<>();
        final List<String> roleNames = request.getRoles();
        for(String roleName: roleNames){
            Optional<Role> dbRole = roleRepository.findByName(roleName);
            if (dbRole.isPresent()) {
                roles.add(dbRole.get());
            }
        }

        Set<Role> setRoles = new HashSet<>(roles);
        User user = User.builder().firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(setRoles)
                .build();
        userRepository.save(user);
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        Optional<User> user = userRepository.findByEmail(request.getEmail());
        if(!user.isPresent()){
            throw new UserNotExistException("User not found");
        }

        //Username and password checks
        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //Generate role based jwt token
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", user.get().getRoles().toString());
        final String token = jwtService.generateToken(claims, user.get());
        return AuthenticationResponse.builder().token(token).build();
    }
}
