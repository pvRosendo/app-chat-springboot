package com.rosendo.app_chat.domain.user.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateAccountCredentialsDto(
        @NotBlank String username,
        @NotBlank String password,
        Long permission
) { }
