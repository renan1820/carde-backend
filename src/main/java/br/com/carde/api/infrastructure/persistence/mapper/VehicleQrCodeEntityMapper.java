package br.com.carde.api.infrastructure.persistence.mapper;

import br.com.carde.api.domain.model.VehicleQrCode;
import br.com.carde.api.infrastructure.persistence.entity.VehicleQrCodeEntity;
import org.springframework.stereotype.Component;

@Component
public class VehicleQrCodeEntityMapper {

    public VehicleQrCode toDomain(VehicleQrCodeEntity entity) {
        return new VehicleQrCode(
                entity.getId(),
                entity.getVehicleId(),
                entity.getQrValue(),
                entity.getImageUrl(),
                entity.getCreatedAt()
        );
    }

    public VehicleQrCodeEntity toEntity(VehicleQrCode domain) {
        VehicleQrCodeEntity entity = new VehicleQrCodeEntity();
        entity.setId(domain.id());
        entity.setVehicleId(domain.vehicleId());
        entity.setQrValue(domain.qrValue());
        entity.setImageUrl(domain.imageUrl());
        return entity;
    }
}
