package com.tj330.userservice.controller;

import com.tj330.userservice.dto.UserLoginRequest;
import com.tj330.userservice.dto.UserLoginResponse;
import com.tj330.userservice.dto.UserRegisterRequest;
import com.tj330.userservice.dto.UserRegisterResponse;
import com.tj330.userservice.entity.User;
import com.tj330.userservice.mapper.UserMapper;
import com.tj330.userservice.service.AuthService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Getter
@Setter
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AuthController {
    private final AuthService authService;
    private final UserMapper userMapper;

    @PostMapping("/user/register")
    @ResponseStatus(HttpStatus.CREATED)
    UserRegisterResponse createUser(@RequestBody UserRegisterRequest request) {
        return authService.signupAsUser(request);
    }

    @PostMapping("/user/login")
    @ResponseStatus(HttpStatus.OK)
    UserLoginResponse userLogin(@RequestBody UserLoginRequest request) {
       return authService.authenticate(request);
    }

    @PostMapping("/admin/register")
    @ResponseStatus(HttpStatus.CREATED)
    UserRegisterResponse createAdmin(@RequestBody UserRegisterRequest request) {
        return authService.signupAsAdmin(request);
    }

    @PostMapping("/admin/login")
    @ResponseStatus(HttpStatus.OK)
    UserLoginResponse adminLogin(@RequestBody UserLoginRequest request) {
        return authService.authenticate(request);
    }
}
