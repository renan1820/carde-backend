package br.com.carde.api.domain.model;

import java.util.List;
import java.util.Map;

public record Vehicle(
        String id,
        String name,
        String brand,
        int year,
        VehicleCategory category,
        String shortDescription,
        String fullHistory,
        List<String> imageUrls,
        String engineSoundUrl,
        Map<String, String> specs
) {
    public String primaryImageUrl() {
        return imageUrls == null || imageUrls.isEmpty() ? null : imageUrls.get(0);
    }

    public boolean hasEngineSound() {
        return engineSoundUrl != null && !engineSoundUrl.isBlank();
    }
}
