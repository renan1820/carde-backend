package br.com.carde.api.presentation.mapper;

import br.com.carde.api.config.AppConfig;
import br.com.carde.api.domain.model.Vehicle;
import br.com.carde.api.presentation.dto.response.VehicleListItemResponse;
import br.com.carde.api.presentation.dto.response.VehicleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VehicleResponseMapper {

    private final AppConfig appConfig;

    public VehicleListItemResponse toListItem(Vehicle vehicle) {
        return new VehicleListItemResponse(
                vehicle.id(),
                vehicle.name(),
                vehicle.brand(),
                vehicle.year(),
                vehicle.category().name(),
                vehicle.shortDescription(),
                appConfig.cdn().resolve(vehicle.imageUrl()),
                vehicle.hasEngineSound()
        );
    }

    public VehicleResponse toDetail(Vehicle vehicle) {
        return new VehicleResponse(
                vehicle.id(),
                vehicle.name(),
                vehicle.brand(),
                vehicle.year(),
                vehicle.category().name(),
                vehicle.shortDescription(),
                vehicle.fullHistory(),
                appConfig.cdn().resolve(vehicle.imageUrl()),
                appConfig.cdn().resolve(vehicle.engineSoundUrl()),
                vehicle.specs()
        );
    }
}
