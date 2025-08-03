package com.tj330.userservice.dto;

import jakarta.validation.constraints.NotBlank;

public record UserRegisterRequest(
        @NotBlank(message = "username is required") String username,
        @NotBlank(message = "user email is required") String email,
        @NotBlank(message = "user password is required") String password,
        @NotBlank(message = "full name is required")  String fullName
) {
}
