package com.tj330.userservice.dto;

public record UserRegisterResponse(
        String username,
        String email,
        String fullName
        ) {
}
