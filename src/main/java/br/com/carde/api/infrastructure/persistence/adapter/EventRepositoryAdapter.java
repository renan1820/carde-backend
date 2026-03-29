package br.com.carde.api.infrastructure.persistence.adapter;

import br.com.carde.api.domain.model.MuseumEvent;
import br.com.carde.api.domain.repository.EventRepository;
import br.com.carde.api.infrastructure.persistence.jpa.EventJpaRepository;
import br.com.carde.api.infrastructure.persistence.mapper.EventEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventRepositoryAdapter implements EventRepository {

    private final EventJpaRepository jpaRepository;
    private final EventEntityMapper mapper;

    @Override
    public Page<MuseumEvent> findAll(Pageable pageable) {
        return jpaRepository.findByActiveTrueOrderByEventDateAsc(pageable)
                .map(mapper::toDomain);
    }

    @Override
    public Page<MuseumEvent> findFeatured(Pageable pageable) {
        return jpaRepository.findByFeaturedTrueAndActiveTrueOrderByEventDateAsc(pageable)
                .map(mapper::toDomain);
    }
}
