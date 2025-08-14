package com.tj330.userservice.dto;

import jakarta.validation.constraints.NotBlank;

public record UserRefreshRequest(
        @NotBlank(message = "Refresh token is required") String refreshToken
) {
}
