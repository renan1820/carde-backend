package br.com.carde.api.presentation.mapper;

import br.com.carde.api.config.AppConfig;
import br.com.carde.api.domain.model.MuseumEvent;
import br.com.carde.api.presentation.dto.response.EventResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventResponseMapper {

    private final AppConfig appConfig;

    public EventResponse toResponse(MuseumEvent event) {
        return new EventResponse(
                event.id(),
                event.title(),
                event.description(),
                event.date(),
                appConfig.cdn().resolve(event.imageUrl()),
                event.featured()
        );
    }
}
