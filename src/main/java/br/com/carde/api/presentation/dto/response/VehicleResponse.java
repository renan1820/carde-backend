package br.com.carde.api.presentation.dto.response;

import java.util.Map;

public record VehicleResponse(
        String id,
        String name,
        String brand,
        int year,
        String category,
        String shortDescription,
        String fullHistory,
        String imageUrl,
        String engineSoundUrl,
        Map<String, String> specs
) {}
