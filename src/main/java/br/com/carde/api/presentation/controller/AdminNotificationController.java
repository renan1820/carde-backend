package br.com.carde.api.presentation.controller;

import br.com.carde.api.application.notification.GetNotificationLogsUseCase;
import br.com.carde.api.application.notification.SendNotificationUseCase;
import br.com.carde.api.presentation.dto.request.SendNotificationRequest;
import br.com.carde.api.presentation.dto.response.NotificationLogResponse;
import br.com.carde.api.presentation.mapper.NotificationResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/notifications")
@RequiredArgsConstructor
@Tag(name = "Admin — Notifications", description = "Disparo de notificações push (requer JWT)")
@SecurityRequirement(name = "bearerAuth")
public class AdminNotificationController {

    private final SendNotificationUseCase sendNotification;
    private final GetNotificationLogsUseCase getNotificationLogs;
    private final NotificationResponseMapper mapper;

    @PostMapping
    @Operation(summary = "Dispara notificação push para todos os usuários do app")
    public ResponseEntity<NotificationLogResponse> send(
            @Valid @RequestBody SendNotificationRequest request,
            @AuthenticationPrincipal UserDetails principal) {
        String sentBy = principal != null ? principal.getUsername() : "admin";
        return ResponseEntity.ok(mapper.toResponse(sendNotification.execute(request, sentBy)));
    }

    @GetMapping
    @Operation(summary = "Lista histórico de notificações enviadas")
    public ResponseEntity<List<NotificationLogResponse>> history() {
        return ResponseEntity.ok(
                getNotificationLogs.execute().stream().map(mapper::toResponse).toList()
        );
    }
}
