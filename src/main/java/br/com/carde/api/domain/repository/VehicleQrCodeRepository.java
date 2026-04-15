package br.com.carde.api.domain.repository;

import br.com.carde.api.domain.model.VehicleQrCode;

import java.util.Optional;

public interface VehicleQrCodeRepository {
    Optional<VehicleQrCode> findByVehicleId(String vehicleId);
    boolean existsByQrValue(String qrValue);
    VehicleQrCode save(VehicleQrCode qrCode);
}
