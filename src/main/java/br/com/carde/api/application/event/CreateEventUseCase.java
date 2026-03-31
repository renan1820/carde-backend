package br.com.carde.api.application.event;

import br.com.carde.api.domain.model.MuseumEvent;
import br.com.carde.api.domain.repository.EventRepository;
import br.com.carde.api.presentation.dto.request.EventRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateEventUseCase {

    private final EventRepository repository;

    @CacheEvict(cacheNames = "events", allEntries = true)
    @Transactional
    public MuseumEvent execute(EventRequest request) {
        MuseumEvent event = new MuseumEvent(
                UUID.randomUUID().toString(),
                request.title(),
                request.description(),
                request.date(),
                request.imageUrl(),
                request.featured()
        );
        return repository.save(event);
    }
}
