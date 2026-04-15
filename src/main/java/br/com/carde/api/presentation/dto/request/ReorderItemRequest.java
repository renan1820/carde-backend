package br.com.carde.api.presentation.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record ReorderItemRequest(
        @NotBlank String id,
        @Min(0) int displayOrder
) {}
