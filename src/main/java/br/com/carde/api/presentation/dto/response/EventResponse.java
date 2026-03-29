package br.com.carde.api.presentation.dto.response;

import java.time.OffsetDateTime;

public record EventResponse(
        String id,
        String title,
        String description,
        OffsetDateTime date,
        String imageUrl,
        boolean isFeatured
) {}
