package com.tj330.userservice.service;

import com.tj330.userservice.entity.User;
import com.tj330.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public String adminInfo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return "Hello ADMIN, " + auth.getName();
    }

    public String userInfo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return "Hello, " + auth.getName();
    }
}
