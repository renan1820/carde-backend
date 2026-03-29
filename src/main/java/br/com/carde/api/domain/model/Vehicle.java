package br.com.carde.api.domain.model;

import java.util.Map;

public record Vehicle(
        String id,
        String name,
        String brand,
        int year,
        VehicleCategory category,
        String shortDescription,
        String fullHistory,
        String imageUrl,
        String engineSoundUrl,
        Map<String, String> specs
) {
    public boolean hasEngineSound() {
        return engineSoundUrl != null && !engineSoundUrl.isBlank();
    }
}
