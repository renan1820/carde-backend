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
                entity.isFeatured(),
                entity.getExternalLink(),
                entity.getDisplayOrder()
        );
    }

    public EventEntity toEntity(MuseumEvent event) {
        EventEntity entity = new EventEntity();
        entity.setId(event.id());
        entity.setTitle(event.title());
        entity.setDescription(event.description());
        entity.setEventDate(event.date());
        entity.setImageUrl(event.imageUrl());
        entity.setFeatured(event.featured());
        entity.setExternalLink(event.externalLink());
        entity.setDisplayOrder(event.displayOrder());
        entity.setActive(true);
        return entity;
    }
}
