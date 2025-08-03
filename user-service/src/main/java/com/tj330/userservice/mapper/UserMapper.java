package com.tj330.userservice.mapper;

import com.tj330.userservice.dto.UserRegisterRequest;
import com.tj330.userservice.dto.UserRegisterResponse;
import com.tj330.userservice.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserRegisterRequest toRegisterRequest(User user) {
        return new UserRegisterRequest(user.getUsername(), user.getEmail(), user.getEmail(),  user.getPassword());
    }

    public User toEntity(UserRegisterRequest request) {
        return User.builder()
                .username(request.username())
                .email(request.email())
                .password(request.password())
                .fullName(request.fullName())
                .build();
    }

    public UserRegisterResponse toResponse(User  user) {
        return new UserRegisterResponse(user.getUsername(), user.getEmail(), user.getFullName());
    }
}
