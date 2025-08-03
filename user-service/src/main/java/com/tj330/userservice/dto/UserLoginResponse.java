package com.tj330.userservice.dto;

public record UserLoginResponse(
        String token,
        String tokenType,
        String username,
        String Role
) {
}
