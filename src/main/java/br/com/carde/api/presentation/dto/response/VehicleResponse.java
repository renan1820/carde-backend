package br.com.carde.api.presentation.dto.response;

import java.util.List;
import java.util.Map;

public record VehicleResponse(
        String id,
        String name,
        String brand,
        int year,
        String category,
        String shortDescription,
        String fullHistory,
        List<String> imageUrls,
        String engineSoundUrl,
        Map<String, String> specs,
        String qrCodeImageUrl
) {}
