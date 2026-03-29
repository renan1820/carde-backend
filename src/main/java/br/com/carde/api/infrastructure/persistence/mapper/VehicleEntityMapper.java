package br.com.carde.api.infrastructure.persistence.mapper;

import br.com.carde.api.domain.model.Vehicle;
import br.com.carde.api.infrastructure.persistence.entity.VehicleEntity;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class VehicleEntityMapper {

    public Vehicle toDomain(VehicleEntity entity) {
        Map<String, String> specs = new LinkedHashMap<>();
        if (entity.getSpecs() != null) {
            entity.getSpecs().forEach(s -> specs.put(s.getSpecKey(), s.getSpecValue()));
        }
        return new Vehicle(
                entity.getId(),
                entity.getName(),
                entity.getBrand(),
                entity.getYear(),
                entity.getCategory(),
                entity.getShortDescription(),
                entity.getFullHistory(),
                entity.getImageUrl(),
                entity.getEngineSoundUrl(),
                specs
        );
    }
}
