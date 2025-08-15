package com.tj330.userservice.controller;

import com.tj330.userservice.entity.User;
import com.tj330.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @GetMapping("/admin/users")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/admin")
    public String adminInfo() {
        return userService.adminInfo();
    }

    @GetMapping("/user")
    public String userInfo() {
        return userService.userInfo();
    }
}