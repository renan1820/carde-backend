package br.com.carde.api.infrastructure.persistence.adapter;

import br.com.carde.api.domain.model.NotificationLog;
import br.com.carde.api.domain.repository.NotificationRepository;
import br.com.carde.api.infrastructure.persistence.jpa.NotificationLogJpaRepository;
import br.com.carde.api.infrastructure.persistence.mapper.NotificationLogMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NotificationRepositoryAdapter implements NotificationRepository {

    private final NotificationLogJpaRepository jpaRepository;
    private final NotificationLogMapper mapper;

    @Override
    public NotificationLog save(NotificationLog log) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(log)));
    }

    @Override
    public List<NotificationLog> findAllOrderBySentAtDesc() {
        return jpaRepository.findAllByOrderBySentAtDesc()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
