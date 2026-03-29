package br.com.carde.api.application.event;

import br.com.carde.api.domain.repository.EventRepository;
import br.com.carde.api.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteEventUseCase {

    private final EventRepository repository;

    @Transactional
    public void execute(String id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Event", id);
        }
        repository.deleteById(id);
    }
}
