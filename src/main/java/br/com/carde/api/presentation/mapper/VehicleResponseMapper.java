package br.com.carde.api.presentation.mapper;

import br.com.carde.api.config.AppConfig;
import br.com.carde.api.domain.model.Vehicle;
import br.com.carde.api.domain.repository.VehicleQrCodeRepository;
import br.com.carde.api.presentation.dto.response.VehicleListItemResponse;
import br.com.carde.api.presentation.dto.response.VehicleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class VehicleResponseMapper {

    private final AppConfig appConfig;
    private final VehicleQrCodeRepository qrCodeRepository;

    public VehicleListItemResponse toListItem(Vehicle vehicle) {
        return new VehicleListItemResponse(
                vehicle.id(),
                vehicle.name(),
                vehicle.brand(),
                vehicle.year(),
                vehicle.category().name(),
                vehicle.shortDescription(),
                appConfig.cdn().resolve(vehicle.primaryImageUrl()),
                vehicle.hasEngineSound()
        );
    }

    public VehicleResponse toDetail(Vehicle vehicle) {
        List<String> resolvedUrls = vehicle.imageUrls() == null ? List.of() :
                vehicle.imageUrls().stream()
                        .map(url -> appConfig.cdn().resolve(url))
                        .filter(Objects::nonNull)
                        .toList();
        String qrCodeImageUrl = qrCodeRepository.findByVehicleId(vehicle.id())
                .map(qr -> qr.imageUrl())
                .orElse(null);
        return new VehicleResponse(
                vehicle.id(),
                vehicle.name(),
                vehicle.brand(),
                vehicle.year(),
                vehicle.category().name(),
                vehicle.shortDescription(),
                vehicle.fullHistory(),
                resolvedUrls,
                appConfig.cdn().resolve(vehicle.engineSoundUrl()),
                vehicle.specs(),
                qrCodeImageUrl,
                vehicle.displayOrder()
        );
    }
}
