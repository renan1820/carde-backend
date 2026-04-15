package br.com.carde.api.domain.model;

import java.time.OffsetDateTime;

public record VehicleQrCode(
        String id,
        String vehicleId,
        String qrValue,
        String imageUrl,
        OffsetDateTime createdAt
) {}
