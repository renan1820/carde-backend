package br.com.carde.api.infrastructure.persistence.jpa;

import br.com.carde.api.infrastructure.persistence.entity.EventEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EventJpaRepository extends JpaRepository<EventEntity, String> {

    Page<EventEntity> findByActiveTrueOrderByDisplayOrderAsc(Pageable pageable);

    Page<EventEntity> findByFeaturedTrueAndActiveTrueOrderByDisplayOrderAsc(Pageable pageable);

    @Query("SELECT MAX(e.displayOrder) FROM EventEntity e WHERE e.active = true")
    Optional<Integer> findMaxDisplayOrder();
}
