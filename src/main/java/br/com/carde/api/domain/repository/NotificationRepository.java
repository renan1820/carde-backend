package br.com.carde.api.domain.repository;

import br.com.carde.api.domain.model.NotificationLog;

import java.util.List;

public interface NotificationRepository {
    NotificationLog save(NotificationLog log);
    List<NotificationLog> findAllOrderBySentAtDesc();
}
