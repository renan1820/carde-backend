package br.com.carde.api.presentation.controller;

import br.com.carde.api.application.event.GetAllEventsUseCase;
import br.com.carde.api.domain.model.MuseumEvent;
import br.com.carde.api.presentation.dto.response.EventResponse;
import br.com.carde.api.presentation.dto.response.PagedResponse;
import br.com.carde.api.presentation.mapper.EventResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
@Validated
@Tag(name = "Events", description = "Eventos do Museu CARDE")
public class EventController {

    private final GetAllEventsUseCase getAllEvents;
    private final EventResponseMapper mapper;

    @GetMapping
    @Operation(summary = "Lista eventos", description = "Retorna lista paginada. Use ?featured=true para apenas eventos em destaque")
    public ResponseEntity<PagedResponse<EventResponse>> listEvents(
            @RequestParam(defaultValue = "false") boolean featured,
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "20") @Min(1) @Max(50) int size
    ) {
        Page<MuseumEvent> events = getAllEvents.execute(featured, PageRequest.of(page, size));
        Page<EventResponse> dtos = events.map(mapper::toResponse);
        return ResponseEntity.ok(PagedResponse.of(dtos));
    }
}
