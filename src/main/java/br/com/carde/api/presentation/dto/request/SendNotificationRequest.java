package br.com.carde.api.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SendNotificationRequest(
        @NotBlank @Size(max = 100) String title,
        @NotBlank @Size(max = 255) String body
) {}
