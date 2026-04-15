package br.com.carde.api.infrastructure.persistence.adapter;

import br.com.carde.api.domain.model.VehicleQrCode;
import br.com.carde.api.domain.repository.VehicleQrCodeRepository;
import br.com.carde.api.infrastructure.persistence.jpa.VehicleQrCodeJpaRepository;
import br.com.carde.api.infrastructure.persistence.mapper.VehicleQrCodeEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VehicleQrCodeRepositoryAdapter implements VehicleQrCodeRepository {

    private final VehicleQrCodeJpaRepository jpaRepository;
    private final VehicleQrCodeEntityMapper mapper;

    @Override
    public Optional<VehicleQrCode> findByVehicleId(String vehicleId) {
        return jpaRepository.findByVehicleId(vehicleId).map(mapper::toDomain);
    }

    @Override
    public boolean existsByQrValue(String qrValue) {
        return jpaRepository.existsByQrValue(qrValue);
    }

    @Override
    public VehicleQrCode save(VehicleQrCode qrCode) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(qrCode)));
    }
}
