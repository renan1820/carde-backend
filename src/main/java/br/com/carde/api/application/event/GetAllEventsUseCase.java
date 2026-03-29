package br.com.carde.api.application.event;

import br.com.carde.api.domain.model.MuseumEvent;
import br.com.carde.api.domain.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetAllEventsUseCase {

    private final EventRepository repository;

    @Transactional(readOnly = true)
    public Page<MuseumEvent> execute(boolean featuredOnly, Pageable pageable) {
        if (featuredOnly) {
            return repository.findFeatured(pageable);
        }
        return repository.findAll(pageable);
    }
}
