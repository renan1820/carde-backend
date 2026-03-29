package br.com.carde.api.infrastructure.persistence.jpa;

import br.com.carde.api.infrastructure.persistence.entity.EventEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventJpaRepository extends JpaRepository<EventEntity, String> {

    Page<EventEntity> findByActiveTrueOrderByEventDateAsc(Pageable pageable);

    Page<EventEntity> findByFeaturedTrueAndActiveTrueOrderByEventDateAsc(Pageable pageable);
}
