package br.com.carde.api.application.notification;

import br.com.carde.api.domain.model.NotificationLog;
import br.com.carde.api.domain.repository.NotificationRepository;
import br.com.carde.api.infrastructure.fcm.FcmService;
import br.com.carde.api.presentation.dto.request.SendNotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendNotificationUseCase {

    private final FcmService fcmService;
    private final NotificationRepository repository;

    public NotificationLog execute(SendNotificationRequest request, String sentBy) {
        fcmService.sendToTopic("carde", request.title(), request.body());

        NotificationLog log = new NotificationLog(
                null,
                request.title(),
                request.body(),
                null,
                sentBy
        );
        return repository.save(log);
    }
}
