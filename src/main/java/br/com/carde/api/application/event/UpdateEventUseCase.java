package br.com.carde.api.application.event;

import br.com.carde.api.domain.model.MuseumEvent;
import br.com.carde.api.domain.repository.EventRepository;
import br.com.carde.api.exception.ResourceNotFoundException;
import br.com.carde.api.presentation.dto.request.EventRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateEventUseCase {

    private final EventRepository repository;

    @CacheEvict(cacheNames = "events", allEntries = true)
    @Transactional
    public MuseumEvent execute(String id, EventRequest request) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Event", id);
        }
        MuseumEvent updated = new MuseumEvent(
                id,
                request.title(),
                request.description(),
                request.date(),
                request.imageUrl(),
                request.featured()
        );
        return repository.save(updated);
    }
}
