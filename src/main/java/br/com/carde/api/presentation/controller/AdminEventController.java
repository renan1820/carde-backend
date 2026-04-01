package br.com.carde.api.presentation.controller;

import br.com.carde.api.application.event.*;
import br.com.carde.api.domain.model.MuseumEvent;
import br.com.carde.api.presentation.dto.request.EventRequest;
import br.com.carde.api.presentation.dto.response.EventResponse;
import br.com.carde.api.presentation.mapper.EventResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/events")
@RequiredArgsConstructor
@Tag(name = "Admin — Events", description = "CRUD de eventos (requer JWT)")
@SecurityRequirement(name = "bearerAuth")
public class AdminEventController {

    private final GetAllEventsUseCase getAllEvents;
    private final CreateEventUseCase createEvent;
    private final UpdateEventUseCase updateEvent;
    private final DeleteEventUseCase deleteEvent;
    private final EventResponseMapper mapper;

    @GetMapping
    @Operation(summary = "Lista todos os eventos")
    public ResponseEntity<List<EventResponse>> listAll() {
        List<EventResponse> result = getAllEvents
                .execute(false, PageRequest.of(0, 200))
                .map(mapper::toResponse)
                .getContent();
        return ResponseEntity.ok(result);
    }

    @PostMapping
    @Operation(summary = "Cria evento")
    public ResponseEntity<EventResponse> create(@Valid @RequestBody EventRequest request) {
        MuseumEvent created = createEvent.execute(request);
        EventResponse response = mapper.toResponse(created);
        return ResponseEntity.created(URI.create("/api/v1/events/" + created.id()))
                .body(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza evento")
    public ResponseEntity<EventResponse> update(
            @PathVariable String id,
            @Valid @RequestBody EventRequest request) {
        MuseumEvent updated = updateEvent.execute(id, request);
        return ResponseEntity.ok(mapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove evento")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        deleteEvent.execute(id);
        return ResponseEntity.noContent().build();
    }
}
