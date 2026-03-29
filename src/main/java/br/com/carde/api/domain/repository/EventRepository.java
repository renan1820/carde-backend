package br.com.carde.api.domain.repository;

import br.com.carde.api.domain.model.MuseumEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface EventRepository {
    Page<MuseumEvent> findAll(Pageable pageable);
    Page<MuseumEvent> findFeatured(Pageable pageable);
    Optional<MuseumEvent> findById(String id);
    MuseumEvent save(MuseumEvent event);
    void deleteById(String id);
    boolean existsById(String id);
}
