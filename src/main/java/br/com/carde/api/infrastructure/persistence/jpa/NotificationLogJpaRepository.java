package br.com.carde.api.infrastructure.persistence.jpa;

import br.com.carde.api.infrastructure.persistence.entity.NotificationLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationLogJpaRepository extends JpaRepository<NotificationLogEntity, String> {
    List<NotificationLogEntity> findAllByOrderBySentAtDesc();
}
