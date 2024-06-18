package com.rosendo.app_chat.domain.profile.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserProfileDto(
        @NotBlank String username,
        @NotBlank String fullName,
        @NotBlank String profileName,
        @NotBlank @Email String email
) {
}
