package br.com.carde.api.infrastructure.persistence.mapper;

import br.com.carde.api.domain.model.MuseumEvent;
import br.com.carde.api.infrastructure.persistence.entity.EventEntity;
import org.springframework.stereotype.Component;

@Component
public class EventEntityMapper {

    public MuseumEvent toDomain(EventEntity entity) {
        return new MuseumEvent(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getEventDate(),
                entity.getImageUrl(),
                entity.isFeatured()
        );
    }
}
