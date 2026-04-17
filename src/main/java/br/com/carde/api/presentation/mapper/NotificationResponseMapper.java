package br.com.carde.api.presentation.mapper;

import br.com.carde.api.domain.model.NotificationLog;
import br.com.carde.api.presentation.dto.response.NotificationLogResponse;
import org.springframework.stereotype.Component;

@Component
public class NotificationResponseMapper {

    public NotificationLogResponse toResponse(NotificationLog log) {
        return new NotificationLogResponse(
                log.id(),
                log.title(),
                log.body(),
                log.sentAt(),
                log.sentBy()
        );
    }
}
