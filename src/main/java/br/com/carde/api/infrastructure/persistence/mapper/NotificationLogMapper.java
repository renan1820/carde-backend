package br.com.carde.api.infrastructure.persistence.mapper;

import br.com.carde.api.domain.model.NotificationLog;
import br.com.carde.api.infrastructure.persistence.entity.NotificationLogEntity;
import org.springframework.stereotype.Component;

@Component
public class NotificationLogMapper {

    public NotificationLog toDomain(NotificationLogEntity entity) {
        return new NotificationLog(
                entity.getId(),
                entity.getTitle(),
                entity.getBody(),
                entity.getSentAt(),
                entity.getSentBy()
        );
    }

    public NotificationLogEntity toEntity(NotificationLog log) {
        NotificationLogEntity entity = new NotificationLogEntity();
        entity.setId(log.id());
        entity.setTitle(log.title());
        entity.setBody(log.body());
        entity.setSentAt(log.sentAt());
        entity.setSentBy(log.sentBy());
        return entity;
    }
}
