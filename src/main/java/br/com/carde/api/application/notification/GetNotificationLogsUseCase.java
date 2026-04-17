package br.com.carde.api.application.notification;

import br.com.carde.api.domain.model.NotificationLog;
import br.com.carde.api.domain.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetNotificationLogsUseCase {

    private final NotificationRepository repository;

    public List<NotificationLog> execute() {
        return repository.findAllOrderBySentAtDesc();
    }
}
