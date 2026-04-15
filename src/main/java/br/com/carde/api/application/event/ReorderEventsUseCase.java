package br.com.carde.api.application.event;

import br.com.carde.api.domain.model.MuseumEvent;
import br.com.carde.api.domain.repository.EventRepository;
import br.com.carde.api.exception.ResourceNotFoundException;
import br.com.carde.api.presentation.dto.request.ReorderItemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReorderEventsUseCase {

    private final EventRepository repository;

    @CacheEvict(cacheNames = "events", allEntries = true)
    @Transactional
    public void execute(List<ReorderItemRequest> items) {
        items.forEach(item -> {
            MuseumEvent existing = repository.findById(item.id())
                    .orElseThrow(() -> new ResourceNotFoundException("Event", item.id()));
            MuseumEvent reordered = new MuseumEvent(
                    existing.id(),
                    existing.title(),
                    existing.description(),
                    existing.date(),
                    existing.imageUrl(),
                    existing.featured(),
                    existing.externalLink(),
                    item.displayOrder()
            );
            repository.save(reordered);
        });
    }
}
