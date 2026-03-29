package br.com.carde.api.presentation.dto.response;

public record VehicleListItemResponse(
        String id,
        String name,
        String brand,
        int year,
        String category,
        String shortDescription,
        String imageUrl,
        boolean hasEngineSound
) {}
