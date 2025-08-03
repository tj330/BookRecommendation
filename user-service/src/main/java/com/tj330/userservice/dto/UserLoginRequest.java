package com.tj330.userservice.dto;

import jakarta.validation.constraints.NotBlank;

public record UserLoginRequest(
        @NotBlank(message = "username is required") String username,
        @NotBlank(message = "password is required")  String password
) {
}
