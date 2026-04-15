package br.com.carde.api.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.OffsetDateTime;

public record EventRequest(
        @NotBlank @Size(max = 300) String title,
        @NotBlank String description,
        @NotNull OffsetDateTime date,
        @NotBlank @Size(max = 1000) String imageUrl,
        boolean featured,
        @Size(max = 1000) String externalLink
) {}
