package br.com.carde.api.presentation.dto.response;

public record VehicleQrCodeResponse(
        String id,
        String vehicleId,
        String qrValue,
        String imageUrl
) {}
