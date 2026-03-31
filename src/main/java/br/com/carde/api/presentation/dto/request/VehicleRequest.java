package br.com.carde.api.presentation.dto.request;

import br.com.carde.api.domain.model.VehicleCategory;
import jakarta.validation.constraints.*;

import java.util.List;

public record VehicleRequest(
        @NotBlank @Size(max = 200) String name,
        @NotBlank @Size(max = 100) String brand,
        @Min(1800) @Max(2100) int year,
        @NotNull VehicleCategory category,
        @NotBlank @Size(max = 500) String shortDescription,
        @NotBlank String fullHistory,
        @NotEmpty @Size(max = 10) List<@NotBlank @Size(max = 1000) String> imageUrls,
        @Size(max = 1000) String engineSoundUrl,
        List<SpecRequest> specs
) {
    public record SpecRequest(
            @NotBlank @Size(max = 100) String key,
            @NotBlank @Size(max = 500) String value,
            int sortOrder
    ) {}
}
