package br.com.carde.api.infrastructure.persistence.jpa;

import br.com.carde.api.infrastructure.persistence.entity.VehicleQrCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleQrCodeJpaRepository extends JpaRepository<VehicleQrCodeEntity, String> {
    Optional<VehicleQrCodeEntity> findByVehicleId(String vehicleId);
    boolean existsByQrValue(String qrValue);
}
