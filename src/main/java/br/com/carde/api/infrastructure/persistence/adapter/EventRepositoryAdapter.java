package br.com.carde.api.infrastructure.persistence.adapter;

import br.com.carde.api.domain.model.MuseumEvent;
import br.com.carde.api.domain.repository.EventRepository;
import br.com.carde.api.infrastructure.persistence.jpa.EventJpaRepository;
import br.com.carde.api.infrastructure.persistence.mapper.EventEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EventRepositoryAdapter implements EventRepository {

    private final EventJpaRepository jpaRepository;
    private final EventEntityMapper mapper;

    @Override
    public Page<MuseumEvent> findAll(Pageable pageable) {
        return jpaRepository.findByActiveTrueOrderByDisplayOrderAsc(pageable)
                .map(mapper::toDomain);
    }

    @Override
    public Page<MuseumEvent> findFeatured(Pageable pageable) {
        return jpaRepository.findByFeaturedTrueAndActiveTrueOrderByDisplayOrderAsc(pageable)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<MuseumEvent> findById(String id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public MuseumEvent save(MuseumEvent event) {
        return jpaRepository.findById(event.id()).map(existing -> {
            existing.setTitle(event.title());
            existing.setDescription(event.description());
            existing.setEventDate(event.date());
            existing.setImageUrl(event.imageUrl());
            existing.setFeatured(event.featured());
            existing.setExternalLink(event.externalLink());
            existing.setDisplayOrder(event.displayOrder());
            return mapper.toDomain(jpaRepository.save(existing));
        }).orElseGet(() -> mapper.toDomain(jpaRepository.save(mapper.toEntity(event))));
    }

    @Override
    public void deleteById(String id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public Optional<Integer> findMaxDisplayOrder() {
        return jpaRepository.findMaxDisplayOrder();
    }
}
