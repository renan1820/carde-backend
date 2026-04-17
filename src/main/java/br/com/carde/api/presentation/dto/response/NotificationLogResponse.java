package br.com.carde.api.presentation.dto.response;

import java.time.OffsetDateTime;

public record NotificationLogResponse(
        String id,
        String title,
        String body,
        OffsetDateTime sentAt,
        String sentBy
) {}
