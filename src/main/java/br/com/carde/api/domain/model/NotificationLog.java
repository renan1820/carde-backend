package br.com.carde.api.domain.model;

import java.time.OffsetDateTime;

public record NotificationLog(
        String id,
        String title,
        String body,
        OffsetDateTime sentAt,
        String sentBy
) {}
