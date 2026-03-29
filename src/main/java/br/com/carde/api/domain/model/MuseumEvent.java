package br.com.carde.api.domain.model;

import java.time.OffsetDateTime;

public record MuseumEvent(
        String id,
        String title,
        String description,
        OffsetDateTime date,
        String imageUrl,
        boolean featured
) {}
