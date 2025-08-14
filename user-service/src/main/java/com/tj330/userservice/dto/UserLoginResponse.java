package com.tj330.userservice.dto;

public record UserLoginResponse(
        String token,
        String tokenType,
        String refreshToken,
        String username,
        String Role
) {
}
